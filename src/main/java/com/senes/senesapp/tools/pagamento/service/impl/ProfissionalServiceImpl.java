package com.senes.senesapp.tools.pagamento.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senes.senesapp.model.Companheiro;
import com.senes.senesapp.repository.CompanheiroRepository;
import com.senes.senesapp.tools.pagamento.dto.ProfissionalDTO;
import com.senes.senesapp.tools.pagamento.entity.Profissional;
import com.senes.senesapp.tools.pagamento.repository.ProfissionalRepository;
import com.senes.senesapp.tools.pagamento.servico.ProfissionalService;

@Service
public class ProfissionalServiceImpl implements ProfissionalService{

	@Autowired
	private CompanheiroRepository profissionalRepository;
	
	@Override
	public Companheiro buscarProfissional(ProfissionalDTO profissionalDTO) {
		return profissionalRepository.findById(profissionalDTO.getId_profissional()).orElse(null);
	}

}