package com.accenture.desafio.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="arquivo_excel")
public class File implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Integer tipo;
	private List<String> palavras;
	public File(String nome, Integer tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public List<String> getPalavras() {
		return palavras;
	}
	public void setPalavras(List<String> palavras) {
		this.palavras = palavras;
	}
}
