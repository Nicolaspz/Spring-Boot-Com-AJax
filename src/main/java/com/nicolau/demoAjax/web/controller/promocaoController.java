package com.nicolau.demoAjax.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.nicolau.demoAjax.domain.categorias;
import com.nicolau.demoAjax.domain.promocao;
import com.nicolau.demoAjax.repository.categoriaRepository;

import com.nicolau.demoAjax.repository.promocaoRepository;


@Controller
@RequestMapping("/promocao")
public class promocaoController {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(promocaoController.class);
	@Autowired
	promocaoRepository reposi;
	@Autowired
	categoriaRepository reposiCategoria;
	
	
	
	
	@GetMapping("/add")
	public String AbrirCadastro() {
		return "promo-add";
	}
	@ModelAttribute("categorias")
	public List<categorias> ListarTodasCategorias() {
		System.out.println("categoriaaas"+ reposiCategoria.findAll());
		return reposiCategoria.findAll();
	}
	
	
	@PostMapping("/save")
	
	public ResponseEntity<?> salvarPromocao(@Valid promocao promocao ,BindingResult result){

		if (result.hasErrors()) {
			
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		log.info("Promocao {} ", promocao.toString());
	promocao.setDtCadastro(LocalDateTime.now());
		reposi.save(promocao);
		return ResponseEntity.ok().build();
		
	}
	 
	@ModelAttribute("cate")
	public List<categorias> ListarMais(){
		return reposiCategoria.findAll();
	}

}
