package com.senes.senesapp.tools.pagamento.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_beneficiario_pagante")
public class BeneficiarioPagante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_beneficiario;
	private String nome;
	private String email;
	private String cpf;
	
	public BeneficiarioPagante(String nome, String email, String cpf) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}
	
	public BeneficiarioPagante() {
	
	}
	
	public Long getId_beneficiario() {
		return id_beneficiario;
	}
	public void setId_beneficiario(Long id_beneficiario) {
		this.id_beneficiario = id_beneficiario;
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
		return "BeneficiarioPagante [id_beneficiario=" + id_beneficiario + ", nome=" + nome + ", email=" + email
				+ ", cpf=" + cpf + "]";
	}

}
