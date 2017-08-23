package br.lb.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.lb.admin.entity.News;
import br.lb.admin.entity.Role;
import br.lb.admin.entity.User;
import br.lb.admin.repository.NewsRepository;
import br.lb.admin.repository.RoleRepository;
import br.lb.admin.repository.UserRepository;

//import br.lb.admin.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private NewsRepository newsRepository;

	@PostConstruct
	public void init() {

		if (userRepository.findAll().isEmpty()) {

			usersTest();

			newsTest();

		} else {

		}

	}

	private void newsTest() {
		News news = new News();
		news.setTitle("Grande evento começa dia 03 de abril!");
		news.setText("O grande evento stá em sua 16ª edição. Organizada pela organizadora, "
				+ "tem crescido a cada ano, tanto em número de expositores e de "
				+ "comercialização de produtos, quanto em quantitativo de visitantes. ");
		news.setFotografo("Luiz Botton");
		news.setVisivel(true);
		news.setImagem("http://www.pensamentoverde.com.br/wp-content/uploads/2014/02/img19.jpg");
		news.setCategoria1("Notícias");
		news.setCategoria2("Eventos");
		news.setCategoria3("");
		newsRepository.save(news);
	}

	private void usersTest() {
		User sysAdmin;
		Role adminRole;
		Role holeriteRole;
		adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		sysAdmin = new User();
		sysAdmin.setName("Luiz Botton");
		List<Role> rolesAdmin = new ArrayList<Role>();
		rolesAdmin.add(adminRole);
		sysAdmin.setRoles(rolesAdmin);
		sysAdmin.setEmail("luizbotton@gmail.com");

		sysAdmin.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		sysAdmin.setPassword(encoder.encode("lb2002"));
		sysAdmin.setCpf("00484779079");

		userRepository.save(sysAdmin);
		
		User admin = new User();
		admin.setName("admin");
		admin.setRoles(rolesAdmin);
		admin.setEmail("admin@admin.com.br");

		admin.setEnabled(true);
		encoder = new BCryptPasswordEncoder();
		admin.setPassword(encoder.encode("1234"));
		admin.setCpf("0123456789");

		userRepository.save(admin);
		
		User kaiaque = new User();
		kaiaque.setName("Kaique");
		kaiaque.setRoles(rolesAdmin);
		kaiaque.setCpf("04015926188");
		kaiaque.setPassword(encoder.encode("1234"));
		
		userRepository.save(kaiaque);
		
	}

}
