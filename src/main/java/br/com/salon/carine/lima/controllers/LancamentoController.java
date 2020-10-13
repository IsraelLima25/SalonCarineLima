package br.com.salon.carine.lima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.response.Message;
import br.com.salon.carine.lima.services.AtendimentoService;
import br.com.salon.carine.lima.services.LancamentoService;

@Controller
@RequestMapping(value = "/lancamento")
public class LancamentoController {
	
	@Autowired
	public LancamentoService lancamentoService;
	
	@Autowired
	public AtendimentoService serviceAtendimento;
	
	@RequestMapping(method = RequestMethod.GET, value = "relatorio")
	public ModelAndView relatorio() {
		ModelAndView modelAndView = new ModelAndView("lancamento/relatorio");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "lancar/{id}")
	public ResponseEntity<Message> lancar(@PathVariable Integer id) {
		Message message = this.lancamentoService.lancar(id);
		return ResponseEntity.ok().body(message);
	}
	
}
