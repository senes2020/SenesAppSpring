package com.senes.senesapp.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senes.senesapp.model.AtualizacaoUsuario;
import com.senes.senesapp.model.Beneficiario;
import com.senes.senesapp.model.Companheiro;
import com.senes.senesapp.model.NumerosUsuario;
import com.senes.senesapp.model.User;
import com.senes.senesapp.model.UserCredentials;
import com.senes.senesapp.repository.BeneficiarioRepository;
import com.senes.senesapp.repository.CompanheiroRepository;
import com.senes.senesapp.repository.UserRepository;
import com.senes.senesapp.tools.email.send.Mailer;
import com.senes.senesapp.tools.email.send.Mensagem;

@RestController
@RequestMapping("/senes/usuario")
@CrossOrigin
public class UsuarioResource {
	
	@Autowired(required=true)
	BeneficiarioRepository beneficiarioRepository;
	
	@Autowired(required=true)
	UserRepository userRepository;
	
	@Autowired(required=true)
	CompanheiroRepository companheiroRepository;
	
	@Autowired
	Mailer mailer;
	
	//ROTAS DE AUTENTICAÇÃO
	//Autenticação de um usuário pelo seu CPF
	@GetMapping("/autenticar/cpf/{cpf}")
	public ResponseEntity<?> autenticarUsuarioCpf(@PathVariable String cpf){
		
		//Verificando se já existe um usuário com esse CPF
		Optional<User> retorno = Optional.ofNullable(userRepository.findByCpf(cpf));
		
		User usuario = new User();
		
		//Se tiver retornado algum usuário, significa que existe um usuário salvo como companheiro (trabalhador)
		if(retorno.isPresent()){
			
			//Coletando o usuário encontrado
		    usuario = retorno.get();
			
			//Obtendo codigo do usuario
			long id = usuario.getId();
			
			//Obtendo email do usuario
			String email = usuario.getEmail();
			
			//Gerar código randômico
			Random rnd = new Random();
			int codigoRand = 10000 + rnd.nextInt(90000);
			
			//Atualizar código no banco desse usuário
			atualizarCodigo(id, codigoRand);
			
			//Enviar código para o email coletado
			enviarCodigoPorEmail(email, codigoRand);
		}
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return retorno.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
	
	//Atualização de código de verificação de um usuário
	private void atualizarCodigo(long id, int codigoRand) {

		//Versão considerando usuários
		
		//Coleta o beneficiário específico que terá o código atualizado
		Optional<User> usuario = userRepository.findById(id);
		
		User usuarioEncontrado = new User();
		
		if(usuario.isPresent()) {
			
			//Coletando o usuário encontrado
			usuarioEncontrado = usuario.get();
			
			//Atualiza o código nesse objeto
			usuarioEncontrado.setCodigo(codigoRand);
		}
		
		
		//Salva o novo código
		userRepository.save(usuarioEncontrado);
		
	}
	
	//Código que envia email com código randômico
	private void enviarCodigoPorEmail(String email, int codigoRand) {
		
		mailer.enviar(new Mensagem("SENES APP <senessenai@gmail.com>", 
				Arrays.asList("SENES APP <" + email + ">")
				, "Olá, trouxe seu código ^^ SENES", "Olá! Vi que está tentando acessar sua conta! "
						+ "\n\n Seu código de acesso é: "+ codigoRand +"!"));
		
	}
	
	//Realiza a autenticação de um usuário existente
	@GetMapping("/autenticar/codigo/{codigo}")
	public ResponseEntity<?> autenticarUsuarioCodigo(@PathVariable int codigo){
		//return "Realizando lógica de autenticação ao receber o código, retornando o usuário completo caso esteja ok";
		
		Optional usuarioConsultado = userRepository.findByCodigo(codigo);
		
		User usuario = new User();
		
		if(usuarioConsultado.isPresent()) {
			
			usuario = (User) usuarioConsultado.get();
			
			//Caso o email não esteja verificado, setar verificação
			if(usuario.getFlgEmailVerificado() == 0) {
				usuario.setFlgEmailVerificado(1);
				usuario = userRepository.save(usuario);
			}
		}
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return usuarioConsultado.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
		
	}
	
	//ROTAS DE CADASTRO
	//Código que salva um novo usuário e envia email de confirmação
	@PostMapping("/registrar/")
	public void cadastrarUsuario(@RequestBody UserCredentials userCredentials) {
		
		//Coletando dados enviados
		String cpfEnviado = userCredentials.getCpfCadastro();
		String emailEnviado = userCredentials.getEmailCadastro();
		
		//Verificando se já existe um usuário com esse CPF
		Optional<User> retorno = Optional.ofNullable(userRepository.findByCpf(cpfEnviado));
		//Optional<User> usuarioEncontrado = userRepository.findByCpf(cpfEnviado);
		//User usuario = usuarioEncontrado.get();
		
		//Se tiver retornado algum usuário, significa que existe um usuário salvo como companheiro (trabalhador)
		if(retorno.isPresent()){
			
			//Coletando o usuário encontrado
			User usuario = retorno.get();
			
			//Se ele for um beneficiário com email confirmado, avisar que já existe conta
			if(usuario.getFlgBeneficiario() == 1 && usuario.getFlgEmailVerificado() == 1){
				
				//Enviar mensagem dizendo que já existe uma conta de beneficiário
				
			}else{
				
				//Obtendo dados do usuário
				Long id = usuario.getId();
				
				//Gerar código randômico
				Random rnd = new Random();
				int codigoRand = 10000 + rnd.nextInt(90000);
				
				//Atualizar código no banco desse usuário
				atualizarCodigo(id, codigoRand);
				
				//Enviar código para o email coletado
				enviarCodigoPorEmail(emailEnviado, codigoRand);
				
			}
			
		//Caso o usuário seja nulo, deve criar um novo e enviar código de confirmação para email
		}else{
			
			//Gerar código randômico
			Random rnd = new Random();
			int codigoRand = 10000 + rnd.nextInt(90000);
			
			//Definindo valores para construtor de um novo usuário
			Integer flgBeneficiario = 1;
			Integer flgCompanheiro = 0;
			String role = "";
			Integer flgEmailVerificado = 0;
			
			//Definindo um usuário novo
			User usuarioNovo = new User(cpfEnviado, emailEnviado, flgBeneficiario, flgCompanheiro, codigoRand, role, flgEmailVerificado);
			
			//Salvando o novo usuário
			userRepository.save(usuarioNovo);
			
			//Enviar código para o email coletado
			enviarCodigoPorEmail(emailEnviado, codigoRand);
			
		}
		
	}
	
	//ROTAS DE ATUALIZAÇÃO
	
	//Caso o código de email seja compatível com algum usuário
	//atualiza o mesmo com o novo email
	@PutMapping("/atualizar/email/codigo/{codigoConfirmacaoEmail}")
	public ResponseEntity<?> atualizarEmail(@PathVariable int codigoConfirmacaoEmail, @RequestBody UserCredentials userCredentials){
		//return "Realizando lógica de autenticação ao receber o código, retornando o usuário completo caso esteja ok";
		
		Optional<User> usuarioConsultado = userRepository.findByCodigo(codigoConfirmacaoEmail);
		
		User usuario = new User();
		
		String novoEmail = userCredentials.getEmailCadastro();
		
		if(usuarioConsultado.isPresent()) {
			
			//Coletando o usuário encontrado
			//atualizando novo email e zerando a verificação
			usuario = (User) usuarioConsultado.get();
			usuario.setEmail(novoEmail);
			usuario.setFlgEmailVerificado(1);
			usuario = userRepository.save(usuario);
			
		}
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return usuarioConsultado.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
		
	}
	
	//Método que recebe um email novo do usuário
	//e envia um código de verificação para que futuramente será alterado
	@PutMapping("/atualizar/email/{codigoUsuario}")
	public ResponseEntity<?> enviarCodigoEmailNovo(@PathVariable int codigoUsuario, @RequestBody AtualizacaoUsuario usuarioAtualizado){
		//return "Realizando lógica de autenticação ao receber o código, retornando o usuário completo caso esteja ok";
		
		Optional<User> usuarioConsultado = userRepository.findById(codigoUsuario);
		
		User usuario = new User();
		
		String novoEmail = usuarioAtualizado.getEmailAtualizacao();
		
		if(usuarioConsultado.isPresent()) {
			
			//Coletando o usuário encontrado
			//atualizando novo email e zerando a verificação
			usuario = (User) usuarioConsultado.get();
			
			//Gerar código randômico
			Random rnd = new Random();
			int codigoRand = 10000 + rnd.nextInt(90000);
			
			//Atualizar código no banco desse usuário
			atualizarCodigo(codigoUsuario, codigoRand);
			
			//Enviar código para o email coletado
			enviarCodigoPorEmail(novoEmail, codigoRand);
		}
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return usuarioConsultado.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
		
	}
	
	
	
	@PutMapping("/atualizar/codigo/{codigoUsuario}")
	public ResponseEntity<?> enviarCodigoEmailNovoPorNumero(@PathVariable int codigoUsuario, @RequestBody AtualizacaoUsuario usuarioAtualizado){
		//return "Realizando lógica de autenticação ao receber o código, retornando o usuário completo caso esteja ok";
		
		Optional<User> usuarioConsultado = userRepository.findById(codigoUsuario);
		
		User usuario = new User();
		
		String numero = usuarioAtualizado.getNumeroAtualizacao();
		
		String novoEmail = usuarioAtualizado.getEmailAtualizacao();
		
		Boolean numeroCompativel = false;
		Boolean numeroCompativelB = false;
		Boolean numeroCompativelC = false;
		
		String numeroBeneficiario;
		
		String numeroCompanheiro;
		
		Beneficiario beneficiario = new Beneficiario();
		Optional beneficiarioProcurado = beneficiarioRepository.findByUser_Id(codigoUsuario);
		
		Companheiro companheiro = new Companheiro();
		Optional companheiroProcurado = companheiroRepository.findByUser_Id(codigoUsuario);
		
		if(usuarioConsultado.isPresent()) {
			
			usuario = usuarioConsultado.get();
			
			if(beneficiarioProcurado.isPresent()) {
				beneficiario = (Beneficiario) beneficiarioProcurado.get();
				numeroBeneficiario = beneficiario.getCelular();
				
				if(numeroBeneficiario.equals(numero)) {
					numeroCompativelB = true;
				}
				
			}
			
			if(companheiroProcurado.isPresent()) {
				companheiro = (Companheiro) companheiroProcurado.get();
				numeroCompanheiro = companheiro.getCelular();
				
				if(numeroCompanheiro.equals(numero)) {
					numeroCompativelC = true;
				}
			}
			
			if(numeroCompativelB || numeroCompativelC){
				
				numeroCompativel = true;
				
				//Gerar código randômico
				Random rnd = new Random();
				int codigoRand = 10000 + rnd.nextInt(90000);
				
				//Atualizar código no banco desse usuário
				atualizarCodigo(codigoUsuario, codigoRand);
				
				//Enviar código para o email coletado
				enviarCodigoPorEmail(novoEmail, codigoRand);
				
			}
			
		}
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return numeroCompativel ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
		
	}
	
	//ROTAS DE COLETA DE DADOS
	//Realiza a autenticação de um usuário existente
	@GetMapping("/celulares/{codigoUsuario}")
	private ResponseEntity<?> encontrarCelularesRelacionados(@PathVariable long codigoUsuario) {

		Optional beneficiarioProcurado = beneficiarioRepository.findByUser_Id(codigoUsuario);
		
		Optional companheiroProcurado = companheiroRepository.findByUser_Id(codigoUsuario);
		
		NumerosUsuario celulares = new NumerosUsuario();
		
		Beneficiario beneficiario = new Beneficiario();
		
		Companheiro companheiro = new Companheiro();
		
		//Se retornar um beneficiário, coleta o número de telefone do mesmo
		if(beneficiarioProcurado.isPresent()) {
			beneficiario = (Beneficiario) beneficiarioProcurado.get();
			String celularBeneficiario = "(**)*****-**" + beneficiario.getCelular().substring(9, 11);
			celulares.setCelularBeneficiario(celularBeneficiario);
		}
		
		//Se retornar um beneficiário, coleta o número de telefone do mesmo
		if(companheiroProcurado.isPresent()) {
			companheiro = (Companheiro) companheiroProcurado.get();
			String celularCompanheiro = "(**)*****-**" + companheiro.getCelular().substring(9, 11);
			celulares.setCelularCompanheiro(celularCompanheiro);
		}
			
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return beneficiarioProcurado.isPresent() || companheiroProcurado.isPresent() ? ResponseEntity.ok(celulares) : ResponseEntity.notFound().build();
	}

}
