package com.accenture.desafio.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StorageComponent {
	
	@Value("${contato.disco.raiz}")
	private String raiz;
	
	@Value("${contato.disco.directory-files}")
	private String directory;
	
	public void salveFile(MultipartFile file) {
		this.salveFileToDirectory(this.directory, file);
	}
	
	public void salveFileToDirectory(String directory, MultipartFile file) {
		Path directoryPath = Paths.get(this.raiz, directory);
		Path filePath = directoryPath.resolve(file.getOriginalFilename());
		
		try {
			Files.createDirectories(directoryPath);
			file.transferTo(filePath.toFile());			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
}
