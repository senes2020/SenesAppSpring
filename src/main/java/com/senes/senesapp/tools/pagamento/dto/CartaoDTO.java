package com.senes.senesapp.tools.pagamento.dto;

import java.time.LocalDate;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

public class CartaoDTO {

	@NonNull
	private String nome;
	@NotNull
	private String numero;
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_validade;
	@NotNull
	private String cvv;
	
	public CartaoDTO(String nome, String numero, LocalDate data_validade, String cvv) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.data_validade = data_validade;
		this.cvv = cvv;
	}
	
	public CartaoDTO() {

	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LocalDate getData_validade() {
		return data_validade;
	}
	public void setData_validade(LocalDate data_validade) {
		this.data_validade = data_validade;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	@Override
	public String toString() {
		return "CartaoDTO [nome=" + nome + ", numero=" + numero + ", data_validade=" + data_validade + ", cvv=" + cvv
				+ "]";
	}
	
}