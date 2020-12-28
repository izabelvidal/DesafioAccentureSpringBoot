package com.accenture.desafio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.desafio.domain.Usuario;
import com.accenture.desafio.dto.UsuarioDto;
import com.accenture.desafio.service.UsuarioService;

@RestController
@RequestMapping(value = "/user")
public class UsuarioController {

	@Autowired
	private UsuarioService administradorService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> obj = administradorService.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findByid(@PathVariable Long id){
		return ResponseEntity.ok().body(administradorService.find(id));
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Void> insert(@RequestBody UsuarioDto objDto){
		Usuario obj = administradorService.fromDto(objDto);
		obj = administradorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UsuarioDto objDto, @PathVariable Long id){
		Usuario obj = administradorService.fromDto(objDto);
		obj.setId(id);
		obj = administradorService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		administradorService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
