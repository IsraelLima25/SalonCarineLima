package br.com.salon.carine.lima.controllers;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.ResponseClienteDTO;
import br.com.salon.carine.lima.services.ClienteService;

@Controller
@RequestMapping(value = "cliente")
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
	public ResponseEntity<ResponseClienteDTO> cadastrarCliente(ClienteDTO clienteDTO) {
		this.logger.info("Cadastrando Cliente");

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId())
				.toUri();

		ResponseClienteDTO response = this.serviceCliente.cadastrar(clienteDTO);
		return ResponseEntity.created(uri).body(response);

	}

	@RequestMapping(method = RequestMethod.POST, value = "alterar")
	public ResponseEntity<ResponseClienteDTO> alterarCliente(ClienteDTO clienteDTO) {

		this.logger.info("Alterando Cliente");
		ResponseClienteDTO response = this.serviceCliente.alterarCliente(clienteDTO);

		return ResponseEntity.ok().body(response);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	public ModelAndView listarClientes() {
		ModelAndView modelAndView = new ModelAndView("cliente/lista");
		List<ClienteDTO> lista = this.serviceCliente.listarTodos();
		modelAndView.addObject("lista", lista);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/remover/{id}")
	public ResponseEntity<ResponseClienteDTO> removerCliente(@PathVariable("id") Integer id) {
		ClienteDTO clienteProximo = this.serviceCliente.remover(id);		
		ResponseClienteDTO response = new ResponseClienteDTO();
		response.setCliente(clienteProximo);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView detalharCliente(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cliente/formDetalhar");
		ClienteDTO clienteDTO = this.serviceCliente.buscarClientePorId(id);
		modelAndView.addObject("cliente", clienteDTO);

		return modelAndView;
	}

}
