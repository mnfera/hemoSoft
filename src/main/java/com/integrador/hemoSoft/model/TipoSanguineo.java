package com.integrador.hemoSoft.model;

public enum TipoSanguineo {
		
	AP("A+"),
	AN("A-"),
	BP("B+"),
	BN("B-"),
	ABP("AB+"),
	ABN("AB-"),
	OP("O+"),
	ON("O-");
	
	private String TipoDescricao;
	
	TipoSanguineo(String TipoDescricao){
		this.TipoDescricao = TipoDescricao;
	}

	public String getTipoDescricao() {
		return TipoDescricao;
	}
	 
	
}
