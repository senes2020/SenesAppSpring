package com.senes.senesapp.tools.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senes.senesapp.tools.pagamento.entity.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

}
