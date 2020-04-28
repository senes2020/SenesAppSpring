package com.senes.senesapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.senes.senesapp.model.Beneficiario;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long>{

	Beneficiario findById(long id);

	Optional<Beneficiario> findByCpf(String cpf);

	Optional<Beneficiario> findByCodigo(int codigo);
	
}
