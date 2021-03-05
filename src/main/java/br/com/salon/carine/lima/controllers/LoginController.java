package br.com.salon.carine.lima.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.salon.carine.lima.dto.UsuarioDTO;
import br.com.salon.carine.lima.dto.UsuarioRecoveryDTO;
import br.com.salon.carine.lima.models.Usuario;
import br.com.salon.carine.lima.services.LoginService;
import br.com.salon.carine.lima.services.UsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView loginForm(@RequestParam(required = false, 
		defaultValue = "") String error, RedirectAttributes redirect) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(loginService.isAuthenticated()) {
			modelAndView.setViewName("redirect:dashboard/home");
			return modelAndView;
		}
		
		modelAndView.setViewName("security/loginForm");
		

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
	public ModelAndView esqueciSenha(UsuarioRecoveryDTO usuarioRecovery) {
		ModelAndView modelAndView = new ModelAndView("security/esqueciSenha");
		modelAndView.addObject("usuarioRecovery", usuarioRecovery);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "registrar")
	public ModelAndView registrar(UsuarioDTO usuario) {
		ModelAndView modelAndView = new ModelAndView("security/registroUsuario");
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "warningSessionExpired")
	public ModelAndView warningMaximumSessionExpired() {
		
		ModelAndView modelAndView = new ModelAndView("security/warning-session");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "register", name = "registerUser")
	public ModelAndView registrarUsuario(@Valid @ModelAttribute("usuario") UsuarioDTO usuario,
			BindingResult result, RedirectAttributes redirect, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return registrar(usuario);
		}
		
		try {
			
			loginService.registrarUsuarioService(usuario, request);
			redirect.addFlashAttribute("sucesso","Usuário pré cadastrado com sucesso. "
					+"Um link para confirmação da conta foi enviado para o email "
					+usuario.getEmail()
					+" acesse o link para desbloqueio da conta.");
			
			return new ModelAndView("redirect:/login");
			
		} catch (Exception e) {
			redirect.addFlashAttribute("atencao","Já existe uma conta vinculada a este email");
			return new ModelAndView("redirect:/registrar");
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, 
			value = "solicitarNovaSenha", name = "solicitarSenha")
	public ModelAndView solicitarNovaSenha(@Valid @ModelAttribute("usuarioRecovery") UsuarioRecoveryDTO usuarioRecovery,
			BindingResult result, RedirectAttributes redirect) {
		
		if(result.hasErrors()) {
			return esqueciSenha(usuarioRecovery);
		}
		
		loginService.recuperarSenhaUsuario(usuarioRecovery.getEmail());
		
		redirect.addFlashAttribute("sucesso", 
				"Solitação efetuada com sucesso. "
				+ "Uma nova senha foi encaminhada para o seu email");
		
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "desbloquearUsuario")
	public ModelAndView desbloquearUsuario(@RequestParam(required = true) String identity) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		Optional<Usuario> userSaltByUser = usuarioService.userSaltByUser(identity);
		
		if(userSaltByUser.isPresent()) {
			Usuario usuario = userSaltByUser.get();
			usuario.setAccountNoLocked(true);
			loginService.updateUsuario(usuario);
			
			modelAndView.setViewName("security/warning-account-valid");
			
			return modelAndView;
		}
		
		/*Retornar página mensagem inválida*/
		return null;
	}
}
