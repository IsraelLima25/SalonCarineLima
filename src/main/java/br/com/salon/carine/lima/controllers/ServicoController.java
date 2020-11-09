package br.com.salon.carine.lima.controllers;

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

import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.exceptions.ArgumentNotValidException;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.response.ResponseServico;
import br.com.salon.carine.lima.services.ServicoService;

@Controller
@RequestMapping(value = "/servico")
public class ServicoController {

	@Autowired
	public ServicoService servicoService;

	private Logger logger = Logger.getLogger("br.com.salon.carine.lima.model.Servico");

	@RequestMapping(method = RequestMethod.GET, value = "cadastro")
	public ModelAndView formCadastroServico() {
		ModelAndView modelAndView = new ModelAndView("servico/formCadastro");
		return modelAndView;
	}
	
	@CacheEvict(value = "listaServicoPage", allEntries = true)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseServico> cadastroServico(@Valid ServicoDTO servicoDTO,
			BindingResult result, HttpServletRequest request) {

		logger.info("Cadastrando Servico");
		
		if(result.hasErrors()) {
			logger.info("Formulario cliente inválido");
			throw new ArgumentNotValidException(result, request);
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servicoDTO.getId())
				.toUri();

		ResponseServico response = servicoService.cadastrar(servicoDTO);

		return ResponseEntity.created(uri).body(response);
	}
	
	@Cacheable(value = "listaServicoPage")
	@RequestMapping(method = RequestMethod.GET, value = "listar")
	public ModelAndView formDetalheAtendimento(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer size) {
		
		logger.info("Iniciando busca paginada model");
		
		logger.info("Atualizando First Serviço e Last Serviço");
		
		/* Atualizar First Servico*/
		servicoService.atualizarFirstId(servicoService.idFirstServico());
		/* Atualizar Last Servico*/
		servicoService.atualizarLastId(servicoService.idLastServico());
		
		ModelAndView modelAndView = new ModelAndView("servico/lista");
		Page<Servico> pageServico = servicoService.findPageServico(page, size);
		modelAndView.addObject("paginas", pageServico);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/filter")
	public ResponseEntity<List<Servico>> filterServicoPorDescricao(String nome){
		List<Servico> pageServico = servicoService.filtrarServicoPorDescricao(nome);
		return ResponseEntity.ok(pageServico);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "next")
	public ModelAndView nextServico (@RequestParam Integer idServicoAtual){
		ModelAndView modelAndView = new ModelAndView("servico/formDetalhar");
		Servico servico = servicoService.nextServico(idServicoAtual);
		modelAndView.addObject("servico",servico);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "previous")
	public ModelAndView previousServico (@RequestParam Integer idServicoAtual){
		ModelAndView modelAndView = new ModelAndView("servico/formDetalhar");
		Servico servico = servicoService.previousServico(idServicoAtual);
		modelAndView.addObject("servico",servico);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView detalheServico(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("servico/formDetalhar");
		Servico servico = servicoService.buscarServicoPorId(id);
		modelAndView.addObject("servico",servico);
		return modelAndView;
	}
	
	@CacheEvict(value = "listaServicoPage", allEntries = true)
	@RequestMapping(method = RequestMethod.DELETE, value = "/remover/{id}")
	public ResponseEntity<Void> removerCliente(@PathVariable("id") Integer id) {
		
		try {
			servicoService.remover(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CacheEvict(value = "listaServicoPage", allEntries = true)
	@RequestMapping(method = RequestMethod.POST, value = "alterar")
	public ResponseEntity<ResponseServico> alterarServico(@Valid ServicoDTO servicoDTO,
			BindingResult result, HttpServletRequest request) {

		logger.info("Alterando Servico");
		
		if(result.hasErrors()) {
			logger.info("Formulario cliente inválido");
			throw new ArgumentNotValidException(result, request);
		}
		
		ResponseServico response = servicoService.alterarServico(servicoDTO);

		return ResponseEntity.ok().body(response);
	}

}
