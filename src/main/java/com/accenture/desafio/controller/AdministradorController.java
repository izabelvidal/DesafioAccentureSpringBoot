package com.accenture.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.desafio.domain.Administrador;
import com.accenture.desafio.service.AdministradorService;

@RestController
@RequestMapping(value = "/administrador")
public class AdministradorController {

	@Autowired
	private AdministradorService administradorService;
	
	@GetMapping
	public ResponseEntity<List<Administrador>> findAll(){
		List<Administrador> obj = administradorService.findAll();
		return ResponseEntity.ok().body(obj);
	}
}
