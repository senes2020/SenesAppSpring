package com.senes.senesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_especializacoes")
public class Especializacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_especializacao;
	
	private String nome_especializacao;

	public Long getId_especializacao() {
		return id_especializacao;
	}

	public void setId_especializacao(Long id_especializacao) {
		this.id_especializacao = id_especializacao;
	}

	public String getNome_especializacao() {
		return nome_especializacao;
	}

	public void setNome_especializacao(String nome_especializacao) {
		this.nome_especializacao = nome_especializacao;
	}

}
