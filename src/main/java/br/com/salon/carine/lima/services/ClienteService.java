package br.com.salon.carine.lima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.enuns.TypeMessage;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.repositories.ClienteRepository;
import br.com.salon.carine.lima.util.Message;
import br.com.salon.carine.lima.util.Messenger;

@Service
public class ClienteService implements Messenger {

	@Autowired
	public Message message;

	@Autowired
	private ClienteRepository clienteRepository;

	public void cadastrar(ClienteDTO clienteDTO) {
		Cliente cliente = inClienteDTOFromCliente(clienteDTO);
		this.clienteRepository.cadastrar(cliente);
		emitterMessagesuccess("Cliente cadastrado com sucesso");
	}

	public List<ClienteDTO> listarTodos() {
		List<Cliente> listaCliente = this.clienteRepository.listarTodos();
		List<ClienteDTO> listClienteDTO = inListClienteFromListClienteDTO(listaCliente);
		return listClienteDTO;

	}

	public ClienteDTO remover(Integer id) {

		Cliente cliente = this.clienteRepository.remover(id);
		ClienteDTO clienteDTO = inClienteFromClienteDTO(cliente);
		emitterMessagesuccess("Cliente removido com sucesso");;
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

	@Override
	public void emitterMessagesuccess(String text) {
		this.message.setType(TypeMessage.SUCESSO);
		this.message.setClasse("success");
		this.message.setText(text);
	}

	@Override
	public void emitterMessagedanger(String text) {
		this.message.setType(TypeMessage.ALERTA);
		this.message.setClasse("danger");
		this.message.setText(text);
	}

	@Override
	public void emitterMessageinfo(String text) {
		this.message.setType(TypeMessage.INFO);
		this.message.setClasse("info");
		this.message.setText(text);
	}

	@Override
	public void emitterMessagewarning(String text) {
		this.message.setType(TypeMessage.ATENCAO);
		this.message.setClasse("warning");
		this.message.setText(text);
	}

}
