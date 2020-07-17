package com.senes.senesapp.tools.pagamento.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senes.senesapp.tools.pagamento.entity.Dinheiro;
import com.senes.senesapp.tools.pagamento.repository.PagDinheiroRepository;
import com.senes.senesapp.tools.pagamento.servico.DinheiroService;

@Service
public class PagDinheiroServiceImpl implements DinheiroService{

	@Autowired
	private PagDinheiroRepository pagDinheiroRepository;
	
	@Override
	public Dinheiro salvarExtrato(Dinheiro dinheiro) {
		return pagDinheiroRepository.save(dinheiro);
	}
	
}
