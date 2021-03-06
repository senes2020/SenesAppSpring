package com.senes.senesapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senes.senesapp.model.User;




//Dessa vez criando repository extendendo CrudRepository
//A diferença do JPA para o CrudRepository é que o JPA contém vários métodos do Hibernate
//enquanto o CrudRepository possui o básico de CRUD
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional findById(long id);
	
	User findByCpf(String cpf);

	Optional<User> findByCodigo(int codigo);
	
}
