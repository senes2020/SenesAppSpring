package com.senes.senesapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_companheiro")
public class Companheiro implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	//Versão com tabela de usuários
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "idUser",referencedColumnName = "id")
	private User user;
	
	@NotNull
	@Size(min=3, max=100, message="Por favor, digite um nome com pelo menos 3 caracteres.")
	private String nome;
	
	@NotNull
	private String celular;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
