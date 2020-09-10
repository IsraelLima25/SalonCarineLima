package br.com.salon.carine.lima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.EnderecoDTO;
import br.com.salon.carine.lima.dto.MessageDTO;
import br.com.salon.carine.lima.dto.ResponseClienteDTO;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoService enderecoService;

	public ResponseClienteDTO cadastrar(ClienteDTO clienteDTO) {
		Cliente cliente = inClienteDTOFromCliente(clienteDTO);
		this.clienteRepository.cadastrar(cliente);

		ResponseClienteDTO response = new ResponseClienteDTO();
		response.setCliente(clienteDTO);
		response.setMessage(new MessageDTO("Cliente", "Cliente cadastrado com sucesso"));

		return response;

	}

	public List<ClienteDTO> listarTodos() {
		List<Cliente> listaCliente = this.clienteRepository.listarTodos();
		List<ClienteDTO> listClienteDTO = inListClienteFromListClienteDTO(listaCliente);
		return listClienteDTO;
	}

	public ClienteDTO remover(Integer id) {

		if (id != null) {
			ClienteDTO clienteDTO = new ClienteDTO();
			Cliente cliente = this.clienteRepository.buscarClientePorID(id);
			Cliente clienteProximo = this.clienteRepository.buscarClienteProximoParaAtual(cliente);
			this.clienteRepository.remover(cliente.getId());
			if (clienteProximo != null) {
				clienteDTO = inClienteFromClienteDTO(clienteProximo);
				return clienteDTO;
			}
		}

		return null;
	}

	public ClienteDTO buscarClientePorId(Integer id) {
		Cliente cliente = this.clienteRepository.buscarClientePorID(id);
		ClienteDTO clienteDTO = inClienteFromClienteDTO(cliente);
		return clienteDTO;
	}

	public ResponseClienteDTO alterarCliente(ClienteDTO clienteDTO) {
		Cliente cliente = inClienteDTOFromCliente(clienteDTO);
		this.clienteRepository.alterarCliente(cliente);

		ResponseClienteDTO response = new ResponseClienteDTO();
		response.setCliente(clienteDTO);

		response.setMessage(new MessageDTO("Cliente", "Cliente Alterado com sucesso"));

		return response;
	}

	/* Coverter's */

	private Cliente inClienteDTOFromCliente(ClienteDTO clienteDTO) {

		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setTelefone(clienteDTO.getTelefone());
		cliente.setEmail(clienteDTO.getEmail());

		Endereco endereco = this.enderecoService.inEnderecoDTOFromEndereco(clienteDTO.getEndereco());

		cliente.setEndereco(endereco);

		return cliente;
	}

	private ClienteDTO inClienteFromClienteDTO(Cliente cliente) {

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setTelefone(cliente.getTelefone());
		clienteDTO.setEmail(cliente.getEmail());

		EnderecoDTO enderecoDTO = this.enderecoService.inEnderecoFromEnderecoDTO(cliente.getEndereco());

		clienteDTO.setEndereco(enderecoDTO);

		return clienteDTO;

	}

	private List<ClienteDTO> inListClienteFromListClienteDTO(List<Cliente> list) {

		List<ClienteDTO> listaClienteDTO = new ArrayList<>();
		ClienteDTO clienteDTO;

		for (Cliente cliente : list) {

			EnderecoDTO enderecoDTO = this.enderecoService.inEnderecoFromEnderecoDTO(cliente.getEndereco());

			clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(),
					enderecoDTO);

			listaClienteDTO.add(clienteDTO);
		}

		return listaClienteDTO;

	}

}
