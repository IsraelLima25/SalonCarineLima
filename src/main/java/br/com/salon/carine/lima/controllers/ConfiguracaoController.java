package br.com.salon.carine.lima.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.dto.UsuarioAlterarSenhaDTO;

@Controller
@RequestMapping(value = "/config")
public class ConfiguracaoController {
	
	@RequestMapping(method = RequestMethod.POST, value = "alterarSenhaUsuario", name = "alterarSenhaAcesso")
	public ModelAndView alterarSenha(@Valid @ModelAttribute("changePasswordForm") UsuarioAlterarSenhaDTO changePasswordForm,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return formAlterarSenha(changePasswordForm);
		}
		
		System.out.println("a");
		return new ModelAndView("cofiguracao/alterarSenha");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "alterarSenha", name = "alterarSenhaUsuario")
	public ModelAndView formAlterarSenha(UsuarioAlterarSenhaDTO changePasswordForm) {
		ModelAndView modelAndView = new ModelAndView("configuracao/alterarSenha");
		modelAndView.addObject("changePasswordForm", changePasswordForm);
		return modelAndView;
	}
	

}
