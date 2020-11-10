package br.com.salon.carine.lima.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.enuns.BandeiraCartao;
import br.com.salon.carine.lima.enuns.TipoEndereco;
import br.com.salon.carine.lima.enuns.TipoPagamento;
import br.com.salon.carine.lima.exceptions.ArgumentNotValidException;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Cliente;
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
	
	@Autowired
	private LojaController controllerLoja;
	
	private Logger logger = Logger.getLogger("br.com.salon.carine.lima.Atendimento");
	
	@Cacheable(value = "listarHTML")
	@RequestMapping(method = RequestMethod.GET, value = "listar")
	public ModelAndView formDetalheAtendimento(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer size) {
		
		this.logger.info("Iniciando busca paginada model");
		
		/* Atualizar First Atendimento*/
		atendimentoService.atualizarFirstId(atendimentoService.idFirstAtendimento());
		/* Atualizar Last Atendimento*/
		atendimentoService.atualizarLastId(atendimentoService.idLastAtendimento());
		
		ModelAndView modelAndView = new ModelAndView("atendimento/lista");
		Page<Atendimento> pageAtendimento = atendimentoService.findPageAtendimento(page, size);
		
		modelAndView.addObject("paginas", pageAtendimento);
		modelAndView.addObject("activeAtendimento", "active");
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "cliente/filter")
	public ResponseEntity<List<Atendimento>> filterAtendimentoPorNome(String nome){
		Page<Atendimento> pageAtendimento = atendimentoService.filtrarAtendimentoPorCliente(nome);
		return ResponseEntity.ok(pageAtendimento.getContent());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "filterData")
	public ResponseEntity<Page<Atendimento>> filterDataAtendimento
		(@Valid FiltroDataAtendimentoDTO filtro, BindingResult result,
				HttpServletRequest request) {	
		
		if(result.hasErrors()) {
			throw new ArgumentNotValidException(result,request);
		}
		
		Page<Atendimento> listaFiltrada = atendimentoService.
				getAtendimentosFilterData(filtro);
		
		return ResponseEntity.ok().body(listaFiltrada);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "formMarcar")
	public ModelAndView formMarcarAtendimento() {		
		
		if(carrinhoService.getQuantidadeTotalItensCarrinho() > 0) {
			List<Cliente> clientes = serviceCliente.listarTodos();
			BigDecimal valorTotalCarrinho = carrinhoService.getValorTotalCarrinho();
			ModelAndView modelAndView = new ModelAndView("atendimento/formMarcar");
			modelAndView.addObject("clientes", clientes);
			modelAndView.addObject("valorTotalCarrinho", valorTotalCarrinho);
			modelAndView.addObject("pagamentos", TipoPagamento.values());
			modelAndView.addObject("bandeiras", BandeiraCartao.values());
			modelAndView.addObject("enderecos", TipoEndereco.values());
			
			return modelAndView;
		}else {
			return controllerLoja.getLoja();
		}
	}
	
	@CacheEvict(value = "listarHTML", allEntries = true)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseMarcar> marcaAtendimento(
			@Valid MarcarAtendimentoDTO atendimentoDTO, BindingResult result, 
			HttpServletRequest request){
		
		if(result.hasErrors()) {
			throw new ArgumentNotValidException(result, request);
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(atendimentoDTO.getId())
				.toUri();

		ResponseMarcar response = atendimentoService.marcarAtendimento(atendimentoDTO,
				request, result);
		
		/* Atualizar First Atendimento*/
		atendimentoService.atualizarFirstId(atendimentoService.idFirstAtendimento());
		/* Atualizar Last Atendimento*/
		atendimentoService.atualizarLastId(atendimentoService.idLastAtendimento());

		return ResponseEntity.created(uri).body(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView detalheAtendimento(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("atendimento/formDetalhar");
		Atendimento atendimento = atendimentoService.buscarAtendimentoPorId(id);
		modelAndView.addObject("atendimento",atendimento);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "next")
	public ModelAndView nextAtendimento (@RequestParam Integer idAtendimentoAtual){
		ModelAndView modelAndView = new ModelAndView("atendimento/formDetalhar");
		Atendimento atendimento = atendimentoService.nextAtendimento(idAtendimentoAtual);
		modelAndView.addObject("atendimento", atendimento);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "previous")
	public ModelAndView previousAtendimento (@RequestParam Integer idAtendimentoAtual){
		ModelAndView modelAndView = new ModelAndView("atendimento/formDetalhar");
		Atendimento atendimento = atendimentoService.previousAtendimento(idAtendimentoAtual);
		modelAndView.addObject("atendimento",atendimento);
		return modelAndView;
	}
	
	@CacheEvict(value = "listarHTML", allEntries = true)
	@RequestMapping(method = RequestMethod.POST, value = "cancelar")
	public ResponseEntity<Message> cancelar(Integer idAtendimentoCancelado) {
		Message message = this.atendimentoService.cancelar(idAtendimentoCancelado);
		
		/* Atualizar First Atendimento*/
		atendimentoService.atualizarFirstId(atendimentoService.idFirstAtendimento());
		/* Atualizar Last Atendimento*/
		atendimentoService.atualizarLastId(atendimentoService.idLastAtendimento());
		
		return ResponseEntity.ok().body(message);
	}
}
