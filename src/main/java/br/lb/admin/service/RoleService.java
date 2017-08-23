package br.lb.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.lb.admin.entity.Role;
import br.lb.admin.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findOne(int id) {
		return roleRepository.findOne(id);
	}

	public void save(Role role) {
		roleRepository.save(role);
	}

	public Role findByName(String name){
		return roleRepository.findByName(name);
	}
	
}
