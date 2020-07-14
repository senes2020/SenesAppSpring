package com.senes.senesapp.dto;

public class CompanheiroDTO {
	
	private Long id;
	private Long idUsuario;
	private String celular;
	private String nome;
	private String email;
	private Float avaliacao;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Float getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Float avaliacao) {
		this.avaliacao = avaliacao;
	}
}
