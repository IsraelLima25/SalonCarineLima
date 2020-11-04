package br.com.salon.carine.lima.services;

import static br.com.salon.carine.lima.converters.ConvertersCliente.deClienteDTOParaCliente;
import static br.com.salon.carine.lima.converters.ConvertersCliente.deListClienteParaListClienteDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.repositoriessdp.ClienteRepositorySJPA;
import br.com.salon.carine.lima.response.Message;
import br.com.salon.carine.lima.response.ResponseCliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepositorySJPA clienteRepositorySJPA;
	
	private Integer idLastCliente = 0;
	
	private Integer idFirstCliente = 0;
	
	public ResponseCliente cadastrar(ClienteDTO clienteDTO) {

		Cliente cliente = deClienteDTOParaCliente(clienteDTO);

		this.clienteRepositorySJPA.save(cliente);

		ResponseCliente response = new ResponseCliente();
		response.setCliente(clienteDTO);
		response.setMessage(new Message("Cliente", "Cliente cadastrado com sucesso"));

		return response;

	}
	
	public Page<Cliente> findPageCliente(Integer page, Integer size) {
		
		if(page > 0) {
		
			PageRequest pageRequest = PageRequest.of(page, size);
			
			Page<Cliente> pagesCliente = clienteRepositorySJPA.findAll(pageRequest);
			
			return pagesCliente;
			
		}else {
			PageRequest pageRequest = PageRequest.of(page, size);
			
			Page<Cliente> pagesCliente = clienteRepositorySJPA.findAll(pageRequest);
			
			return pagesCliente;
		}
	}

	public List<ClienteDTO> listarTodos() {
		List<Cliente> listaCliente = clienteRepositorySJPA.findAll();
		List<ClienteDTO> listClienteDTO = deListClienteParaListClienteDTO(listaCliente);
		return listClienteDTO;
	}

	public void remover(Integer id) {
		
		Optional<Cliente> cliente = this.clienteRepositorySJPA.findById(id);
		
		if (cliente.isPresent()) {
			Cliente clienteBusca = cliente.get();
			clienteRepositorySJPA.delete(clienteBusca);
		}
	}
	
	public Cliente nextCliente(Integer idClienteAtual) {
		
		if(isLastCliente(idClienteAtual)) {
			return firstCliente();
		}else {
			Integer idClienteProximo = clienteRepositorySJPA.idClienteProximo(idClienteAtual);
			return clienteRepositorySJPA.findById(idClienteProximo).get();
		}
	}
	
	public Cliente previousCliente(Integer idClienteAtual) {
		
		if(isFirstCliente(idClienteAtual)) {
			return lastCliente();
		}else {
			Integer idClienteAnterior = clienteRepositorySJPA.idClienteAnterior(idClienteAtual);
			return clienteRepositorySJPA.findById(idClienteAnterior).get();
		}
	}
	
	public boolean isLastCliente(Integer idClienteAtual) {
		
		if(idLastCliente == idClienteAtual) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isFirstCliente(Integer idClienteAtual) {
		
		if(idFirstCliente == idClienteAtual) {
			return true;
		}else {
			return false;
		}
	}
	
	public Cliente firstCliente() {
		
		Integer idFirstCliente = clienteRepositorySJPA.idFirstCliente();
		Optional<Cliente> optionalCliente = clienteRepositorySJPA.findById(idFirstCliente);
		if(optionalCliente.isPresent()) {
			return optionalCliente.get();
		}
		
		return null;
	}
	
	public Cliente lastCliente() {
		Integer idlastCliente = clienteRepositorySJPA.idlastCliente();
		Optional<Cliente> optionalCliente = clienteRepositorySJPA.findById(idlastCliente);
		if(optionalCliente.isPresent()) {
			return optionalCliente.get();
		}
		
		return null;
	}
	
	public Integer idLastCliente() {
		return clienteRepositorySJPA.idlastCliente();
	}
	
	public Integer idFirstCliente() {
		return clienteRepositorySJPA.idFirstCliente();
	}

	public Cliente buscarClientePorId(Integer id) {
		if(clienteRepositorySJPA.findById(id).isPresent()) {
			return clienteRepositorySJPA.findById(id).get();
		}else {
			return null;
		}
	}

	public ResponseCliente alterarCliente(ClienteDTO clienteDTO) {
		Cliente cliente = deClienteDTOParaCliente(clienteDTO);
		clienteRepositorySJPA.save(cliente);

		ResponseCliente response = new ResponseCliente();
		response.setCliente(clienteDTO);

		response.setMessage(new Message("Cliente", "Cliente Alterado com sucesso"));

		return response;
	}

	public List<Cliente> filtrarAtendimentoPorNome(String nome) {
		if(!nome.equals("")) {
			List<Cliente> clientes = clienteRepositorySJPA.searchNomeFilter(nome);
			
			return clientes;
			
		}else {
			
			Page<Cliente> findPageAtendimento = findPageCliente(0, 5);
			return findPageAtendimento.getContent();
		}
	}

	public void atualizarLastId(Integer id) {
		idLastCliente = id;
	}

	public void atualizarFirstId(Integer id) {
		idFirstCliente = id;
	}
}
