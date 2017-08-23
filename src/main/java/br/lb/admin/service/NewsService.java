package br.lb.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.lb.admin.entity.News;
import br.lb.admin.entity.User;
import br.lb.admin.repository.NewsRepository;

@Service
public class NewsService {

    private static final int page_size = 10;
    
    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAll() {
	return newsRepository.findAll();
    }

    public News findOne(int id) {
	return newsRepository.findOne(id);
    }

    public void save(News news) {
	newsRepository.save(news);
    }
    
    public Page<News> findAllPage(Integer pageNumber) {
	PageRequest request = new PageRequest(pageNumber, page_size, Sort.Direction.ASC, "id");
	return newsRepository.findAll(request);
    }

}