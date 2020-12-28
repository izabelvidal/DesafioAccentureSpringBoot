package com.accenture.desafio.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.accenture.desafio.domain.Administrador;

public class AdministradorDto {
	
	Long id;
	
	@NotBlank(message = "Nome não pode ser vazio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres!")
	private String nome;
	
	public AdministradorDto() {}
	
	public AdministradorDto(Administrador obj){
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
