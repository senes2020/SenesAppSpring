package com.senes.senesapp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BeneficiarioCredentials {
	
	@NotNull
	@Size(min=5, max=100, message="Por favor, digite um nome com pelo menos 5 carctéres.")
	private String nome;
	
	@Email(message="Por favor, informar um email válido.")
	private String email;
	
	@NotNull
	private String celular;
	
	private Integer codigo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	

}
