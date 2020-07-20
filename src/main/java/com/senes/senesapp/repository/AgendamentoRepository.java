package com.senes.senesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senes.senesapp.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	
Agendamento findById(long id);
	
	@Query(value="SELECT a FROM Agendamento a "
			+ "INNER JOIN a.companheiro comp WHERE comp.id =:companheiro")
	List<Agendamento> findAgendamentoPorIdCompanheiro(Long companheiro);

}
