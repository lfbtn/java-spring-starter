package br.lb.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.lb.admin.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer>{
		
	
}