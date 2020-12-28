package com.accenture.desafio.domain.enums;

public enum Origem {
	MOBILE(1, "ROLE_MOBILE"),
	DESKTOP(2, "ROLE_DESKTOP"),
	WEB(3, "ROLE_WEB");
	
	private int cod;
	private String descricao;
	
	private Origem(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Origem toEnum(Integer cod) {
		if(cod == null){
			return null;
		}
		for(Origem x : Origem.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido" + cod);
	}
}
