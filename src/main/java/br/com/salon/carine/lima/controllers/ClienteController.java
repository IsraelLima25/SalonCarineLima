package br.com.salon.carine.lima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.services.ClienteService;

@Controller
@RequestMapping(value = "cliente")
public class ClienteController {

	@Autowired
	private ClienteService serviceCliente;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView formCadastroCliente() {
		ModelAndView modelAndView = new ModelAndView("cliente/formCadastro");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastrarCliente(ClienteDTO clienteDTO, RedirectAttributes redirectAttributes) {
		this.serviceCliente.cadastrar(clienteDTO);
		redirectAttributes.addFlashAttribute("message", this.serviceCliente.message);

		return new ModelAndView("redirect:cliente/listar");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	public ModelAndView listarClientes() {
		ModelAndView modelAndView = new ModelAndView("cliente/lista");
		List<ClienteDTO> lista = this.serviceCliente.listarTodos();
		modelAndView.addObject("lista", lista);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/remover/{id}")
	@ResponseBody
	public ClienteDTO removerCliente(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		ClienteDTO clienteRemovido = this.serviceCliente.remover(id);		
		clienteRemovido.setMensagem(this.serviceCliente.message);
		return clienteRemovido;
	}

}
