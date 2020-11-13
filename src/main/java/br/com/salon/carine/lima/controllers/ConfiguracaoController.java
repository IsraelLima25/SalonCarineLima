package br.com.salon.carine.lima.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.salon.carine.lima.dto.UsuarioAlterarSenhaDTO;
import br.com.salon.carine.lima.services.ConfigService;

@Controller
@RequestMapping(value = "/config")
public class ConfiguracaoController {
	
	@Autowired
	private ConfigService serviceConfig;
	
	@RequestMapping(method = RequestMethod.POST, value = "alterarSenhaUsuario", name = "alterarSenhaAcesso")
	public ModelAndView alterarSenha(@Valid @ModelAttribute("changePasswordForm") 
		UsuarioAlterarSenhaDTO changePasswordForm, BindingResult result,
		RedirectAttributes redirect) {

		if(result.hasErrors()) {
			return formAlterarSenha(changePasswordForm);
		}
		
		serviceConfig.alterarSenha(changePasswordForm.getPasswordDTO().getSenha());
		
		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard/home");
		redirect.addFlashAttribute("sucesso","Senha alterada com sucesso");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "alterarSenha", name = "alterarSenhaUsuario")
	public ModelAndView formAlterarSenha(UsuarioAlterarSenhaDTO changePasswordForm) {
		ModelAndView modelAndView = new ModelAndView("configuracao/alterarSenha");
		modelAndView.addObject("changePasswordForm", changePasswordForm);
		return modelAndView;
	}
	

}
