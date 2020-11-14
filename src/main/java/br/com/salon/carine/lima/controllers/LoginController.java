package br.com.salon.carine.lima.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.salon.carine.lima.services.UsuarioService;

@Controller
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView loginForm(@RequestParam(required = false, 
		defaultValue = "") String error) {
		
		ModelAndView modelAndView = new ModelAndView("security/loginForm");
		if(!error.isEmpty()) {
			String emailInvalido = UsuarioService.emailPage;
			modelAndView.addObject("message","Usuário ou Senha inválidos");
			modelAndView.addObject("emailInvalido", emailInvalido);
			return modelAndView;
		}else {
			return modelAndView;
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(RedirectAttributes redirectAttributes) {
		return "redirect:dashboard/home";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "esqueciSenha")
	public ModelAndView esqueciSenha() {
		ModelAndView modelAndView = new ModelAndView("security/esqueciSenha");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "registrar")
	public ModelAndView registrar() {
		ModelAndView modelAndView = new ModelAndView("security/registroUsuario");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "warningSessionExpired")
	public ModelAndView warningMaximumSessionExpired() {
		
		ModelAndView modelAndView = new ModelAndView("security/warning-session");
		
		return modelAndView;
		
	}

}
