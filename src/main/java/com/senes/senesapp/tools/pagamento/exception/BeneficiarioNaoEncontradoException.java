package com.senes.senesapp.tools.pagamento.exception;

public class BeneficiarioNaoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private Long id_beneficiario;

	public BeneficiarioNaoEncontradoException(Long id_beneficiario) {
		super();
		this.id_beneficiario = id_beneficiario;
	}

	public Long getId_beneficiario() {
		return id_beneficiario;
	}

	public void setId_beneficiario(Long id_beneficiario) {
		this.id_beneficiario = id_beneficiario;
	}
}
