package com.senes.senesapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senes.senesapp.dto.AgendamentoDTO;
import com.senes.senesapp.model.Agendamento;
import com.senes.senesapp.repository.AgendamentoRepository;

@RestController
@RequestMapping("senes/api")
public class AgendamentoResource {

	@Autowired
	AgendamentoRepository agendamentoRepository;
	
	@PostMapping("/agendamento/cadastro")
	public ResponseEntity<?> agendarEncontro(@RequestBody Agendamento agendamento) {
		
		//Criando objeto de retorno
		AgendamentoDTO agendamentoRegistrado = new AgendamentoDTO();
		
		Boolean cadastrado = false;
		
		try {
			
			//Tentando salvar o novo agendamento
			Agendamento novoAgendamento = agendamentoRepository.save(agendamento);
			
			//Setando dados de retorno
			agendamentoRegistrado.setBairro(novoAgendamento.getBairro());
			agendamentoRegistrado.setCep(novoAgendamento.getCep());
			agendamentoRegistrado.setCidade(novoAgendamento.getCidade());
			agendamentoRegistrado.setComplemento(novoAgendamento.getComplemento());
			agendamentoRegistrado.setData(novoAgendamento.getData());
			agendamentoRegistrado.setHorario_fim(novoAgendamento.getHorario_fim());
			agendamentoRegistrado.setHorario_inicio(novoAgendamento.getHorario_inicio());
			agendamentoRegistrado.setIdPagamento(novoAgendamento.getPagamento().getId_pagamento());
			agendamentoRegistrado.setIdBeneficiario(novoAgendamento.getBeneficiario().getId());
			agendamentoRegistrado.setIdCompanheiro(novoAgendamento.getCompanheiro().getId());
			agendamentoRegistrado.setRua(novoAgendamento.getRua());
			agendamentoRegistrado.setNumero(novoAgendamento.getNumero());
			
			cadastrado = true;
			
		} catch (Exception e) {
		
			cadastrado = false;
		}
		
		//Retorna o agendamento novo caso tenha sido salvo com sucesso, senão retorna o erro de "não encontrado"
		return cadastrado ? ResponseEntity.ok(agendamentoRegistrado) : ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/agendamento/lista/{companheiro}")
	public List<Agendamento> getAgendamentoPorCompanheiro(@PathVariable Long companheiro){
		return agendamentoRepository.findAgendamentoPorIdCompanheiro(companheiro);
	}
	
}
