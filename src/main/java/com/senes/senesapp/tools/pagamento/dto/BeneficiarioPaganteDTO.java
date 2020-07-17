package com.senes.senesapp.tools.pagamento.dto;

import com.sun.istack.NotNull;

public class BeneficiarioPaganteDTO {

	@NotNull
	private Long id_beneficiario;
	
	public BeneficiarioPaganteDTO(Long id_beneficiario) {
		super();
		this.id_beneficiario = id_beneficiario;
	}

	public BeneficiarioPaganteDTO() {
	
	}
	public Long getId_beneficiario() {
		return id_beneficiario;
	}

	public void setId_beneficiario(Long id_beneficiario) {
		this.id_beneficiario = id_beneficiario;
	}

	@Override
	public String toString() {
		return "BeneficiarioPaganteDTO [codigo=" + id_beneficiario+ "]";
	}

}
