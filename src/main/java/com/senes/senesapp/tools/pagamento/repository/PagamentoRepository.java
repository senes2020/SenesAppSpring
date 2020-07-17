package com.senes.senesapp.tools.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senes.senesapp.tools.pagamento.entity.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
