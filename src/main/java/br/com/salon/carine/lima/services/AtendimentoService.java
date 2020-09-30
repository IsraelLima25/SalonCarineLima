package br.com.salon.carine.lima.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersCliente;
import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.models.Especie;
import br.com.salon.carine.lima.models.Pagamento;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;
import br.com.salon.carine.lima.repositories.AtendimentoRepository;
import br.com.salon.carine.lima.repositories.EnderecoRepository;
import br.com.salon.carine.lima.repositories.ServicoItemCarrinhoRepository;

@Service
public class AtendimentoService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private CarrinhoService carrinhoService;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private ServicoItemCarrinhoRepository itemRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public void marcarAtendimento(MarcarAtendimentoDTO atendimentoDTO) {

		Cliente clienteAtendimento = getClienteAtendimento(atendimentoDTO.
				getCliente());
		Endereco enderecoAtendimento = getEnderecoAtendimento(atendimentoDTO);
		BigDecimal valorTotalCarrinhoAtendimento = carrinhoService
				.getValorTotalCarrinho();
		LocalDateTime dataHoraAtendimento = getDataHoraAtendimento
				(atendimentoDTO.getData(), atendimentoDTO.getHora());
		BigDecimal desconto = atendimentoDTO.getDesconto();
		BigDecimal taxa = atendimentoDTO.getTaxa();
		
		List<ServicoItemCarrinho> itensCarrinho = getItensCarrinho();
		
		Atendimento atendimento = builderAtendimento(itensCarrinho, clienteAtendimento, 
				enderecoAtendimento, valorTotalCarrinhoAtendimento, 
				dataHoraAtendimento, desconto, taxa);	
		
		Pagamento pagamentoAtendimento = getFormaPagamento(atendimentoDTO.
				getFormaPagamento(), atendimento);
		
		atendimento.setPagamento(pagamentoAtendimento);
		
		atendimentoRepository.marcarAtendimento(atendimento);
		
		for (ServicoItemCarrinho servicoItemCarrinho : itensCarrinho) {
			servicoItemCarrinho.setAtendimento(atendimento);
			itemRepository.salvarItem(servicoItemCarrinho);
		}
		
		esvaziarCarrinho();
	}
	
	private void esvaziarCarrinho() {
		carrinhoService.esvaziarCarrinho();
	}
	
	private List<ServicoItemCarrinho> getItensCarrinho(){
		
		 List<ServicoItemCarrinho> itensCarrinho = new ArrayList<>();
		
		 List<ServicoItemCarrinhoDTO> itensCarrinhoDTO = carrinhoService.
				 finalizarAtendimentoItensCarrinho();
		 
		 for (ServicoItemCarrinhoDTO servicoItemCarrinhoDTO : itensCarrinhoDTO) {
			 
			ServicoItemCarrinho servicoItemCarrinho = new ServicoItemCarrinho();
			
			ServicoDTO servicoDTO = servicoService.buscarServicoPorId(servicoItemCarrinhoDTO.getServicoDTO().
					getId());
			
			Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);
			
			servicoItemCarrinho.setServico(servico);
			
			servicoItemCarrinho.setQuantidade(servicoItemCarrinhoDTO.getQuantidade());
			
			itensCarrinho.add(servicoItemCarrinho);
		 }
		 
		 return itensCarrinho;

	}
	
	private Atendimento builderAtendimento(List<ServicoItemCarrinho> servicosItemCarrinho,
			Cliente cliente, Endereco endereco,	BigDecimal valorTotal, LocalDateTime dataHora,
			BigDecimal desconto, BigDecimal taxa) {
		
		Atendimento atendimento = new Atendimento();
		
		atendimento.setCliente(cliente);
		atendimento.setEndereco(endereco);
		atendimento.setValorTotal(valorTotal);
		atendimento.setDataHora(dataHora);
		atendimento.setStatus(StatusAtendimento.PENDENTE);
		atendimento.setDesconto(desconto);
		atendimento.setTaxa(taxa);
		
		return atendimento;
		
	}
	
	private Endereco getEnderecoAtendimento(MarcarAtendimentoDTO marcarAtendimentoDTO) {
		
		if(marcarAtendimentoDTO.getTipoEndereco().equals("endereco-cliente")) {
			Cliente cliente = getClienteAtendimento(marcarAtendimentoDTO.getCliente());
			Endereco endereco = enderecoRepository.buscarEnderecoPorId(cliente.getId());
			return endereco;
		}
		
		return null;
	}

	private Pagamento getFormaPagamento(String formaPagamento, 
			Atendimento atendimento) {

		if (formaPagamento.equals("carteira")) {
			Especie pagamentoEspecie = new Especie();
			return pagamentoEspecie;
		}

		return null;
	}

	private Cliente getClienteAtendimento(Integer idCliente) {
		ClienteDTO clienteDTO = clienteService.buscarClientePorId(idCliente);
		Cliente cliente = ConvertersCliente.deClienteDTOParaCliente(clienteDTO);
		return cliente;
	}

	private LocalDateTime getDataHoraAtendimento(String data, String hora) {

		String[] date = data.split("-");
		String[] hour = hora.split(":");

		LocalDate dataAtendimento = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
				Integer.parseInt(date[2]));

		LocalTime horaAtendimeno = LocalTime.of(Integer.parseInt(hour[0]), Integer.parseInt(hour[1]));

		LocalDateTime dataHoraAtendimento = LocalDateTime.of(dataAtendimento, horaAtendimeno);

//		String format = dataHoraAtendimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		return dataHoraAtendimento;

	}
}
