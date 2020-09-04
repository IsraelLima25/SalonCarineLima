package br.com.salon.carine.lima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.services.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService serviceCliente;

	@RequestMapping(method = RequestMethod.GET, value = "cliente")
	public ModelAndView formCadastroCliente() {
		ModelAndView modelAndView = new ModelAndView("cliente/formCadastro");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "cliente")
	public ModelAndView cadastrarCliente(ClienteDTO clienteDTO) {
		ModelAndView modelAndView = new ModelAndView("cliente/formCadastro");
		this.serviceCliente.cadastrar(clienteDTO);
		return modelAndView;

	}

}
