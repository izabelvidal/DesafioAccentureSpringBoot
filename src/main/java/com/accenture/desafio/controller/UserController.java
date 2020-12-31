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

import com.accenture.desafio.domain.User;
import com.accenture.desafio.dto.UserDto;
import com.accenture.desafio.service.UsuarioService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UsuarioService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> obj = userService.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findByid(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.find(id));
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Void> insert(@RequestBody UserDto objDto){
		User obj = userService.fromDTO(objDto);
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDto objDto, @PathVariable Long id){
		User obj = userService.fromDTO(objDto);
		obj.setId(id);
		obj = userService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
