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

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.exceptions.ArgumentNotValidException;
import br.com.salon.carine.lima.models.Atendimento;
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

	@RequestMapping(method = RequestMethod.POST, value = "alterar")
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
		
		this.logger.info("Iniciando busca paginada model");
		
		ModelAndView modelAndView = new ModelAndView("cliente/lista");
		Page<Cliente> pageAtendimento = serviceCliente.findPageCliente(page, size);
		modelAndView.addObject("paginas", pageAtendimento);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "listar/json")
	public ResponseEntity<Page<Cliente>> formDetalheAtendimentoJSON(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "5") Integer size,
			HttpServletRequest request) {
		
		this.logger.info("Iniciando busca paginada JSON");
		
		Page<Cliente> pageCliente = serviceCliente.findPageCliente(page, size);
		
		return ResponseEntity.ok().body(pageCliente);
	}

//	@RequestMapping(method = RequestMethod.DELETE, value = "/remover/{id}")
//	public ResponseEntity<ResponseCliente> removerCliente(@PathVariable("id") Integer id) {
//		//ClienteDTO clienteProximo = this.serviceCliente.remover(id);
//		ResponseCliente response = new ResponseCliente();
//		response.setCliente(clienteProximo);
//		return ResponseEntity.ok().body(response);
//	}
	
	@RequestMapping(method = RequestMethod.GET, value = "next")
	public ModelAndView nextPage (@RequestParam boolean isLast, @RequestParam Integer number){
		ModelAndView modelAndView = new ModelAndView("cliente/formDetalhar");
		Page<Cliente> pagina = serviceCliente.nextPageService(isLast, number);
		modelAndView.addObject("cliente",pagina.getContent().get(0));
		modelAndView.addObject("page", pagina);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "previous")
	public ModelAndView previousPage (@RequestParam boolean isFirst, @RequestParam Integer number){
		ModelAndView modelAndView = new ModelAndView("cliente/formDetalhar");
		Page<Cliente> pagina = serviceCliente.previousPageService(isFirst, number);
		modelAndView.addObject("cliente",pagina.getContent().get(0));
		modelAndView.addObject("page", pagina);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", 
					name = "detalharCliente")
	public ModelAndView detalheCliente(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("cliente/formDetalhar");
		Integer rowNumber = serviceCliente.buscarRowPorID(id);
		Page<Cliente> cliente = serviceCliente.buscarAtendimentoRowNumber(rowNumber);
		modelAndView.addObject("cliente",cliente.getContent().get(0));
		modelAndView.addObject("page", cliente);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/filter")
	public ResponseEntity<List<Cliente>> filterAtendimentoPorNome(String nome){
		List<Cliente> pageCliente = serviceCliente.filtrarAtendimentoPorNome(nome);
		return ResponseEntity.ok(pageCliente);
	}

}
