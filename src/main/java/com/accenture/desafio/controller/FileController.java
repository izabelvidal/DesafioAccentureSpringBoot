package com.accenture.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.desafio.domain.File;
import com.accenture.desafio.service.FileService;

@RestController
@RequestMapping(value = "/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public File uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/v1/downloadFile/")
				.path(fileName).toUriString();

		return new File(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
}