package br.com.salon.carine.lima.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.response.Message;
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
	public ModelAndView formDetalheAtendimento(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer size) {
		
		this.logger.info("Iniciando busca paginada model");
		
		ModelAndView modelAndView = new ModelAndView("atendimento/lista");
		Page<Atendimento> pageAtendimento = atendimentoService.findPageAtendimento(page, size);
		modelAndView.addObject("paginas", pageAtendimento);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "cliente/filter")
	public ResponseEntity<List<Atendimento>> filterAtendimentoPorNome(String nome){
		List<Atendimento> pageAtendimento = atendimentoService.filtrarAtendimentoPorCliente(nome);
		return ResponseEntity.ok(pageAtendimento);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "listar/json")
	public ResponseEntity<Page<Atendimento>> formDetalheAtendimentoJSON(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer size,
			HttpServletRequest request) {
		
		this.logger.info("Iniciando busca paginada JSON");
		
		Page<Atendimento> pageAtendimento = atendimentoService.findPageAtendimento(page, size);
		
		return ResponseEntity.ok().body(pageAtendimento);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "filterData")
	public ResponseEntity<Page<Atendimento>> filterDataAtendimento
		(FiltroDataAtendimentoDTO filtro) {	
		
		Page<Atendimento> listaFiltrada = atendimentoService.
				getAtendimentosFilterData(filtro);
		
		return ResponseEntity.ok().body(listaFiltrada);
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
			@Valid MarcarAtendimentoDTO atendimentoDTO, BindingResult result, 
			HttpServletRequest request){
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(atendimentoDTO.getId())
				.toUri();

		ResponseMarcar response = atendimentoService.marcarAtendimento(atendimentoDTO,
				request, result);

		return ResponseEntity.created(uri).body(response);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{rowNumber}")
	public ModelAndView detalheAtendimento(@PathVariable Integer rowNumber) {
		ModelAndView modelAndView = new ModelAndView("atendimento/detalhe");
		Page<Atendimento> atendimento = atendimentoService.buscarAtendimentoRowNumber(rowNumber);
		modelAndView.addObject("atendimento",atendimento.getContent().get(0));
		modelAndView.addObject("page", atendimento);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "next")
	public ModelAndView nextPage (@RequestParam boolean isLast, @RequestParam Integer number){
		ModelAndView modelAndView = new ModelAndView("atendimento/detalhe");
		Page<Atendimento> pagina = atendimentoService.nextPageService(isLast, number);
		modelAndView.addObject("atendimento",pagina.getContent().get(0));
		modelAndView.addObject("page", pagina);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "previous")
	public ModelAndView previousPage (@RequestParam boolean isFirst, @RequestParam Integer number){
		ModelAndView modelAndView = new ModelAndView("atendimento/detalhe");
		Page<Atendimento> pagina = atendimentoService.previousPageService(isFirst, number);
		modelAndView.addObject("atendimento",pagina.getContent().get(0));
		modelAndView.addObject("page", pagina);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "cancelar")
	public ResponseEntity<Message> cancelar(Integer idAtendimentoCancelado) {
		Message message = this.atendimentoService.cancelar(idAtendimentoCancelado);
		return ResponseEntity.ok().body(message);
	}
}
