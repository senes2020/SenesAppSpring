package com.senes.senesapp.security;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;

import com.senes.senesapp.repository.UserRepository;

//@Service -> assinatura que informa ao String que pode ser utilizado como serviço

/*
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	
	//Montando senha
	String password = "";
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//No caso, é dito o caminho completo da classe 
		//pois abaixo dela será utilizada uma classe do UserDetails que tem o mesmo nome
		//e aí precisará sanar a ambiguidade
		//Aqui ele recebe o objeto procurado a partir do nome e devolve como um objeto UserDetails
		com.senes.senesapp.model.User user = userRepository.findByCpf(username);
		UserDetails userDetails = new User(username, password, AuthorityUtils.createAuthorityList(user.getRole()));
		
		return userDetails;
		
	}
	
}
*/
