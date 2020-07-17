package com.senes.senesapp.tools.pagamento.exception;

public class ProfissionalNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Long id_profissional;

	public ProfissionalNaoEncontradoException(Long id_profissional) {
		super();
		this.id_profissional = id_profissional;
	}

	public Long getId_profissional() {
		return id_profissional;
	}

	public void setId_profissional(Long id_profissional) {
		this.id_profissional = id_profissional;
	}

}
