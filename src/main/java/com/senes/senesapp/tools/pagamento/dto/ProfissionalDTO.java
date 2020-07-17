package com.senes.senesapp.tools.pagamento.dto;

public class ProfissionalDTO {

	private Long id_profissional;

	public ProfissionalDTO() {
		
	}

	public ProfissionalDTO(Long id_profissional) {
		super();
		this.id_profissional = id_profissional;
	}

	public Long getId_profissional() {
		return id_profissional;
	}

	@Override
	public String toString() {
		return "ProfissionalDTO [id_profissional=" + id_profissional + "]";
	}
	
}
