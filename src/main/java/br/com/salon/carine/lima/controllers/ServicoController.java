package br.com.salon.carine.lima.controllers;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.salon.carine.lima.dto.ResponseServicoDTO;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.exceptions.ArgumentNotValidException;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.services.ServicoService;

@Controller
@RequestMapping(value = "servico")
public class ServicoController {

	@Autowired
	public ServicoService servicoService;

	private Logger logger = Logger.getLogger("br.com.salon.carine.lima.model.Servico");

	@RequestMapping(method = RequestMethod.GET, value = "cadastro")
	public ModelAndView formCadastroServico() {
		ModelAndView modelAndView = new ModelAndView("servico/formCadastro");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseServicoDTO> cadastroServico(@Valid ServicoDTO servicoDTO,
			BindingResult result, HttpServletRequest request) {

		this.logger.info("Cadastrando Servico");
		
		if(result.hasErrors()) {
			this.logger.info("Formulario cliente inválido");
			throw new ArgumentNotValidException(result, request);
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servicoDTO.getId())
				.toUri();

		ResponseServicoDTO response = this.servicoService.cadastrar(servicoDTO);

		return ResponseEntity.created(uri).body(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	public ModelAndView listarServicos() {
		
		ModelAndView modelAndView = new ModelAndView("servico/lista");
		List<ServicoDTO> lista = this.servicoService.listarTodos();
		modelAndView.addObject("lista", lista);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView detalharServico(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("servico/formDetalhar");
		ServicoDTO servicoDTO = this.servicoService.buscarServicoPorId(id);
		modelAndView.addObject("servico", servicoDTO);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/remover/{id}")
	public ResponseEntity<ResponseServicoDTO> removerCliente(@PathVariable("id") Integer id) {
		ServicoDTO servicoProximo = this.servicoService.remover(id);
		ResponseServicoDTO response = new ResponseServicoDTO();
		response.setServico(servicoProximo);
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "proximo/{id}")
	public ResponseEntity<ResponseServicoDTO> servicoProximo(@PathVariable("id") Integer id) {
		Servico servico = new Servico();
		servico.setId(id);
		ServicoDTO servicoProximo = this.servicoService.buscarServicoProximoParaAtual(servico);
		ResponseServicoDTO response = new ResponseServicoDTO();
		response.setServico(servicoProximo);

		return ResponseEntity.ok().body(response);

	}

	@RequestMapping(method = RequestMethod.GET, value = "anterior/{id}")
	public ResponseEntity<ResponseServicoDTO> servicoAnterior(@PathVariable("id") Integer id) {
		Servico servico = new Servico();
		servico.setId(id);
		ServicoDTO servicoProximo = this.servicoService.buscarServicoAnteriorParaAtual(servico);
		ResponseServicoDTO response = new ResponseServicoDTO();
		response.setServico(servicoProximo);

		return ResponseEntity.ok().body(response);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "alterar")
	public ResponseEntity<ResponseServicoDTO> alterarServico(ServicoDTO servicoDTO,
			BindingResult result, HttpServletRequest request) {

		this.logger.info("Alterando Servico");
		
		ResponseServicoDTO response = this.servicoService.alterarServico(servicoDTO);

		return ResponseEntity.ok().body(response);
	}

}
