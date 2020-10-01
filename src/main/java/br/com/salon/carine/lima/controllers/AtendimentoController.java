package br.com.salon.carine.lima.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.EnderecoDTO;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.dto.ResponseMarcarDTO;
import br.com.salon.carine.lima.services.AtendimentoService;
import br.com.salon.carine.lima.services.CarrinhoService;
import br.com.salon.carine.lima.services.ClienteService;

@Controller
@RequestMapping(value = "atendimento")
public class AtendimentoController {
	
	@Autowired
	private ClienteService serviceCliente;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	private Logger logger = Logger.getLogger("br.com.salon.carine.lima.Atendimento");

	
	@RequestMapping(method = RequestMethod.GET, value = "formMarcar")
	public ModelAndView formMarcarAtendimento() {		
		
		List<ClienteDTO> clientes = serviceCliente.listarTodos();
		BigDecimal valorTotalCarrinho = carrinhoService.getValorTotalCarrinho();
		ModelAndView modelAndView = new ModelAndView("atendimento/formMarcar");
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("valorTotalCarrinho", valorTotalCarrinho);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseMarcarDTO> marcaAtendimento(
			MarcarAtendimentoDTO atendimentoDTO, BindingResult result, 
			HttpServletRequest request){
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(atendimentoDTO.getId())
				.toUri();

		ResponseMarcarDTO response = atendimentoService.marcarAtendimento(atendimentoDTO,
				request, result);

		return ResponseEntity.created(uri).body(response);
		
	}
}
