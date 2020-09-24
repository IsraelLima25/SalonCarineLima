package br.com.salon.carine.lima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.services.ServicoService;

@Controller
@RequestMapping(value = "loja")
public class LojaController {

	@Autowired
	private ServicoService serviceServico;
	
	@RequestMapping(method = RequestMethod.GET, value = "itensLoja")
	public ModelAndView getLoja() {
		
		List<ServicoDTO> servicos = this.serviceServico.listarTodos();
		
		ModelAndView modelAndView = new ModelAndView("loja/home-loja");
		modelAndView.addObject("servicos",servicos);

		return modelAndView;
	}
	
	
	

}
