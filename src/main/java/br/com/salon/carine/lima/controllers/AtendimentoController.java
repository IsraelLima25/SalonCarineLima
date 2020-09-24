package br.com.salon.carine.lima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.models.Carrinho;
import br.com.salon.carine.lima.services.ClienteService;
import br.com.salon.carine.lima.services.ServicoService;

@Controller
@RequestMapping(value = "atendimento")
public class AtendimentoController {
	
	@Autowired
	private ClienteService serviceCliente;
	
	@Autowired
	private ServicoService serviceServico;
	
	@Autowired
	private Carrinho carrinho;
	
	@RequestMapping(method = RequestMethod.GET, value = "marcar")
	public ModelAndView marcarAtendimento() {
		
		List<ClienteDTO> clientes = serviceCliente.listarTodos();
		List<ServicoDTO> servicos = serviceServico.listarTodos();
		
		ModelAndView modelAndView = new ModelAndView("atendimento/formMarcar");
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("servicos",servicos);
		
		carrinho.esvaziar();
		
		return modelAndView;
	}

}
