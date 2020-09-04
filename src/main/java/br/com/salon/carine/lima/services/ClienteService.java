package br.com.salon.carine.lima.services;

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

	private Cliente inClienteDTOFromCliente(ClienteDTO clienteDTO) {

		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		cliente.setTelefone(clienteDTO.getTelefone());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setEndereco(clienteDTO.getEndereco());

		return cliente;

	}

}
