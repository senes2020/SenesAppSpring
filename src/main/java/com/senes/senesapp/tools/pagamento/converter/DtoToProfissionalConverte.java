package com.senes.senesapp.tools.pagamento.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.senes.senesapp.model.Companheiro;
import com.senes.senesapp.tools.pagamento.dto.ProfissionalDTO;
import com.senes.senesapp.tools.pagamento.entity.Profissional;
import com.senes.senesapp.tools.pagamento.exception.ProfissionalNaoEncontradoException;
import com.senes.senesapp.tools.pagamento.servico.ProfissionalService;



@Component
public class DtoToProfissionalConverte implements Converter<ProfissionalDTO, Companheiro>{

	@Autowired
	private ProfissionalService profissionalService;
	
	@Override
	public Companheiro convert(ProfissionalDTO profissionalDTO) {
		Companheiro profissional = profissionalService.buscarProfissional(profissionalDTO);
		if (!ObjectUtils.isEmpty(profissional)) {
			return profissional;
		} else {
			throw new ProfissionalNaoEncontradoException(profissionalDTO.getId_profissional());
		}
	}
	
}
