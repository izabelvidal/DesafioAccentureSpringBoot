package com.accenture.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.accenture.desafio.domain.Administrador;
import com.accenture.desafio.dto.AdministradorDto;
import com.accenture.desafio.repository.AdministradorRepository;
import com.accenture.desafio.service.exceptions.DataIntegrityException;
import com.accenture.desafio.service.exceptions.ObjectNotFoundException;

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepository;

	public List<Administrador> findAll() {
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

	public Administrador update(Administrador obj) {
		Administrador newObj = find(obj.getId());
		updateData(newObj, obj);
		return administradorRepository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			administradorRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public Administrador fromDto(AdministradorDto objDto) {
		return new Administrador(null, objDto.getNome());
	}

	public void updateData(Administrador newObj, Administrador obj) {
		newObj.setNome(obj.getNome());
	}
}
