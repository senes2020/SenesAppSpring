package com.senes.senesapp.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senes.senesapp.dto.BeneficiarioDTO;
import com.senes.senesapp.model.Beneficiario;
import com.senes.senesapp.model.BeneficiarioCredentials;
import com.senes.senesapp.model.User;
import com.senes.senesapp.model.UserCredentials;
import com.senes.senesapp.repository.BeneficiarioRepository;
import com.senes.senesapp.repository.UserRepository;
import com.senes.senesapp.tools.email.send.Mailer;
import com.senes.senesapp.tools.email.send.Mensagem;

@RestController
@RequestMapping("/senes/beneficiario")
@CrossOrigin
public class BeneficiarioResource {
	
	@Autowired(required=true)
	BeneficiarioRepository beneficiarioRepository;
	
	@Autowired(required=true)
	UserRepository userRepository;
	
	@Autowired
	Mailer mailer;
	
	//VISUALIZAÇÃO DE BENEFICIÁRIO	
	@GetMapping("/codigo/usuario/{idUsuario}")
	public ResponseEntity<?> buscarDadosBeneficiario(@PathVariable Long idUsuario){
	
		Optional beneficiarioConsultado = beneficiarioRepository.findByUser_Id(idUsuario);
				
		BeneficiarioDTO beneficiarioDto = new BeneficiarioDTO();
		
		if(beneficiarioConsultado.isPresent()) {
			
			Beneficiario beneficiario = (Beneficiario) beneficiarioConsultado.get();
			
			Optional usuarioConsultado = userRepository.findById(idUsuario);
			User usuario = (User) usuarioConsultado.get();
			
			beneficiarioDto.setId(beneficiario.getId());
			beneficiarioDto.setIdUsuario(beneficiario.getIdUser());
			beneficiarioDto.setCelular(beneficiario.getCelular());
			beneficiarioDto.setNome(beneficiario.getNome());
			beneficiarioDto.setEmail(usuario.getEmail());
			
		}
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return beneficiarioConsultado.isPresent() ? ResponseEntity.ok(beneficiarioDto) : ResponseEntity.notFound().build();
		
	}
		
	//ROTAS DE CADASTRO
	
	//Código que cadastra um beneficiário se houver um código verificado com esse CPF no usuário
	@PostMapping("/registrar/")
	public  ResponseEntity<?> cadastrarBeneficiario(@RequestBody BeneficiarioCredentials beneficiarioEnviado) {
		
		//Criação de objeto que receberá os detalhes de um novo beneficiário, se tiver o usuário confirmado
		Beneficiario beneficiarioNovo = new Beneficiario();
		
		//Criação de objeto para retorno de dados, caso seja cadastrado com sucesso
		BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
		
		//Criação de booleano para representação de conclusão
		Boolean cadastrado = false;
		
		//Recebimento do código e confirmação do mesmo na tabela de usuários
		int codigo = beneficiarioEnviado.getCodigo();
		
		Optional usuarioConfirmado = userRepository.findByCodigo(codigo);
		
		User usuario = new User();
		
		//Confirmando se existe um usuário que esteja com esse código
		if(usuarioConfirmado.isPresent()) {
			
			//Caso tenha um usuário confirmado, coleta o mesmo
			usuario = (User) usuarioConfirmado.get();
			
			//Confirmando a verificação de email
			if(usuario.getCpf().equals(beneficiarioEnviado.getCpf())) {
				
				//Caso corresponda, significa que houve a verificação do emial
				usuario.setFlgEmailVerificado(1);
				usuario = userRepository.save(usuario);
			}
			
			//Se retornar um usuário e ele for um beneficiário e tiver um email confirmado
			//Alimenta o objeto Beneficiário e o salva
			//Depois alimenta o objeto DTO de retorno
			if(usuario.getFlgBeneficiario() == 1 && usuario.getFlgEmailVerificado() == 1) {
				
				beneficiarioNovo.setUser(usuario);
				beneficiarioNovo.setCelular(beneficiarioEnviado.getCelular());
				beneficiarioNovo.setNome(beneficiarioEnviado.getNome());
				
				beneficiarioNovo = beneficiarioRepository.save(beneficiarioNovo);
				
				//retornar nome e email do usuário, somente
				beneficiarioDTO.setEmail(usuario.getEmail());
				beneficiarioDTO.setNome(beneficiarioNovo.getNome());
				
				cadastrado = true;
				
			}
		}
		
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return cadastrado ? ResponseEntity.ok(beneficiarioDTO) : ResponseEntity.notFound().build();
		
	}
	
	
}























