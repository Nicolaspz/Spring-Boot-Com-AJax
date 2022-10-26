package com.nicolau.demoAjax.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name ="categoria" )
public class categorias implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(name = "titulo", nullable = false, unique = true)
	private String titulo;
	
	@OneToMany(mappedBy = "categoria")
	private List<promocao> promocoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<promocao> promocoes) {
		this.promocoes = promocoes;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", titulo=" + titulo + "]";
	}
	
}
