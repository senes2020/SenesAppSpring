package com.senes.senesapp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senes.senesapp.tools.pagamento.entity.Pagamento;
import com.sun.istack.NotNull;

@Entity
@Table(name="tbl_agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_agendamento;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@NotNull
	private String horario_inicio;
	
	@NotNull
	private String horario_fim;
	
	@NotNull
	private String cep;
	
	@NotNull
	private String rua;
	
	@NotNull
	private String bairro;
	
	@NotNull
	private String cidade;
	
	@NotNull
	private String numero;
	
	@NotNull
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name = "id_companheiro", referencedColumnName = "id")
	private Companheiro companheiro;
	
	@ManyToOne
	@JoinColumn(name = "id_beneficiario", referencedColumnName = "id")
	private Beneficiario beneficiario;
	
	@ManyToOne
	@JoinColumn(name = "id_pagamento", referencedColumnName = "id_pagamento")
	private Pagamento pagamento;

	public long getId_agendamento() {
		return id_agendamento;
	}



	public void setId_agendamento(long id_agendamento) {
		this.id_agendamento = id_agendamento;
	}



	public LocalDate getData() {
		return data;
	}



	public void setData(LocalDate data) {
		this.data = data;
	}



	public String getHorario_inicio() {
		return horario_inicio;
	}



	public void setHorario_inicio(String horario_inicio) {
		this.horario_inicio = horario_inicio;
	}



	public String getHorario_fim() {
		return horario_fim;
	}



	public void setHorario_fim(String horario_fim) {
		this.horario_fim = horario_fim;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getRua() {
		return rua;
	}



	public void setRua(String rua) {
		this.rua = rua;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getComplemento() {
		return complemento;
	}



	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



	public Companheiro getCompanheiro() {
		return companheiro;
	}



	public void setCompanheiro(Companheiro companheiro) {
		this.companheiro = companheiro;
	}



	public Beneficiario getBeneficiario() {
		return beneficiario;
	}



	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}



	public Pagamento getPagamento() {
		return pagamento;
	}



	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}



	@Override
	public String toString() {
		return "Agendamento [id_agendamento=" + id_agendamento + ", data=" + data + ", horario_inicio=" + horario_inicio
				+ ", horario_fim=" + horario_fim + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade="
				+ cidade + ", numero=" + numero + ", complemento=" + complemento + ", companheiro=" + companheiro
				+ ", beneficiario=" + beneficiario + ", pagamento=" + pagamento + "]";
	}
	
}

