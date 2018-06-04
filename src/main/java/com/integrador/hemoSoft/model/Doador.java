package com.integrador.hemoSoft.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Doador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 50, message = "O Nome não pode conter mais de 50 caracteres")
	private String nome;
	
	@NotBlank(message = "Cpf é obrigatório")
	@Size(max = 11, message = "O Cpf não pode conter mais de 11 caracteres")
	private String cpf;
	
	private String telefone;
	
	private String sexo;
	
	@NotBlank(message = "Endereço é obrigatório")
	@Size(max = 30, message = "O Endereço não pode conter mais de 30 caracteres")
	private String endereco;
	
	@NotBlank(message = "Cidade é obrigatória")
	@Size(max = 20, message = "O Nome não pode conter mais de 20 caracteres")
	private String cidade;
	
	@NotBlank(message = "Senha é obrigatória")
	@Size(min = 6, max = 24, message = "A senha deve ter de 6 até 24 caracteres")
	private String senha;
	
	@NotBlank(message = "E-mail é obrigatório")
	private String email;
	
	@Enumerated(EnumType.STRING)
	private TipoSanguineo tipoS;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TipoSanguineo getTipoS() {
		return tipoS;
	}
	public void setTipoS(TipoSanguineo tipoS) {
		this.tipoS = tipoS;
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
		Doador other = (Doador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
