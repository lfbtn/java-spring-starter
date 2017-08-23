package br.lb.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.lb.admin.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role findByName(String name);	
	
}
