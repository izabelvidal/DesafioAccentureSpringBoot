package com.accenture.desafio.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.accenture.desafio.domain.User;
import com.accenture.desafio.domain.enums.Origin;

public class UserDto {
	
	private Long id;
	
	@NotBlank(message = "Nome não pode ser vazio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres!")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	@Email(message="Email Inválido!")
	private String email;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	private Integer device;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	private String senha;
	
	
	public UserDto() {}
	
	public UserDto(User obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.device = obj.getDevice().getCod();
		this.senha = obj.getSenha();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Origin getDevice() {
		return Origin.toEnum(device);
	}

	public void setDevice(Origin origin) {
		this.device = origin.getCod();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
