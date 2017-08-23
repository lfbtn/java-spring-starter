package br.lb.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.lb.admin.entity.AppConfiguration;
import br.lb.admin.entity.User;
import br.lb.admin.service.UserService;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    AppConfiguration appConfiguration = new AppConfiguration();

    @RequestMapping("/rest/usuarios")
    public Page<User> usuarios(Model model) {
	model.addAttribute("appName", appConfiguration.getAppName());
	Page<User> pagina = userService.findAllPage(0);
	return pagina;
    }

    @RequestMapping("/rest/usuario/{cpf}")
    public User usuario(Model model, @PathVariable(value = "cpf") String cpf) {
	model.addAttribute("appName", appConfiguration.getAppName());
	return userService.findByCpf(cpf);
    }

    @RequestMapping("/rest/check/usuario/{cpf}/{pass}")
    public User usuario(Model model, @PathVariable(value = "cpf") String cpf,
            @PathVariable(value = "pass") String pass) {
	return userService.findByCpf(cpf);
    }

}