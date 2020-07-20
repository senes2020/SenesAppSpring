package com.senes.senesapp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="tbl_profissionais")
public class Companheiro implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	//Versão com tabela de usuários
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "idUser",referencedColumnName = "id")
	private User user;
	
	@NotNull
	@Size(min=3, max=100, message="Por favor, digite um nome com pelo menos 3 caracteres.")
	private String nome;
	
	@NotNull
	private String celular;
	
	@NotNull
	private String carteira_motorista;
	
	@NotNull
	private String valor_hora;
	
	@ManyToMany
	@JoinColumn(name = "especializacao", referencedColumnName = "id_especializacao")
	private List<Especializacao> especializacoes;
	
	@NotNull
	private Float avaliacao;
	
	@NotNull 
	private String sexo;
	
	private String dados_bio;
	
	public String getDados_bio() {
		return dados_bio;
	}

	public void setDados_bio(String dados_bio) {
		this.dados_bio = dados_bio;
	}

	public Float getAvaliacao() {
		return avaliacao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public long getIdUser() {
		return user.getId();
	}

	public void setIdUser(long idUser) {
		this.user.setId(idUser);
	}

	public String getCarteira_motorista() {
		return carteira_motorista;
	}

	public void setCarteira_motorista(String carteira_motorista) {
		this.carteira_motorista = carteira_motorista;
	}

	public String getValor_hora() {
		return valor_hora;
	}

	public void setValor_hora(String valor_hora) {
		this.valor_hora = valor_hora;
	}

	public List<Especializacao> getEspecializacoes() {
		return especializacoes;
	}

	public void setEspecializacoes(List<Especializacao> especializacoes) {
		this.especializacoes = especializacoes;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setAvaliacao(Float avaliacao) {
		this.avaliacao = avaliacao;
	}

}
