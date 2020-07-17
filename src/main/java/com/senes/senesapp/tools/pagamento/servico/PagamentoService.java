package com.senes.senesapp.tools.pagamento.servico;

import com.senes.senesapp.tools.pagamento.dto.RequisicaoPagamentoDTO;
import com.senes.senesapp.tools.pagamento.dto.RespostaPagamentoDTO;
import com.senes.senesapp.tools.pagamento.entity.Pagamento;

public interface PagamentoService {
	
	public RespostaPagamentoDTO realizarPagamento(RequisicaoPagamentoDTO requisicaoPagamentoDTO);

	public Pagamento buscarPagamento(Long idPagamento);
	
	public String esconderCPF(String cpf);
	
	public boolean removerPagamento(Long idPagamento);

}