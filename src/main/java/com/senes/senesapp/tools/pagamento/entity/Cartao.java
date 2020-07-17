package com.senes.senesapp.tools.pagamento.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_cartao;
	private String nome;
	private String numero;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_validade;
	@Enumerated(EnumType.STRING)
	private TipoBandeira tipo_bandeira;
	
	public Cartao(String name, String numero, LocalDate dataValidade) {
		super();
		this.nome = name;
		this.numero = numero;
		this.data_validade = dataValidade;
	}

	public Cartao() {

	}
	
	public Long getId_cartao() {
		return id_cartao;
	}
	public void setId_cartao(Long id_cartao) {
		this.id_cartao = id_cartao;
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
	public TipoBandeira getTipo_bandeira() {
		return tipo_bandeira;
	}
	public void setTipo_bandeira(TipoBandeira tipo_bandeira) {
		this.tipo_bandeira = tipo_bandeira;
	}
	
	@Override
	public String toString() {
		return "Cartao [nome=" + nome + ", numero=" + numero + ", data_validade=" + data_validade + "]";
	}
		
}
