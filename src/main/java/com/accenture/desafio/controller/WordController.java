package com.accenture.desafio.controller;

import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.desafio.domain.Word;
import com.accenture.desafio.service.ExcelService;
import com.accenture.desafio.service.WordService;

@RestController
@RequestMapping(value = "/word")
public class WordController {

	@Autowired
	private WordService wordService;
	
	@GetMapping
	public ResponseEntity<List<Word>> findAll(){
		List<Word> obj = wordService.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Word> findByid(@PathVariable Long id){
		return ResponseEntity.ok().body(wordService.find(id));
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Void> insert(@RequestBody Word newObj){
		Word obj = wordService.insert(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Word> listWords = wordService.findAll();
         
        ExcelService excelExporter = new ExcelService(listWords);
         
        excelExporter.export(response);    
    }  
}
