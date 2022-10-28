package com.nicolau.demoAjax.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	 
	// ======================================LISTAR OFERTAS==========================================
	
	@GetMapping("/list")
	public String listarOfertas(ModelMap model) {
		Sort sort =Sort.by(Sort.Direction.DESC,"dtCadastro");//ordenar a lista
		PageRequest pageRequest = PageRequest.of(0, 4, sort); // colocar limite na página a cada pagina so aparece 4 elements
		model.addAttribute("promocoes",reposi.findAll( pageRequest));
		return "promo-list";
	}
		
		@GetMapping("/list/ajax")
		public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, ModelMap model) {
			
			Sort sort =Sort.by(Sort.Direction.DESC,"dtCadastro");
			PageRequest pageRequest = PageRequest.of(page, 4, sort);
			model.addAttribute("promocoes", reposi.findAll(pageRequest));
			
			return "promo-card";
		}	

}
