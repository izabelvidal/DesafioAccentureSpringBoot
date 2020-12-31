package com.accenture.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.desafio.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Transactional(readOnly=true)
	User findByEmail(String email);
}
