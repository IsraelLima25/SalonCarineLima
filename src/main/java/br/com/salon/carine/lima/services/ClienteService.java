package br.com.salon.carine.lima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void cadastrar(ClienteDTO clienteDTO) {
		Cliente cliente = inClienteDTOFromCliente(clienteDTO);
		this.clienteRepository.cadastrar(cliente);

	}

	public List<ClienteDTO> listarTodos() {
		List<Cliente> listaCliente = this.clienteRepository.listarTodos();
		List<ClienteDTO> listClienteDTO = inListClienteFromListClienteDTO(listaCliente);
		return listClienteDTO;

	}

	public ClienteDTO remover(Integer id) {

		Cliente cliente = this.clienteRepository.remover(id);
		ClienteDTO clienteDTO = inClienteFromClienteDTO(cliente);

		return clienteDTO;
	}

	private Cliente inClienteDTOFromCliente(ClienteDTO clienteDTO) {

		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		cliente.setTelefone(clienteDTO.getTelefone());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setEndereco(clienteDTO.getEndereco());

		return cliente;
	}

	private ClienteDTO inClienteFromClienteDTO(Cliente cliente) {

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setTelefone(cliente.getTelefone());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setEndereco(cliente.getEndereco());

		return clienteDTO;

	}

	private List<ClienteDTO> inListClienteFromListClienteDTO(List<Cliente> list) {

		List<ClienteDTO> listaClienteDTO = new ArrayList<>();
		ClienteDTO clienteDTO;

		for (Cliente cliente : list) {
			clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(),
					cliente.getEndereco());
			listaClienteDTO.add(clienteDTO);
		}

		return listaClienteDTO;

	}

}
