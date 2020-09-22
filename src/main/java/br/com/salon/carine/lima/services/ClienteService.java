package br.com.salon.carine.lima.services;

import static br.com.salon.carine.lima.converters.ConvertersCliente.deClienteDTOParaCliente;
import static br.com.salon.carine.lima.converters.ConvertersCliente.deClienteParaClienteDTO;
import static br.com.salon.carine.lima.converters.ConvertersCliente.deListClienteParaListClienteDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersCliente;
import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.MessageDTO;
import br.com.salon.carine.lima.dto.ResponseClienteDTO;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private NextPreviousClienteService nextPreviousClienteService;

	public ResponseClienteDTO cadastrar(ClienteDTO clienteDTO) {

		Cliente cliente = deClienteDTOParaCliente(clienteDTO);

		this.clienteRepository.cadastrar(cliente);

		ResponseClienteDTO response = new ResponseClienteDTO();
		response.setCliente(clienteDTO);
		response.setMessage(new MessageDTO("Cliente", "Cliente cadastrado com sucesso"));

		return response;

	}

	public List<ClienteDTO> listarTodos() {
		List<Cliente> listaCliente = this.clienteRepository.listarTodos();
		List<ClienteDTO> listClienteDTO = deListClienteParaListClienteDTO(listaCliente);
		return listClienteDTO;
	}

	public ClienteDTO remover(Integer id) {

		if (id != null) {
			Cliente cliente = this.clienteRepository.buscarClientePorID(id);
			ClienteDTO clienteProximo = buscarClienteProximoParaAtual(cliente);
			this.clienteRepository.remover(cliente.getId());
			if (clienteProximo != null) {
				return clienteProximo;
			}
		}

		return null;
	}

	public ClienteDTO buscarClienteProximoParaAtual(Cliente clienteAtual) {

		Cliente clienteProximo = this.nextPreviousClienteService.proximo(clienteAtual);
		ClienteDTO clienteDTOProximo = ConvertersCliente.deClienteParaClienteDTO(clienteProximo);

		return clienteDTOProximo;
	}

	public ClienteDTO buscarClienteAnteriorParaAtual(Cliente clienteAtual) {

		Cliente clienteAnterior = this.nextPreviousClienteService.anterior(clienteAtual);
		ClienteDTO clienteDTOAnterior = ConvertersCliente.deClienteParaClienteDTO(clienteAnterior);

		return clienteDTOAnterior;

	}

	public ClienteDTO buscarClientePorId(Integer id) {
		Cliente cliente = this.clienteRepository.buscarClientePorID(id);
		ClienteDTO clienteDTO = deClienteParaClienteDTO(cliente);
		return clienteDTO;
	}

	public ResponseClienteDTO alterarCliente(ClienteDTO clienteDTO) {
		Cliente cliente = deClienteDTOParaCliente(clienteDTO);
		this.clienteRepository.alterarCliente(cliente);

		ResponseClienteDTO response = new ResponseClienteDTO();
		response.setCliente(clienteDTO);

		response.setMessage(new MessageDTO("Cliente", "Cliente Alterado com sucesso"));

		return response;
	}

}
