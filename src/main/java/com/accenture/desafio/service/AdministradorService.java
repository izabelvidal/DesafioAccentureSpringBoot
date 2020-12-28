package com.accenture.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.desafio.domain.Administrador;
import com.accenture.desafio.repository.AdministradorRepository;
import com.accenture.desafio.service.exceptions.ObjectNotFoundException;

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	public List<Administrador> findAll(){
		return administradorRepository.findAll();
	}
	
	public Administrador find(Long id) {
		Optional<Administrador> obj = administradorRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Administrador.class.getName()));
	}
	
	public Administrador insert(Administrador obj) {
		obj.setId(null);
		obj = administradorRepository.save(obj);
		return obj;
	}
}
