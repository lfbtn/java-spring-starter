package br.lb.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.lb.admin.entity.Role;
import br.lb.admin.entity.User;
import br.lb.admin.repository.UserRepository;

@Service
public class UserService {

	private static final int page_size = 10;

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Page<User> findAllPage(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber, page_size, Sort.Direction.ASC, "name");
		return userRepository.findAll(request);
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public List<User> findByRoles(Role role) {
		return userRepository.findByRoles(role);
	}

	/*
	 * public List<User> findByCentrodeCustoContabil(CentrodeCustoContabil
	 * centrodeCustoContabil){ return
	 * userRepository.findByCentrodeCustoContabil(centrodeCustoContabil); }
	 */

	public User findByCpf(String cpf) {
		return userRepository.findByCpf(cpf);
	}

	public User findByCpfAndPassword(String cpf, String password) {
		return userRepository.findByCpfAndPassword(cpf, password);
	}

}
