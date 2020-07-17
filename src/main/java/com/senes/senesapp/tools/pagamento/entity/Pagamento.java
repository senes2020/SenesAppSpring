package com.senes.senesapp.tools.pagamento.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.senes.senesapp.model.Beneficiario;
import com.senes.senesapp.model.Companheiro;

@Entity
@Table(name = "tbl_pagamento")
@JsonInclude(Include.NON_NULL)
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_pagamento;
	private Double valor;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_cadastro;
	@Enumerated(EnumType.STRING)
	private FormaPagamento forma;
	@OneToOne
	@JoinColumn(name = "id_cartao")
	private Cartao cartao;
	@OneToOne
	@JoinColumn(name = "id_pag_dinheiro")
	private Dinheiro dinheiro;
	@OneToOne
	@JoinColumn(name = "id_profissional", referencedColumnName = "id")
	//private Profissional profissional;
	private Companheiro profissional;
	@OneToOne
	@JoinColumn(name = "id_beneficiario", referencedColumnName = "id")
	//private BeneficiarioPagante beneficiario;
	private Beneficiario beneficiario;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Pagamento(Long id_pagamento, Double valor, LocalDate data_cadastro, FormaPagamento forma, Cartao cartao,
			Dinheiro dinheiro, Companheiro profissional, Beneficiario beneficiario, Status status) {
		super();
		this.id_pagamento = id_pagamento;
		this.valor = valor;
		this.data_cadastro = data_cadastro;
		this.forma = forma;
		this.cartao = cartao;
		this.dinheiro = dinheiro;
		this.profissional = profissional;
		this.beneficiario = beneficiario;
		this.status = status;
	}
	
	public Pagamento() {

	}
	
	public Long getId_pagamento() {
		return id_pagamento;
	}
	public void setId_pagamento(Long id_pagamento) {
		this.id_pagamento = id_pagamento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public LocalDate getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(LocalDate data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public FormaPagamento getForma() {
		return forma;
	}
	public void setForma(FormaPagamento forma) {
		this.forma = forma;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public Dinheiro getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(Dinheiro dinheiro) {
		this.dinheiro = dinheiro;
	}
	public Companheiro getProfissional() {
		return profissional;
	}
	public void setProfissional(Companheiro profissional) {
		this.profissional = profissional;
	}
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
