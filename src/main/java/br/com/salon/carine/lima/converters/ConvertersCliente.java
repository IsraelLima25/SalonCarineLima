package br.com.salon.carine.lima.converters;

import java.util.ArrayList;
import java.util.List;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.EnderecoDTO;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.models.Endereco;

public class ConvertersCliente {
	
	
	public static Cliente deClienteDTOParaCliente(ClienteDTO clienteDTO) {

		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setTelefone(clienteDTO.getTelefone());
		cliente.setEmail(clienteDTO.getEmail());
		
		Endereco endereco = ConvertersEndereco.deEnderecoDTOParaEndereco(clienteDTO.getEndereco());

		cliente.setEndereco(endereco);

		return cliente;
	}
	
	public static ClienteDTO deClienteParaClienteDTO(Cliente cliente) {

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setTelefone(cliente.getTelefone());
		clienteDTO.setEmail(cliente.getEmail());
		
		EnderecoDTO enderecoDTO = ConvertersEndereco.deEnderecoParaEnderecoDTO(cliente.getEndereco());

		clienteDTO.setEndereco(enderecoDTO);

		return clienteDTO;

	}

	public static List<ClienteDTO> deListClienteParaListClienteDTO(List<Cliente> list) {

		List<ClienteDTO> listaClienteDTO = new ArrayList<>();
		ClienteDTO clienteDTO;

		for (Cliente cliente : list) {
			
			EnderecoDTO enderecoDTO = ConvertersEndereco.deEnderecoParaEnderecoDTO(cliente.getEndereco());

			clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), 
					cliente.getTelefone(), enderecoDTO);

			listaClienteDTO.add(clienteDTO);
		}

		return listaClienteDTO;

	}
	
	

}
