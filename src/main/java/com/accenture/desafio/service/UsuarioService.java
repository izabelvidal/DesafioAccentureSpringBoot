package com.accenture.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.accenture.desafio.domain.Usuario;
import com.accenture.desafio.dto.UsuarioDto;
import com.accenture.desafio.repository.UsuarioRepository;
import com.accenture.desafio.service.exceptions.DataIntegrityException;
import com.accenture.desafio.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository administradorRepository;

	public List<Usuario> findAll() {
		return administradorRepository.findAll();
	}

	public Usuario find(Long id) {
		Optional<Usuario> obj = administradorRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = administradorRepository.save(obj);
		return obj;
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
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

	public Usuario fromDto(UsuarioDto objDto) {
		return new Usuario(null, objDto.getNome());
	}

	public void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
	}
}
