package com.senes.senesapp.tools.pagamento.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senes.senesapp.model.Beneficiario;
import com.senes.senesapp.repository.BeneficiarioRepository;
import com.senes.senesapp.tools.pagamento.dto.BeneficiarioPaganteDTO;
import com.senes.senesapp.tools.pagamento.entity.BeneficiarioPagante;
import com.senes.senesapp.tools.pagamento.repository.BeneficiarioPaganteRepository;
import com.senes.senesapp.tools.pagamento.servico.BeneficiarioPaganteService;



@Service
public class BeneficiarioPaganteServiceImpl implements BeneficiarioPaganteService{

	@Autowired
	private BeneficiarioRepository beneficiarioPaganteRepository;

	@Override
	public Beneficiario buscarBeneficiario(BeneficiarioPaganteDTO beneficiarioPagante) {
		Optional<Beneficiario> beneficiarioProcurado = 
				beneficiarioPaganteRepository.findById(beneficiarioPagante.getId_beneficiario());
		
		return beneficiarioProcurado.isPresent() ? beneficiarioProcurado.get() : null;
		
	}
	
}