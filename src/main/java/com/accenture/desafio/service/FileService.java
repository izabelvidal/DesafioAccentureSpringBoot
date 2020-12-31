package com.accenture.desafio.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.desafio.config.FileStorageConfig;
import com.accenture.desafio.exception.FileStorageException;
import com.accenture.desafio.exception.MyFileNotFoundException;

@Service
public class FileService {

	private final Path fileStorageLocation;

	@Autowired
	public FileService(FileStorageConfig fileStorageConfig) {
		
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored", e);
		}
	}

	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalida path sequence " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (Exception e) {
			throw new MyFileNotFoundException("File not found " + fileName, e);
		}

	}
	/*
	 * public void upload(MultipartFile file) throws IOException { File convertFile
	 * = new
	 * File("C:\\Users\\izabe\\OneDrive\\Documents\\01 Meus Projetos\\arquivos-spring"
	 * + file.getOriginalFilename()); convertFile.createNewFile(); FileOutputStream
	 * fout = new FileOutputStream(convertFile); fout.write(file.getBytes());
	 * fout.close(); }
	 */
}
