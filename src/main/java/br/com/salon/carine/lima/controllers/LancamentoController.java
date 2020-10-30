package br.com.salon.carine.lima.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.dto.RelatorioLancamentoDTO;
import br.com.salon.carine.lima.exceptions.ArgumentNotValidException;
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
	
	@RequestMapping(method = RequestMethod.POST, value = "filterData")
	public ResponseEntity<RelatorioLancamentoDTO> filterDataLancamento(
			@Valid FiltroDataAtendimentoDTO filtroData,
			BindingResult result, 
			HttpServletRequest request){
		
		if(result.hasErrors()) {
			throw new ArgumentNotValidException(result, request);
		}
		 
		RelatorioLancamentoDTO relatorio = lancamentoService
				.findLancamentoBetweenDate(filtroData);
		
		return ResponseEntity.ok().body(relatorio);
	}
	
}
