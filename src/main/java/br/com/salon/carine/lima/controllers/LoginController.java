package br.com.salon.carine.lima.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(RedirectAttributes redirectAttributes) {
		return "redirect:dashboard/home";
	}

}
