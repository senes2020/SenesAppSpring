package com.senes.senesapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senes.senesapp.model.Beneficiario;
import com.senes.senesapp.model.Companheiro;

@Repository
public interface CompanheiroRepository extends JpaRepository<Companheiro, Long>{
	
	Optional<?> findByUser_Id(long id);

}
