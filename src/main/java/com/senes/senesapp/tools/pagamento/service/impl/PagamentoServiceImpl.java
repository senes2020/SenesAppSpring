package com.senes.senesapp.tools.pagamento.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.senes.senesapp.model.Beneficiario;
import com.senes.senesapp.model.Companheiro;
import com.senes.senesapp.tools.pagamento.dto.RequisicaoPagamentoDTO;
import com.senes.senesapp.tools.pagamento.dto.RespostaPagamentoDTO;
import com.senes.senesapp.tools.pagamento.entity.BeneficiarioPagante;
import com.senes.senesapp.tools.pagamento.entity.CartaoCredito;
import com.senes.senesapp.tools.pagamento.entity.Dinheiro;
import com.senes.senesapp.tools.pagamento.entity.FormaPagamento;
import com.senes.senesapp.tools.pagamento.entity.Pagamento;
import com.senes.senesapp.tools.pagamento.entity.Profissional;
import com.senes.senesapp.tools.pagamento.entity.Status;
import com.senes.senesapp.tools.pagamento.repository.PagamentoRepository;
import com.senes.senesapp.tools.pagamento.servico.BeneficiarioPaganteService;
import com.senes.senesapp.tools.pagamento.servico.CartaoService;
import com.senes.senesapp.tools.pagamento.servico.DinheiroService;
import com.senes.senesapp.tools.pagamento.servico.PagamentoService;


@Service
public class PagamentoServiceImpl implements PagamentoService{

	@Autowired
	private PagamentoRepository pagamentoRepositorio;

	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private BeneficiarioPaganteService beneficiarioPaganteService;

	@Autowired
	private DinheiroService dinheiroService;
	
	@Autowired
	private ConversionService conversionService;
	
	@Override
	public RespostaPagamentoDTO realizarPagamento(RequisicaoPagamentoDTO requisicaoPagamentoDTO) {

		Pagamento pag = conversionService.convert(requisicaoPagamentoDTO.getPagamento(), Pagamento.class);

		// Converte o profissional a ser pago e atribui no pagamento
		//Profissional profissional = conversionService.convert(requisicaoPagamentoDTO.getProfissional(), Profissional.class);
		Companheiro profissional = 
				conversionService.convert(requisicaoPagamentoDTO.getProfissional(), Companheiro.class);
		pag.setProfissional(profissional);

		// Verifica se existe o beneficiario pagante
		//BeneficiarioPagante beneficiarioPagante = beneficiarioPaganteService.buscarBeneficiarioCPF(requisicaoPagamentoDTO.getBeneficiario().getCpf());
		Beneficiario beneficiarioPagante = 
				conversionService.convert(requisicaoPagamentoDTO.getBeneficiario(), Beneficiario.class);
		pag.setBeneficiario(beneficiarioPagante);
		
		/*
		if (!ObjectUtils.isEmpty(beneficiarioPagante)) {
			pag.setBeneficiario(beneficiarioPagante);
		} else {
			BeneficiarioPagante beneficiarioPaganteNovo = conversionService
					.convert(requisicaoPagamentoDTO.getBeneficiario(), BeneficiarioPagante.class);
			pag.setBeneficiario(beneficiarioPaganteService.salvarBeneficiario(beneficiarioPaganteNovo));
		}*/

		// Verifica a forma de pagamento
		if (FormaPagamento.CARTAO_CREDITO.equals(pag.getForma())) {
			CartaoCredito cc = (CartaoCredito) pag.getCartao();
			cartaoService.validarCartao(cc);
			cc.setTipo_bandeira(cartaoService.identificarBandeira(cc.getNumero()));
			pag.setCartao(cartaoService.salvarCartao(cc));
			pag.setStatus(Status.ENVIADO);

		} else if (FormaPagamento.DINHEIRO.equals(pag.getForma())) {
			Dinheiro dinheiro = new Dinheiro();
			pag.setDinheiro(dinheiroService.salvarExtrato(dinheiro));
			pag.setStatus(Status.PROCESSANDO);
		}
		pag.setData_cadastro(LocalDate.now());

		Pagamento pagamento = pagamentoRepositorio.save(pag);

		RespostaPagamentoDTO respostaPagamentoDTO = new RespostaPagamentoDTO();
		respostaPagamentoDTO.setId_pagamento(pagamento.getId_pagamento());
		respostaPagamentoDTO.setValor(pagamento.getValor());
		respostaPagamentoDTO.setForma_pagamento(pagamento.getForma());
		respostaPagamentoDTO.setStatus(pagamento.getStatus());
		
		return respostaPagamentoDTO;
	}

	@Override
	public Pagamento buscarPagamento(Long idPagamento) {
		Pagamento pag = pagamentoRepositorio.findById(idPagamento).orElse(null);
		if (ObjectUtils.isEmpty(pag)) {
			return null;
		}
		/*String cpfNovo = this.esconderCPF(pag.getBeneficiario().getId());
		pag.getBeneficiario().setCpf(cpfNovo);*/
		return pag;
	}

	@Override
	public String esconderCPF(String cpf) {
		if (ObjectUtils.isEmpty(cpf)) {
			return null;
		}
		String cpfNovo = "";
		for (int x = 0; x < cpf.length() - 2; x++) {
			cpfNovo += "*";
		}
		return cpfNovo + cpf.substring(cpf.length() - 2);
	}

	@Override
	public boolean removerPagamento(Long idPagamento) {
		Pagamento pag = pagamentoRepositorio.findById(idPagamento).orElse(null);
		if (ObjectUtils.isEmpty(pag)) {
			return false;
		}
		pagamentoRepositorio.delete(pag);
		return true;
	}

}
