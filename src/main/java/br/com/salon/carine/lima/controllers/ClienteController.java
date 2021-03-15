package br.com.salon.carine.lima.controllers;

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

import br.com.salon.carine.lima.conf.ConfGeneric;
import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.exceptions.ArgumentNotValidException;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.response.ResponseCliente;
import br.com.salon.carine.lima.services.ClienteService;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteService serviceCliente;
	
	private Logger logger = Logger.getLogger("br.com.salon.carine.lima.Cliente");

	@RequestMapping(method = RequestMethod.GET, value = "cadastro")
	public ModelAndView formCadastroCliente() {
		ModelAndView modelAndView = new ModelAndView("cliente/formCadastro");
		modelAndView.addObject("activeCliente", "active");
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="relatorios")
	public ModelAndView reportRunCliente() {
		ModelAndView modelAndView = new ModelAndView("cliente/reportRun");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseCliente> cadastrarCliente(@Valid ClienteDTO clienteDTO,			
			BindingResult result, HttpServletRequest request) {

		this.logger.info("Cadastrando Cliente");
		
		if(result.hasErrors()) {
			this.logger.info("Formulario cliente inválido");
			throw new ArgumentNotValidException(result,request);
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId())
				.toUri();

		ResponseCliente response = this.serviceCliente.cadastrar(clienteDTO);

		return ResponseEntity.created(uri).body(response);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/alterar")
	public ResponseEntity<ResponseCliente> alterarCliente(@Valid ClienteDTO clienteDTO,
			BindingResult result, HttpServletRequest request) {
		
		this.logger.info("Cadastrando Cliente");
		
		if(result.hasErrors()) {
			this.logger.info("Formulario cliente inválido");
			throw new ArgumentNotValidException(result,request);
		}

		this.logger.info("Alterando Cliente");
		ResponseCliente response = this.serviceCliente.alterarCliente(clienteDTO);

		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "listar")
	public ModelAndView buscaPaginadaCliente(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer size) {
		
		this.logger.info("Iniciando busca paginada HTML");
		
		/* Atualizar First Cliente*/
		serviceCliente.atualizarFirstId(serviceCliente.idFirstCliente());
		/* Atualizar Last Cliente*/
		serviceCliente.atualizarLastId(serviceCliente.idLastCliente());
		
		ModelAndView modelAndView = new ModelAndView("cliente/lista");
		Page<Cliente> pageAtendimento = serviceCliente.findPageCliente(page, size);
		modelAndView.addObject("paginas", pageAtendimento);
		modelAndView.addObject("activeCliente", "active");
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/remover/{id}")
	public ResponseEntity<Void> removerCliente(@PathVariable("id") Integer id) {
		
		try {
			serviceCliente.remover(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "next")
	public ModelAndView nextCliente (@RequestParam Integer idClienteAtual){
		ModelAndView modelAndView = new ModelAndView("cliente/formDetalhar");
		Cliente cliente = serviceCliente.nextCliente(idClienteAtual);
		modelAndView.addObject("cliente", cliente);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "previous")
	public ModelAndView previousCliente (@RequestParam Integer idClienteAtual){
		ModelAndView modelAndView = new ModelAndView("cliente/formDetalhar");
		Cliente cliente = serviceCliente.previousCliente(idClienteAtual);
		modelAndView.addObject("cliente",cliente);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", 
					name = "detalharCliente")
	public ModelAndView detalheCliente(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("cliente/formDetalhar");
		Cliente cliente = serviceCliente.buscarClientePorId(id);
		modelAndView.addObject("cliente",cliente);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/filter")
	public ResponseEntity<List<Cliente>> filterAtendimentoPorNome(String nome){
		List<Cliente> pageCliente = serviceCliente.filtrarAtendimentoPorNome(nome);
		return ResponseEntity.ok(pageCliente);
	}

}
