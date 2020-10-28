package br.com.salon.carine.lima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.dto.RelatorioLancamentoDTO;
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/lancar")
	public ResponseEntity<Message> lancar(Integer idAtendimento) {
		Message message = this.lancamentoService.lancar(idAtendimento);
		return ResponseEntity.ok().body(message);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "relatorio")
	public ModelAndView relatorio() {
		ModelAndView modelAndView = new ModelAndView("lancamento/relatorio");
		RelatorioLancamentoDTO relatorio = lancamentoService.getRelatorioPeriodo();
		modelAndView.addObject("lancamentos", relatorio.getLancamentos());
		modelAndView.addObject("totalPeriodo", relatorio.getTotalPeriodo());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/estornar/{id}")
	public ResponseEntity<RelatorioLancamentoDTO> estorno(@PathVariable Integer id){
		
		RelatorioLancamentoDTO relatorioAtualizado = lancamentoService.estornarLancamento(id);
		
		if(relatorioAtualizado != null) {
			return ResponseEntity.ok().body(relatorioAtualizado);			
		}else {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	
}
