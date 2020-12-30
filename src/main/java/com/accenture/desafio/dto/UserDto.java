package com.accenture.desafio.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.accenture.desafio.domain.User;
import com.accenture.desafio.domain.enums.Origem;

public class UserDto {
	
	Long id;
	
	@NotBlank(message = "Nome não pode ser vazio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres!")
	private String nome;
	private Integer origem;
	
	public UserDto() {}
	
	public UserDto(User obj){
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.origem = obj.getOrigem().getCod();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Origem getOrigem() {
		return Origem.toEnum(origem);
	}

	public void setOrigem(Origem origem) {
		this.origem = origem.getCod();
	}
}
