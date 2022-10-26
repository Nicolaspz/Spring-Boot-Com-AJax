package com.nicolau.demoAjax.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nicolau.demoAjax.domain.SocilaMetaTag;
import com.nicolau.demoAjax.service.SocilaMetaTagService;

@Controller
@RequestMapping("/meta")
public class SocialMetaController {
	
	@Autowired
	private SocilaMetaTagService service;
	
	@PostMapping("/info")
	public ResponseEntity<SocilaMetaTag> getDadosViaUrl(@RequestParam("url") String url){
		SocilaMetaTag tag= service.getOpenGraphByUrl(url);
		return tag != null
				? ResponseEntity.ok(tag)
				:ResponseEntity.notFound().build();
		}

}
