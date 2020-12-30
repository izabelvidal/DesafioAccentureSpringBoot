package com.accenture.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.desafio.domain.User;
import com.accenture.desafio.dto.UserDto;
import com.accenture.desafio.repository.UserRepository;
import com.accenture.desafio.service.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User find(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}

	public User insert(User obj) {
		obj.setId(null);
		obj = userRepository.save(obj);
		return obj;
	}

	public User update(User obj) {
		User newObj = find(obj.getId());
		updateData(newObj, obj);
		return userRepository.save(newObj);
	}

	public void delete(Long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}else {
			throw new ObjectNotFoundException("Pessoa não localizada para deletar");
		}
	}

	public User fromDto(UserDto objDto) {
		return new User(null, objDto.getNome(), objDto.getOrigem());
	}

	public void updateData(User newObj, User obj) {
		newObj.setNome(obj.getNome());
		newObj.setOrigem(obj.getOrigem());
	}
}
