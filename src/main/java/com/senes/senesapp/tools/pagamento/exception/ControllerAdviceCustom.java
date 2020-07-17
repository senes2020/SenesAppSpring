package com.senes.senesapp.tools.pagamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.senes.senesapp.tools.pagamento.entity.CartaoCredito;


@ControllerAdvice
public class ControllerAdviceCustom {

	@ExceptionHandler(ProfissionalNaoEncontradoException.class)
	public ResponseEntity<Message<Long>> autorNaoEncontradoException(ProfissionalNaoEncontradoException ex) {
		Message<Long> mensagem = new Message<>("Profissional não encontrado", ex.getId_profissional());
		return new ResponseEntity<Message<Long>>(mensagem, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BeneficiarioNaoEncontradoException.class)
	public ResponseEntity<Message<Long>> beneficiarioNaoEncontradoException(BeneficiarioNaoEncontradoException ex) {
		Message<Long> mensagem = new Message<>("Beneficiário não encontrado", ex.getId_beneficiario());
		return new ResponseEntity<Message<Long>>(mensagem, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CartaoInvalidoException.class)
	public ResponseEntity<Message<CartaoCredito>> cartaoInvalidoException(CartaoInvalidoException ex) {
		Message<CartaoCredito> mensagem = new Message<>("Ops... Cartão de crédito inválido", null);
		return new ResponseEntity<Message<CartaoCredito>>(mensagem, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CartaoNuloException.class)
	public ResponseEntity<Message<Void>> cartaoNuloException() {
		Message<Void> mensagem = new Message<>("Por favor, informe os dados do cartão", null);
		return new ResponseEntity<Message<Void>>(mensagem, HttpStatus.BAD_REQUEST);
	}
}
