package com.senes.senesapp.tools.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senes.senesapp.tools.pagamento.entity.CartaoCredito;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoCredito, Long> {

}
