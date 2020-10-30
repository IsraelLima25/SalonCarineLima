package br.com.salon.carine.lima.services;

import static br.com.salon.carine.lima.converters.ConvertersCliente.deClienteDTOParaCliente;
import static br.com.salon.carine.lima.converters.ConvertersCliente.deClienteParaClienteDTO;
import static br.com.salon.carine.lima.converters.ConvertersCliente.deListClienteParaListClienteDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.repositories.ClienteRepository;
import br.com.salon.carine.lima.repositoriessdp.ClienteRepositorySJPA;
import br.com.salon.carine.lima.response.Message;
import br.com.salon.carine.lima.response.ResponseCliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteRepositorySJPA clienteRepositorySJPA;

	public ResponseCliente cadastrar(ClienteDTO clienteDTO) {

		Cliente cliente = deClienteDTOParaCliente(clienteDTO);

		this.clienteRepository.cadastrar(cliente);

		ResponseCliente response = new ResponseCliente();
		response.setCliente(clienteDTO);
		response.setMessage(new Message("Cliente", "Cliente cadastrado com sucesso"));

		return response;

	}
	
	public Page<Cliente> findPageCliente(Integer page, Integer size) {
		
		if(page > 0) {
		
			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Cliente> pagesCliente = clienteRepositorySJPA.findAll(pageRequest);
			
			int pointInit = page * 5;
			
			for (Cliente cliente : pagesCliente) {
				cliente.setRowNumber(pointInit);
				pointInit++;
			}
			
			return pagesCliente;
			
		}else {
			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Cliente> pagesCliente = clienteRepositorySJPA.findAll(pageRequest);
			
			return pagesCliente;
		}
	}

	public List<ClienteDTO> listarTodos() {
		List<Cliente> listaCliente = this.clienteRepository.listarTodos();
		List<ClienteDTO> listClienteDTO = deListClienteParaListClienteDTO(listaCliente);
		return listClienteDTO;
	}

//	public ClienteDTO remover(Integer id) {
//
//		if (id != null) {
//			Cliente cliente = this.clienteRepository.buscarClientePorID(id);
//			ClienteDTO clienteProximo = buscarClienteProximoParaAtual(cliente);
//			this.clienteRepository.remover(cliente.getId());
//			if (clienteProximo != null) {
//				return clienteProximo;
//			}
//		}
//
//		return null;
//	}
	
	public Page<Cliente> nextPageService(boolean isLast, Integer number) {
		
		if(isLast) {
			PageRequest pageRequest = PageRequest.of(0, 1);
			Page<Cliente> paginaProxima = clienteRepositorySJPA.findAll(pageRequest);
			
			return paginaProxima;
			
		}else {
			PageRequest pageRequest = PageRequest.of(number + 1, 1);
			Page<Cliente> paginaProxima = clienteRepositorySJPA.findAll(pageRequest);
			
			return paginaProxima;
		}
	}
	
	public Page<Cliente> previousPageService(boolean isFirst, Integer number) {
		
		if(isFirst) {			
			int lastPage = (int) clienteRepositorySJPA.count() - 1;
			PageRequest pageRequest = PageRequest.of(lastPage, 1);
			Page<Cliente> paginaAterior = clienteRepositorySJPA.findAll(pageRequest);
			return paginaAterior;
			
		}else {
			PageRequest pageRequest = PageRequest.of(number - 1, 1);
			Page<Cliente> paginaAterior = clienteRepositorySJPA.findAll(pageRequest);
			
			return paginaAterior;
		}
	}


	public ClienteDTO buscarClientePorId(Integer id) {
		Cliente cliente = this.clienteRepository.buscarClientePorID(id);
		ClienteDTO clienteDTO = deClienteParaClienteDTO(cliente);
		return clienteDTO;
	}

	public ResponseCliente alterarCliente(ClienteDTO clienteDTO) {
		Cliente cliente = deClienteDTOParaCliente(clienteDTO);
		this.clienteRepository.alterarCliente(cliente);

		ResponseCliente response = new ResponseCliente();
		response.setCliente(clienteDTO);

		response.setMessage(new Message("Cliente", "Cliente Alterado com sucesso"));

		return response;
	}
	
	public Integer buscarRowPorID(Integer idCliente) {
		
		List<Cliente> findAllClientes = (List<Cliente>) clienteRepositorySJPA.findAll();
		clienteRepository.numerarLinhas(findAllClientes);
				List<Cliente> clientes = findAllClientes.stream()
				.filter(atendimento -> atendimento.getId() == idCliente)
				.collect(Collectors.toList());
				
		return clientes.get(0).getRowNumber();
	}
	
	public Page<Cliente> buscarAtendimentoRowNumber(Integer rowNumber) {
		
		PageRequest pageRequest = PageRequest.of(rowNumber, 1);
		
		Page<Cliente> pageCliente = clienteRepositorySJPA.findAll(pageRequest);
		
		return pageCliente;
	}

	public List<Cliente> filtrarAtendimentoPorNome(String nome) {
		if(!nome.equals("")) {
			List<Cliente> atendimentos = clienteRepository.searchNomeFilterRowNumber(nome);
			
			return atendimentos;
			
		}else {
			
			Page<Cliente> findPageAtendimento = findPageCliente(0, 5);
			return findPageAtendimento.getContent();
		}
	}

}
