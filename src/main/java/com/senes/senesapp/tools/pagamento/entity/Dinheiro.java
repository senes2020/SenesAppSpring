package com.senes.senesapp.tools.pagamento.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pag_dinheiro")
public class Dinheiro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_pag_dinheiro;
	
	public Long getId_pag_dinheiro() {
		return id_pag_dinheiro;
	}
	public void setId_pag_dinheiro(Long id_pag_dinheiro) {
		this.id_pag_dinheiro = id_pag_dinheiro;
	}
	
	@Override
	public String toString() {
		return "Dinheiro [id_pag_dinheiro=" + id_pag_dinheiro + "]";
	}
	
}
