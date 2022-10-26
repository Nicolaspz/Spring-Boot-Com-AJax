package com.nicolau.demoAjax.service;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicolau.demoAjax.domain.SocilaMetaTag;



@Service
public class SocilaMetaTagService {
	
	private static Logger log = LoggerFactory.getLogger(SocilaMetaTagService.class);
	
	private SocilaMetaTag getSocialMetaTagByurl(String url) {
		SocilaMetaTag twitter =  getTwitterByUrl(url);
		if(!isEmpty(twitter)) {
			return twitter;
		}
		
		SocilaMetaTag openGraph = getSocialMetaTagByurl(url);
		if(!isEmpty(openGraph)) {
			return openGraph;
		}
		
		return null;
		
		}		
	
	private SocilaMetaTag getTwitterByUrl(String url) {
		SocilaMetaTag tag = new SocilaMetaTag();
		try {
			Document doc = Jsoup.connect(url).get();
			tag.setTitle(doc.head().select("meta[name=twitter:title]").attr("content"));
			tag.setSite(doc.head().select("meta[name=twitter:site]").attr("content"));
			tag.setImage(doc.head().select("meta[name=twitter:image]").attr("content"));
			tag.setUrl(doc.head().select("meta[name=twitter:url]").attr("content"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
		
	}

	public SocilaMetaTag getOpenGraphByUrl(String url) {
		SocilaMetaTag tag = new SocilaMetaTag();
		try {
			Document doc = Jsoup.connect(url).get();
			tag.setTitle(doc.head().select("meta[property=og:title]").attr("content"));
			tag.setSite(doc.head().select("meta[property=og:site_name]").attr("content"));
			tag.setImage(doc.head().select("meta[property=og:image]").attr("content"));
			tag.setUrl(doc.head().select("meta[property=og:url]").attr("content"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
		
	}
	
	private boolean isEmpty(SocilaMetaTag tag){
		if(tag.getImage().isEmpty()) return true;
		if(tag.getSite().isEmpty()) return true;
		if(tag.getTitle().isEmpty()) return true;
		if(tag.getUrl().isEmpty()) return true;
		
		return false;
		
	} 
}
