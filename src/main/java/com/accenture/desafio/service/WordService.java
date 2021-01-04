package com.accenture.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.desafio.domain.Word;
import com.accenture.desafio.repository.WordRepository;
import com.accenture.desafio.service.exceptions.ObjectNotFoundException;

@Service
public class WordService {
	
	@Autowired
	private WordRepository wordRepository;

	public List<Word> findAll() {
		return wordRepository.findAll();
	}

	public Word find(Long id) {
		Optional<Word> obj = wordRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Word.class.getName()));
	}

	public Word insert(Word obj) {
		obj.setId(null);
		obj = wordRepository.save(obj);
		return obj;
	}
}
