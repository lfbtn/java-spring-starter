package br.lb.admin.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.lb.admin.entity.Role;
import br.lb.admin.entity.User;
import br.lb.admin.service.RoleService;
import br.lb.admin.service.UserService;
//import br.lb.dp.holerite.service.HoleriteService;
//import br.lb.financeiro.entity.CentrodeCustoContabil;
//import br.lb.financeiro.service.CentrodeCustoContabilService;
import br.lb.util.mail.sender.Mail;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	//@Autowired
	//private CentrodeCustoContabilService centrodeCustoContabilService;

	@RequestMapping("/register")
	public String register(Model model) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(Model model, @RequestParam String name, @RequestParam String cpf,
			@RequestParam String email, @RequestParam String password1, @RequestParam String password2,
			@RequestParam String profissao) {
		cpf = cpf.replaceAll("[^\\d]", "");
		if (password1.equals(password2)) {
			try {
				User user = userService.findByCpf(cpf);
				user.setFirstTimeLogin(true);
				user.setEnabled(true);
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				user.setPassword(encoder.encode(password1));
				user.setPdfToken(password1);
				List<Role> roles = new ArrayList<Role>();
				roles.add(roleService.findByName("ROLE_ADMIN"));
				user.setRoles(roles);
				userService.save(user);
			} catch (Exception e) {
				System.out.println(new Date() + " UserController /register POST: " + e);
				model.addAttribute("encontrouCpf", false);
			}
		} else {
			model.addAttribute("passwordIgual", false);
		}
		return "redirect:/login.html";
	}

	@RequestMapping("/users")
	public String users(Model model, Authentication authentication) {
		try {
			boolean autorizado = false;
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
					autorizado = true;
				}
			}
			if (autorizado) {
				model.addAttribute("users", userService.findAll());
			} else {
				return "redirect:/error-403.html";
			}
		} catch (Exception e) {
			return "redirect:/error-403.html";
		}
		return "users";
	}

	@RequestMapping("/user-register")
	public String userRegister(Model model, Authentication authentication) {
		try {
			boolean autorizado = false;
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
					autorizado = true;
				}
			}
			if (autorizado) {
				//model.addAttribute("centrosDeCusto", centrodeCustoContabilService.findAll());
				model.addAttribute("roles", roleService.findAll());
				model.addAttribute("hoje", dataAtual());
				return "user-register";
			}
		} catch (Exception e) {
			return "redirect:/error-403.html";
		}
		return "redirect:/error-403.html";

	}

	@RequestMapping(value = "/user-register", method = RequestMethod.POST)
	public String doUserRegister(Model model, @RequestParam String name, @RequestParam String email,
			Principal principal, @RequestParam String password,
			@RequestParam(value = "idCentroDeCusto", defaultValue = "0", required = false) int idCentroDeCusto,
			@RequestParam(value = "listaRolesSelecionadas[]", defaultValue = "0", required = false) int[] listaRolesSelecionadas,
			@RequestParam java.sql.Date anoInclusao,
			@RequestParam(value = "profissao", defaultValue = "", required = false) String profissao) {
		try {
			List<Role> roles = new ArrayList<Role>();
			User user = new User();
			user.setFirstTimeLogin(false);
			user.setEnabled(true);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(password));
			user.setPdfToken(password);
			//CentrodeCustoContabil centrodeCusto = centrodeCustoContabilService.findOne(idCentroDeCusto);
			user.setName(name);
			user.setEmail(email);
			user.setProfissao(profissao);
			userService.save(user);
			//user.setCentrodeCustoContabil(centrodeCusto);
			for (int idRole : listaRolesSelecionadas) {
				Role role = roleService.findOne(idRole);
				roles.add(role);
			}
			user.setRoles(roles);
			user.setAnoInclusao(anoInclusao);
			userService.save(user);
		} catch (Exception e) {
			System.out.println("User Controller  User Register Post: " + e + " " + new Date());
		}
		return "redirect:/users.html";
	}

	@RequestMapping(value = "/user-update/{id}", method = RequestMethod.GET)
	public String showUserUpdate(Model model, @PathVariable int id, Principal principal) {
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("roles", roleService.findAll());
		return "user-update";
	}

	@RequestMapping(value = "/user-update/{id}", method = RequestMethod.POST)
	public String doUserUpdate(Model model, @PathVariable int id, Principal principal,
			@RequestParam(value = "listaRolesSelecionadas[]", defaultValue = "0", required = false) int[] listaRolesSelecionadas) {
		User user = userService.findOne(id);
		for (int roleId : listaRolesSelecionadas) {
			Role role = roleService.findOne(roleId);
			user.getRoles().add(role);
			userService.save(user);
		}
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("roles", roleService.findAll());
		return "user-update";
	}

	@RequestMapping("/user-password-recovery")
	public String showPasswordRecovery() {
		return "user-password-recovery";
	}

	@RequestMapping("/user-has-token")
	public String showUserHasToken(Model model) {
		return "user-has-token";

	}

	@RequestMapping(value = "/user-has-token", method = RequestMethod.POST)
	public String doUpdatePassword(String token, String cpf, String password1, String password2) {
		User user = userService.findByCpf(cpf);
		if (user != null) {
			if (user.getToken().equals(token)) {
				if (password1.equals(password2)) {

					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					String password = encoder.encode(password1);
					user.setPassword(password);
					user.setPdfToken(password1);
					userService.save(user);

				} else {
					System.out.println("Passwords não correspondem");
					return "redirect:/user-password-recovery.html?dp=true"; // differents
																			// passwords
																			// =
																			// true
				}
			} else {
				System.out.println("Tokens não correspondem");
				return "redirect:/user-password-recovery.html?it=true"; // invalid
																		// token
																		// =
																		// true
			}
		} else {
			System.out.println("Usuario não encontrado");
			return "redirect:/user-password-recovery.html?unf=true"; // user not
																		// found
																		// =
																		// true
		}
		return "redirect:/login.html?pref=true"; // password redefined = true
	}

	@RequestMapping(value = "/user-password-recovery", method = RequestMethod.POST)
	public String doPasswordRecovery(@RequestParam String cpf, @RequestParam String email) {
		cpf = cpf.replaceAll("[^\\d]", "");
		try {
			User user = userService.findByCpf(cpf);
			try {
				if (user.getEmail() != null) {
					String to = user.getEmail();
					Mail enviarEmail = new Mail();
					if (to.equals(email)) {
						String randomToken = generateRandomSecurityString();
						enviarEmail.sendMailPasswordRecovery(to, randomToken);
						user.setToken(randomToken);
						userService.save(user);
						return "redirect:/user-password-recovery.html?es=true"; // email
																				// sent
																				// =
																				// true

					} else {
						System.out.println("Email informado e diferente do cadastrado");
						return "redirect:/user-password-recovery.html?ed=true"; // email
																				// different
																				// =
																				// true
					}
				}
			} catch (Exception e) {
				System.out.println("Exception e: " + e);
				return "redirect:/user-password-recovery.html?enr=true"; // user
																			// dont
																			// have
																			// email
																			// registered
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return "redirect:/user-password-recovery.html?unf=true"; // user not
																		// found
																		// =
																		// true
		}
		return "redirect:/user-password-recovery.html?unf=true"; // user not
																	// found =
																	// true
	}

	@RequestMapping("/user-email-register")
	public String showUserEmailRegister(Model model, Principal principal) {
		try {
			User user = userService.findByCpf(principal.getName());
			model.addAttribute("user", user);
			return "user-email-register";
		} catch (Exception e) {
			return "redirect:/login.html";
		}
	}

	@RequestMapping(value = "/user-email-register", method = RequestMethod.POST)
	public String doUserEmailRegister(Model model, Principal principal, String email, String emailConfirm,
			String password1, String password2) {
		try {
			if (principal != null) {

				if ((email.equals(emailConfirm)) && ((password1.equals(password2)))) {
					User user = userService.findByCpf(principal.getName());
					user.setEmail(email);
					user.setFirstTimeLogin(false);
					user.setPdfToken(password1);
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					user.setPassword(encoder.encode(password1));
					userService.save(user);
					return "redirect:/holerites.html";
				} else {
					return "redirect:/user-email-register.html";
				}
			} else {
				return "redirect:/login.html";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/user-email-register.html";
		}
	}

	public String generateRandomSecurityString() {
		Random gerador = new Random();
		return Integer.toString(gerador.nextInt(513287) * 1564);
	}

	public String dataAtual() {
		String formatoData = "yyyy-MM-dd";
		String dataHoraAtual = "";
		java.util.Date agora = new java.util.Date();
		SimpleDateFormat formata = new SimpleDateFormat(formatoData);
		dataHoraAtual = formata.format(agora);
		return dataHoraAtual;
	}

}
