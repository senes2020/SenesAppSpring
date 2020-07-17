package com.senes.senesapp.tools.pagamento.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.senes.senesapp.tools.pagamento.dto.PagamentoDTO;
import com.senes.senesapp.tools.pagamento.entity.FormaPagamento;
import com.senes.senesapp.tools.pagamento.entity.Pagamento;
import com.senes.senesapp.tools.pagamento.exception.CartaoNuloException;



@Component
public class DtoToPagamentoConverte implements Converter<PagamentoDTO, Pagamento>{
	
	@Autowired
	private DtoToCartaoConverte cartaoConverte;

	@Override
	public Pagamento convert(PagamentoDTO pagamentoDTO) {
		Pagamento pagamento = new Pagamento();
		if (FormaPagamento.CARTAO_CREDITO.equals(pagamentoDTO.getForma())) {
			if (ObjectUtils.isEmpty(pagamentoDTO.getCartao())) {
				throw new CartaoNuloException();
			}
			pagamento.setCartao(cartaoConverte.convert(pagamentoDTO.getCartao()));
		}

		pagamento.setForma(pagamentoDTO.getForma());
		pagamento.setValor(pagamentoDTO.getValor());
		return pagamento;
	}
	
}
