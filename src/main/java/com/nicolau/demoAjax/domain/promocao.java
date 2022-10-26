package com.nicolau.demoAjax.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;







@SuppressWarnings("serial")
@Entity
@Table(name= "promocoes")
public class promocao implements Serializable {
	
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 
 @NotBlank(message = "um titulo é requerido")
 @Column(nullable = false)
 private String titulo;
 
 public BigDecimal getPreco() {
	return preco;
}

 
public void setPreco(BigDecimal preco) {
	this.preco = preco;
}

 @NotBlank(message = "um link da promoção é requerido")
@Column(nullable = false)
 private String linkPromcao;
 
 @Column(nullable = false)
 private String site;
 
 @Column(nullable = false)
 private String  Image;
 
 @Column(nullable = false)
 private String decricao;
 

@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
@Column(name = "preco_promocao", nullable = false)
private BigDecimal preco;
	
 
@NotNull(message = " O preço é obrigatório")
 @ManyToOne
 @JoinColumn(name = "categoria_fk")
 private categorias categoria;
 
 @Column(name = "total_likes")
 private int likes;
 @Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dtCadastro;

public LocalDateTime getDtCadastro() {
	return dtCadastro;
}

public void setDtCadastro(LocalDateTime dtCadastro) {
	this.dtCadastro = dtCadastro;
}

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

public String getLinkPromcao() {
	return linkPromcao;
}

public void setLinkPromcao(String linkPromcao) {
	this.linkPromcao = linkPromcao;
}

public String getSite() {
	return site;
}

public void setSite(String site) {
	this.site = site;
}

public String getImage() {
	return Image;
}

public void setImage(String image) {
	Image = image;
}

public String getDecricao() {
	return decricao;
}

public void setDecricao(String decricao) {
	this.decricao = decricao;
}



public int getLikes() {
	return likes;
}

@Override
public String toString() {
	return "promocao [id=" + id + ", titulo=" + titulo + ", linkPromcao=" + linkPromcao + ", site=" + site + ", Image="
			+ Image + ", decricao=" + decricao + ", preco=" + preco + ", categoria=" + categoria + ", likes=" + likes
			+ ", dtCadastro=" + dtCadastro + "]";
}

public void setLikes(int likes) {
	this.likes = likes;
}


public categorias getCategoria() {
	return categoria;
}

public void setCategoria(categorias categoria) {
	this.categoria = categoria;
}
}
