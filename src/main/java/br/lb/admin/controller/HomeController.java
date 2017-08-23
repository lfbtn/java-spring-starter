package br.lb.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.lb.admin.entity.AppConfiguration;

@Controller
public class HomeController {

    AppConfiguration appConfiguration = new AppConfiguration();

    @RequestMapping("/home")
    public String home(Model model) {
	model.addAttribute("appName", appConfiguration.getAppName());
	model.addAttribute("appIntro", appConfiguration.getAppIntro());
	model.addAttribute("appIntroTitle1", appConfiguration.getAppintrotitle1());
	model.addAttribute("appIntroDesc1", appConfiguration.getAppintrodesc1());
	model.addAttribute("appIntroTitle2", appConfiguration.getAppintrotitle2());
	model.addAttribute("appIntroDesc2", appConfiguration.getAppintrodesc2());
	model.addAttribute("appIntroTitle3", appConfiguration.getAppintrotitle3());
	model.addAttribute("appIntroDesc3", appConfiguration.getAppintrodesc3());
	model.addAttribute("appIntroTitle4", appConfiguration.getAppintrotitle4());
	model.addAttribute("appIntroDesc4", appConfiguration.getAppintrodesc4());
	return "home";
    }

}
