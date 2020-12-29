package com.accenture.desafio.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public void upload(MultipartFile file) throws IOException {
		File convertFile = new File("C:\\Users\\izabe\\OneDrive\\Documents\\01 Meus Projetos\\arquivos-spring"
				+ file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
	}
}
