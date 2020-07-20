package com.senes.senesapp.dto;

import java.time.LocalDate;

public class AgendamentoDTO {
	
	private LocalDate data;
	private String horario_inicio;
	private String horario_fim;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String numero;
	private String complemento;
	private long idCompanheiro;
	private long idBeneficiario;
	private long idPagamento;
	
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
	public long getIdCompanheiro() {
		return idCompanheiro;
	}
	public void setIdCompanheiro(long idCompanheiro) {
		this.idCompanheiro = idCompanheiro;
	}
	public long getIdBeneficiario() {
		return idBeneficiario;
	}
	public void setIdBeneficiario(long idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}
	public long getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(long idPagamento) {
		this.idPagamento = idPagamento;
	}
	
	
}
