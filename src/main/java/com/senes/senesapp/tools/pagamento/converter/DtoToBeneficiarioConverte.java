package com.senes.senesapp.tools.pagamento.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.senes.senesapp.model.Beneficiario;
import com.senes.senesapp.tools.pagamento.dto.BeneficiarioPaganteDTO;
import com.senes.senesapp.tools.pagamento.dto.ProfissionalDTO;
import com.senes.senesapp.tools.pagamento.entity.BeneficiarioPagante;
import com.senes.senesapp.tools.pagamento.entity.Profissional;
import com.senes.senesapp.tools.pagamento.exception.BeneficiarioNaoEncontradoException;
import com.senes.senesapp.tools.pagamento.exception.ProfissionalNaoEncontradoException;
import com.senes.senesapp.tools.pagamento.servico.BeneficiarioPaganteService;
import com.senes.senesapp.tools.pagamento.servico.ProfissionalService;



@Component
public class DtoToBeneficiarioConverte implements Converter<BeneficiarioPaganteDTO, Beneficiario>{

	@Autowired
	private BeneficiarioPaganteService beneficiarioService;
	
	@Override
	public Beneficiario convert(BeneficiarioPaganteDTO beneficiarioPaganteDTO) {
		Beneficiario beneficiario = beneficiarioService.buscarBeneficiario(beneficiarioPaganteDTO);
		if (!ObjectUtils.isEmpty(beneficiario)) {
			return beneficiario;
		} else {
			throw new BeneficiarioNaoEncontradoException(beneficiarioPaganteDTO.getId_beneficiario());
		}
	}
	
}
