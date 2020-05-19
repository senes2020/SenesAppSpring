package com.senes.senesapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="tbl_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private Integer flgBeneficiario;
	private Integer flgCompanheiro;
	private Integer codigo;
	private String role;
	private Integer flgEmailVerificado;
	
	@Email(message="Por favor, informar um email válido.")
	private String email;
	
	//Construtor vazio padrão
	public User() {}

	//Construtor para autenticação
	public User(String cpf, String email, Integer flgBeneficiario, Integer flgCompanheiro, Integer codigo, String role,
			Integer flgEmailVerificado) {
		super();
		this.cpf = cpf;
		this.email = email;
		this.flgBeneficiario = flgBeneficiario;
		this.flgCompanheiro = flgCompanheiro;
		this.codigo = codigo;
		this.role = role;
		this.flgEmailVerificado = flgEmailVerificado;
	}
	
	public Long getId() {
		return id;
	}

	public Integer getFlgBeneficiario() {
		return flgBeneficiario;
	}

	public void setFlgBeneficiario(Integer flgBeneficiario) {
		this.flgBeneficiario = flgBeneficiario;
	}

	public Integer getFlgCompanheiro() {
		return flgCompanheiro;
	}

	public void setFlgCompanheiro(Integer flgCompanheiro) {
		this.flgCompanheiro = flgCompanheiro;
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
	}
	
	
}
