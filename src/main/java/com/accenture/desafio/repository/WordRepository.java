package com.accenture.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.desafio.domain.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer>{
}
