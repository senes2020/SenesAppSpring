package com.senes.senesapp.tools.pagamento.servico;

import java.time.LocalDate;

import com.senes.senesapp.tools.pagamento.entity.CartaoCredito;
import com.senes.senesapp.tools.pagamento.entity.TipoBandeira;

public interface CartaoService {
	
	public boolean validarCartao(CartaoCredito cartaoCredito);

	public boolean validarNumeroCartao(String numCartao);

	public boolean validarCvvCartao(String cvv);

	public boolean validarDataValidade(LocalDate dataValidade);

	public TipoBandeira identificarBandeira(String numCartao);

	public CartaoCredito salvarCartao(CartaoCredito cartaoCredito);
	
}
