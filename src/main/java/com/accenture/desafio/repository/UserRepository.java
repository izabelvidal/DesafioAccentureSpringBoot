package com.accenture.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.desafio.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
