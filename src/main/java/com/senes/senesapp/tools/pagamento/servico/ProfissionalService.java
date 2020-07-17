package com.senes.senesapp.tools.pagamento.servico;

import com.senes.senesapp.model.Companheiro;
import com.senes.senesapp.tools.pagamento.dto.ProfissionalDTO;
import com.senes.senesapp.tools.pagamento.entity.Profissional;

public interface ProfissionalService {

	public Companheiro buscarProfissional(ProfissionalDTO pofProfissionalDTO);
	
}
