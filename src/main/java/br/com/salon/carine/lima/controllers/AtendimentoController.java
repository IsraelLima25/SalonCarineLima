package br.com.salon.carine.lima.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.salon.carine.lima.dto.AtendimentoDTO;
import br.com.salon.carine.lima.dto.AtendimentoListaDTO;
import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.response.ResponseMarcar;
import br.com.salon.carine.lima.services.AtendimentoService;
import br.com.salon.carine.lima.services.CarrinhoService;
import br.com.salon.carine.lima.services.ClienteService;

@Controller
@RequestMapping(value = "/atendimento")
public class AtendimentoController {
	
	@Autowired
	private ClienteService serviceCliente;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	private Logger logger = Logger.getLogger("br.com.salon.carine.lima.Atendimento");
	
	@RequestMapping(method = RequestMethod.GET, value = "listar")
	public ModelAndView formDetalheAtendimento() {
		this.logger.info("Detalhando atendimento");
		ModelAndView modelAndView = new ModelAndView("atendimento/lista");
		List<AtendimentoListaDTO> atendimentosDTO = atendimentoService.listarTodos();
		modelAndView.addObject("lista", atendimentosDTO);
		return modelAndView;
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "filterData")
	@ResponseBody
	public String filterDataAtendimento
		(FiltroDataAtendimentoDTO filtro) {	
		
		List<AtendimentoListaDTO> listaFiltrada = atendimentoService.
				getAtendimentosFilterData(filtro);
		
		List<JSONObject> entities = new ArrayList<JSONObject>();
		
		for (AtendimentoListaDTO atendimento : listaFiltrada) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("clienteNome", atendimento.getCliente().getNome());
			jsonObject.put("data", atendimento.getData());
			jsonObject.put("hora", atendimento.getHora());
			entities.add(jsonObject);
		}
		
		return entities.toString();
		
	}

	
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
	public ResponseEntity<ResponseMarcar> marcaAtendimento(
			MarcarAtendimentoDTO atendimentoDTO, BindingResult result, 
			HttpServletRequest request){
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(atendimentoDTO.getId())
				.toUri();

		ResponseMarcar response = atendimentoService.marcarAtendimento(atendimentoDTO,
				request, result);

		return ResponseEntity.created(uri).body(response);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView detalheAtendimento(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("atendimento/detalhe");
		AtendimentoDTO atendimento = atendimentoService.buscarAtendimentoPorId(id);
		modelAndView.addObject("atendimento",atendimento);
		return modelAndView;
	}
	
	
}
