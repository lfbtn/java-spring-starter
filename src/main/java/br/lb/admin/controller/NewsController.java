package br.lb.admin.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.lb.admin.entity.AppConfiguration;
import br.lb.admin.entity.News;
import br.lb.admin.service.NewsService;

@Controller
public class NewsController {

	AppConfiguration appConfiguration = new AppConfiguration();

	@Autowired
	public NewsService newsService;

	@RequestMapping("/noticias")
	public String login(Model model) {
		model.addAttribute("appName", appConfiguration.getAppName());
		model.addAttribute("news", newsService.findAll());
		return "noticias";
	}

	@RequestMapping("/noticia-register")
	public String noticiasRegister(Model model) {
		model.addAttribute("appName", appConfiguration.getAppName());
		return "noticia-register";
	}
	
	@RequestMapping(value = "/noticia-register", method = RequestMethod.POST)
	public String doRegister(Model model, @RequestParam String title, 
			@RequestParam String text,
			@RequestParam String autor,
			@RequestParam String fotografo,
			@RequestParam String publicacao,
			@RequestParam String remocao,
			@RequestParam String imagem,
			@RequestParam String categoria1,
			@RequestParam String categoria2,
			@RequestParam String categoria3
			//@RequestParam String visivel
			) {

		News news = new News();
		news.setAutor(autor);
		news.setTitle(title);
		news.setText(text);
		news.setFotografo(fotografo);
		news.setInclusao(new Date());
		news.setPublicacao(new Date(publicacao));
		news.setRemocao(new Date(remocao));
		news.setVisivel(true);
		news.setImagem(imagem);
		news.setCategoria1(categoria1);
		news.setCategoria2(categoria2);
		news.setCategoria3(categoria3);
		newsService.save(news);
		
		return "redirect:/noticias.html";
	}

}
