package br.lb.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.lb.admin.entity.AppConfiguration;
import br.lb.admin.entity.News;
import br.lb.admin.service.NewsService;
import br.lb.leal.util.PageIndex;

@RestController
public class NewsRestController {

	@Autowired
	private NewsService newsService;

	AppConfiguration appConfiguration = new AppConfiguration();

	PageIndex pageIndex = new PageIndex();

	@CrossOrigin(origins = "*")
	@RequestMapping("/rest/noticias")
	public Page<News> newsRest(Model model) {
		Page<News> pagina = newsService.findAllPage(0);
		pageIndex.montarIndicedePaginas(model, pagina);
		model.addAttribute("newsPage", pagina);
		return pagina;
	}

}