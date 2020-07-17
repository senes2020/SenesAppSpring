package com.senes.senesapp.tools.pagamento.servico;

import java.util.Optional;

import com.senes.senesapp.dto.BeneficiarioDTO;
import com.senes.senesapp.model.Beneficiario;
import com.senes.senesapp.tools.pagamento.dto.BeneficiarioPaganteDTO;

public interface BeneficiarioPaganteService {
	
	public Beneficiario buscarBeneficiario(BeneficiarioPaganteDTO beneficiarioPagante);

}
