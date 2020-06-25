package com.senes.senesapp.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senes.senesapp.dto.CompanheiroDTO;
import com.senes.senesapp.model.Companheiro;
import com.senes.senesapp.model.User;
import com.senes.senesapp.repository.BeneficiarioRepository;
import com.senes.senesapp.repository.CompanheiroRepository;
import com.senes.senesapp.repository.UserRepository;
import com.senes.senesapp.tools.email.send.Mailer;

@RestController
@RequestMapping("/senes/companheiro")
@CrossOrigin
public class CompanheiroResource {
	
	@Autowired(required=true)
	BeneficiarioRepository beneficiarioRepository;
	
	@Autowired(required=true)
	UserRepository userRepository;
	
	@Autowired(required=true)
	CompanheiroRepository companheiroRepository;
	
	@Autowired
	Mailer mailer;
	
	//LISTANDO TODOS OS PROFISSIONAIS (SEM PAGINAÇÃO ATÉ O MOMENTO)
	@GetMapping
	public List<Companheiro> listaCadastrados(){
		return companheiroRepository.findAll();
	}
	
	//LISTANDO OS PROFISSIONAIS PELO ID
	@GetMapping("/{id}")
	public Optional<Companheiro> listaCadastradoPorId(@PathVariable(value="id") long id) {
		return companheiroRepository.findById(id);
	}
	
	@GetMapping("/especializacoes/{especializacao}")
	public List<Companheiro> getProfissionaisPorEspecializacao(@PathVariable String especializacao){
		return companheiroRepository.findProfissionalPorEspecializacao(especializacao);
	}
	
	@PostMapping("/registrar")
	public Companheiro gravar(@RequestBody Companheiro companheiro) {
		Companheiro novoProfissional = companheiroRepository.save(companheiro);
		return novoProfissional;
	}
	
	//VISUALIZAÇÃO DE COMPANHEIRO	
	@GetMapping("/codigo/usuario/{idUsuario}")
	public ResponseEntity<?> buscarDadosCompanheiro(@PathVariable Long idUsuario){
	
		Optional companheiroProcurado = companheiroRepository.findByUser_Id(idUsuario);
		
		CompanheiroDTO companheiroDTO = new CompanheiroDTO();
		
		if(companheiroProcurado.isPresent()) {
			
			Companheiro companheiro = (Companheiro) companheiroProcurado.get();
			
			Optional usuarioConsultado = userRepository.findById(idUsuario);
			User usuario = (User) usuarioConsultado.get();
			
			companheiroDTO.setId(companheiro.getId());
			companheiroDTO.setIdUsuario(companheiro.getIdUser());
			companheiroDTO.setCelular(companheiro.getCelular());
			companheiroDTO.setNome(companheiro.getNome());
			companheiroDTO.setEmail(usuario.getEmail());
			
		}
		
		//Retorna o dentistaDTO se houver o objeto pesquisado, senão retorna o erro de "não encontrado"
		return companheiroProcurado.isPresent() ? ResponseEntity.ok(companheiroDTO) : ResponseEntity.notFound().build();
		
	}

}
