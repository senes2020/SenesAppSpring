package com.senes.senesapp.tools.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senes.senesapp.tools.pagamento.entity.BeneficiarioPagante;

@Repository
public interface BeneficiarioPaganteRepository extends JpaRepository<BeneficiarioPagante, Long>{

	public BeneficiarioPagante findByCpf(String cpf);
	
}
