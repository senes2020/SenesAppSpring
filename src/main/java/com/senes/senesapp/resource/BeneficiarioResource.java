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
import com.senes.senesapp.model.User;
import com.senes.senesapp.repository.BeneficiarioRepository;
import com.senes.senesapp.repository.UserRepository;
import com.senes.senesapp.tools.email.send.Mailer;
import com.senes.senesapp.tools.email.send.Mensagem;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class BeneficiarioResource {
	
	@Autowired(required=true)
	BeneficiarioRepository beneficiarioRepository;
	
	@Autowired(required=true)
	UserRepository userRepository;
	
	@Autowired
	Mailer mailer;
	
	//ROTAS DE AUTENTICAÇÃO
	@GetMapping("/autenticar/cpf/{cpf}")
	public ResponseEntity<?> autenticarCpf(@PathVariable String cpf){
		//return "Realizando lógica de autenticação ao receber CPF, retornando o envio do código ao email e atualizando o código na tabela";
		
		Optional<Beneficiario> beneficiarioConsultado = beneficiarioRepository.findByCpf(cpf);
		Beneficiario beneficiario = (Beneficiario) beneficiarioConsultado.get();
		
		//Ao invés de retornar o objeto dentista completo, a partir dele são setados
		//os valores para retornar o dentista do DTO
		//que já possui os valores permitidos para retorno
		BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
		
		//Obtendo codigo do beneficiario
		long id = beneficiario.getId();
		
		//Obtendo email do beneficiario
		String email = beneficiario.getEmail();
		
		//Gerar código randômico
		Random rnd = new Random();
		int codigoRand = 10000 + rnd.nextInt(90000);
		
		//Atualizar código no banco desse usuário
		atualizarCodigo(id, codigoRand);
		
		//Enviar código para o email coletado
		enviarCodigoPorEmail(email, codigoRand);
		
		//retornar nome e email do usuário
		beneficiarioDTO.setEmail(beneficiario.getEmail());
		beneficiarioDTO.setNome(beneficiario.getNome());
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return beneficiarioConsultado.isPresent() ? ResponseEntity.ok(beneficiarioDTO) : ResponseEntity.notFound().build();
	}
	
	//Atualização de código de verificação
	private void atualizarCodigo(long id, int codigoRand) {

		//Coleta o beneficiário específico que terá o código atualizado
		Beneficiario beneficiario = beneficiarioRepository.findById(id);
		
		//Atualiza o código nesse objeto
		beneficiario.setCodigo(codigoRand);
		
		//Salva o novo código
		Beneficiario beneficiarioAtualizado = beneficiarioRepository.save(beneficiario);
		
	}
	
	//Código que envia email com código randômico
	private void enviarCodigoPorEmail(String email, int codigoRand) {
		
		mailer.enviar(new Mensagem("SENES APP <senessenai@gmail.com>", 
				Arrays.asList("SENES APP <" + email + ">")
				, "Olá, trouxe seu código ^^ SENES", "Olá! Vi que está tentando acessar sua conta! \n\n Seu código de acesso é: "+ codigoRand +"!"));
		
	}

	@GetMapping("/autenticar/codigo/{codigo}")
	public ResponseEntity<?> autenticarCodigo(@PathVariable int codigo){
		//return "Realizando lógica de autenticação ao receber o código, retornando o usuário completo caso esteja ok";
		
		Optional beneficiarioConsultado = beneficiarioRepository.findByCodigo(codigo);
		Beneficiario beneficiario = (Beneficiario) beneficiarioConsultado.get();
		
		//Ao invés de retornar o objeto dentista completo, a partir dele são setados
		//os valores para retornar o dentista do DTO
		//que já possui os valores permitidos para retorno
		BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
		beneficiarioDTO.setEmail(beneficiario.getEmail());
		beneficiarioDTO.setNome(beneficiario.getNome());
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return beneficiarioConsultado.isPresent() ? ResponseEntity.ok(beneficiarioDTO) : ResponseEntity.notFound().build();
		
	}
	
	//ROTAS DE CADASTRO
	@PostMapping("/registrar")
	public  ResponseEntity<?> cadastrarBeneficiario(@RequestBody Beneficiario beneficiario) {
		
		Beneficiario beneficiarioNovo = beneficiarioRepository.save(beneficiario);
		
		//Ao invés de retornar o objeto dentista completo, a partir dele são setados
		//os valores para retornar o dentista do DTO
		//que já possui os valores permitidos para retorno
		BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
		
		if( beneficiarioNovo != null) {
			
			//Obtendo email do beneficiario
			String email = beneficiarioNovo.getEmail();
			Long id = beneficiarioNovo.getId();
			
			//Gerar código randômico
			Random rnd = new Random();
			int codigoRand = 10000 + rnd.nextInt(90000);
			
			//Atualizar código no banco desse usuário
			atualizarCodigo(id, codigoRand);
			
			//Enviar código para o email coletado
			enviarCodigoPorEmail(email, codigoRand);
			
			//retornar nome e email do usuário
			beneficiarioDTO.setEmail(beneficiarioNovo.getEmail());
			beneficiarioDTO.setNome(beneficiarioNovo.getNome());
			
		}
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return beneficiarioNovo != null ? ResponseEntity.ok(beneficiarioDTO) : ResponseEntity.notFound().build();
		
	}
}























