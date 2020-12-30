package com.accenture.desafio.domain.enums;

public enum Origin {
	MOBILE(1, "ROLE_MOBILE"),
	DESKTOP(2, "ROLE_DESKTOP"),
	WEB(3, "ROLE_WEB");
	
	private int cod;
	private String descricao;
	
	private Origin(int cod, String descricao) {
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
	
	public static Origin toEnum(Integer cod) {
		if(cod == null){
			return null;
		}
		for(Origin x : Origin.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido" + cod);
	}
}
