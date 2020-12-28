package com.accenture.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.desafio.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
