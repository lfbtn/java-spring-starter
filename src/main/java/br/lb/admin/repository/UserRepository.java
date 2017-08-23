package br.lb.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.lb.admin.entity.Role;
import br.lb.admin.entity.User;
//import br.lb.financeiro.entity.CentrodeCustoContabil;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByRoles(Role role);
	
	//List<User> findByCentrodeCustoContabil(CentrodeCustoContabil centrodeCustoContabil);
	
	User findByName(String name);
	
	User findByCpf(String cpf);
	
	User findByCpfAndPassword(String cpf, String password);
	
	//Page<User> findByCpf(Pageable p, String cpf);
	
}
