package com.senes.senesapp.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senes.senesapp.dto.UserAccountCredential;
import com.senes.senesapp.model.User;
import com.senes.senesapp.repository.UserRepository;
//import com.senes.senesapp.security.JwtAuthenticationService;

@RestController
//@RequestMapping("/home")
@RequestMapping("/login")
public class UserAuthentication {
	
	//Busca os dados do usuário no banco
	@Autowired
	private UserRepository userRepository;
	
	//Faz a criação do token pelo método createToken
	//@Autowired
	//private JwtAuthenticationService jwtService;
	
	//Faz a autenticação verificando se é um usuário válido
	//@Autowired
	//private AuthenticationManager authenticationManager;
	
	//Método para logar
	//Aqui será o caminho obrigatório que o usuário deverá seguir quando acessar
	//@PostMapping("/home/login")
	@PostMapping("login/auth")
	public ResponseEntity<Map<Object, Object>> signIn(@RequestBody UserAccountCredential credential) {
		
		//Monta as credenciais do usuário pelo que é enviado no body da requisição
		//UsernamePasswordAuthenticationToken userCredential = new UsernamePasswordAuthenticationToken(credential.getCpfLogin(), "");
		
		//realiza a checagem da autenticação com os dados
		//authenticationManager.authenticate(userCredential);
		
		//Definindo variável de cargos/papéis
		List<String> roles = new ArrayList<String>();
		
		//Bloco para adquirir as funções do usuário
		//Localiza o usuário no banco através do nome e associa as funções do mesmo
		User userLogin = new User();
		userLogin = userRepository.findByCpf(credential.getCpfLogin());
		roles.add(userLogin.getRole());
		
		//Realiza a criação do token utilzando o nome e as funções que serão atribuidas como dados de payload
		//String token = jwtService.createToken(credential.getCpfLogin(), roles);
		
		//Método Map -> registra valores no conjunto chave (key) e valor (value)
		Map<Object, Object> jsonResponse = new HashMap<>();
		jsonResponse.put("cpf", credential.getCpfLogin());
		//jsonResponse.put("token", token);
		
		//Faz o retorno no formato chave/valor das informações relevantes
		return ResponseEntity.ok(jsonResponse);
		
	}

}
