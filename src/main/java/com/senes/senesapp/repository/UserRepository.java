package com.senes.senesapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.senes.senesapp.model.User;


//Dessa vez criando repository extendendo CrudRepository
//A diferença do JPA para o CrudRepository é que o JPA contém vários métodos do Hibernate
//enquanto o CrudRepository possui o básico de CRUD
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByCpf(String cpf);
	
}
