package com.senes.senesapp.tools.pagamento.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tbl_profissional_pagamento")
public class Profissional {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_profissional;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String cpf;

	public Profissional(String nome, String email, String cpf) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}
	
	public Profissional() {
		
	}
	
	public Long getId_profissional() {
		return id_profissional;
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Profissional [id_profissional=" + id_profissional + ", nome=" + nome + ", email=" + email + ", cpf="
				+ cpf + "]";
	}

}
