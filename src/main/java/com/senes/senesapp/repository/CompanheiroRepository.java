package com.senes.senesapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senes.senesapp.model.Companheiro;

@Repository
public interface CompanheiroRepository extends JpaRepository<Companheiro, Long>{
	
	Optional<?> findByUser_Id(long id);

	@Query(value="SELECT p FROM Companheiro p "
			+ "INNER JOIN p.especializacoes especi WHERE especi.nome_especializacao = :nome_especializacao")
	List<Companheiro> findProfissionalPorEspecializacao(@Param("nome_especializacao") String especializacao);

}
