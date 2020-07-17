package com.senes.senesapp.tools.pagamento.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cartao_credito")
public class CartaoCredito extends Cartao{

	@Column(length = 3)
	private String cvv;

	public CartaoCredito(String name, String numero, LocalDate dataValidade, String cvv) {
		super(name, numero, dataValidade);
		this.cvv = cvv;
	}

	public CartaoCredito() {
		
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "CartaoCredito [cvv=" + cvv + "]";
	}
	
}
