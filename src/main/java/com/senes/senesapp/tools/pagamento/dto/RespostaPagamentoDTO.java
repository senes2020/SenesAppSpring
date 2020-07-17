package com.senes.senesapp.tools.pagamento.dto;

import com.senes.senesapp.tools.pagamento.entity.FormaPagamento;
import com.senes.senesapp.tools.pagamento.entity.Status;

public class RespostaPagamentoDTO {

	private Long id_pagamento;
	private double valor;
	private FormaPagamento forma_pagamento;
	private Status status;
	
	public Long getId_pagamento() {
		return id_pagamento;
	}
	public void setId_pagamento(Long id_pagamento) {
		this.id_pagamento = id_pagamento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public FormaPagamento getForma_pagamento() {
		return forma_pagamento;
	}
	public void setForma_pagamento(FormaPagamento forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "RespostaPagamentoDTO [id_pagamento=" + id_pagamento + ", valor=" + valor + ", forma_pagamento="
				+ forma_pagamento + ", status=" + status + ", getId_pagamento()=" + getId_pagamento() + ", getValor()="
				+ getValor() + ", getForma_pagamento()=" + getForma_pagamento() + ", getStatus()=" + getStatus()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
