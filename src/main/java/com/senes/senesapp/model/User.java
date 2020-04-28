package com.senes.senesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String email;
	private Integer codigo;
	private String role;
	private Integer flgEmailVerificado;
	
	//Construtor vazio padrão
	public User() {}

	//Construtor para autenticação
	public User(String cpf, String email, Integer codigo, String role, Integer flgEmailVerificado) {
		super();
		this.cpf = cpf;
		this.email = email;
		this.codigo = codigo;
		this.role = role;
		this.flgEmailVerificado = flgEmailVerificado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getFlgEmailVerificado() {
		return flgEmailVerificado;
	}

	public void setFlgEmailVerificado(Integer flgEmailVerificado) {
		this.flgEmailVerificado = flgEmailVerificado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	};
	
	
	
}
