package com.senes.senesapp.tools.pagamento.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.senes.senesapp.tools.pagamento.dto.CartaoDTO;
import com.senes.senesapp.tools.pagamento.entity.CartaoCredito;



@Component
public class DtoToCartaoConverte implements Converter<CartaoDTO, CartaoCredito>{

	@Override
	public CartaoCredito convert(CartaoDTO cartaoDTO) {
		CartaoCredito cartao = new CartaoCredito();
		cartao.setData_validade(cartaoDTO.getData_validade());
		cartao.setNome(cartaoDTO.getNome());
		cartao.setNumero(cartaoDTO.getNumero());
		cartao.setCvv(cartaoDTO.getCvv());
		return cartao;
	}
}
