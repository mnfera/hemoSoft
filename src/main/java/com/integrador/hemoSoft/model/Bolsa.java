package com.integrador.hemoSoft.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "tbl_bolsas")
public class Bolsa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "bolsa_seq", sequenceName = "bolsa_seq") //Cria uma sequence para ser usada com a tabela
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bolsa_seq") //Define que a tabela fará uso da sequence criada antes
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoSanguineo tipoS;	
	
	@NotBlank(message = "Quantidade é obrigatório")
	@Size(max = 2, message = "A quantidade não pode conter mais de 2 caracteres")
	private String quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoSanguineo getTipoS() {
		return tipoS;
	}

	public void setTipoS(TipoSanguineo tipoS) {
		this.tipoS = tipoS;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bolsa other = (Bolsa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
