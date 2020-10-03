package br.com.salon.carine.lima.services;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.com.salon.carine.lima.converters.ConvertersAtendimento;
import br.com.salon.carine.lima.converters.ConvertersCliente;
import br.com.salon.carine.lima.converters.ConvertersDate;
import br.com.salon.carine.lima.converters.ConvertersEndereco;
import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.AtendimentoListaDTO;
import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.enuns.BandeiraCartao;
import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.models.Credito;
import br.com.salon.carine.lima.models.Debito;
import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.models.Especie;
import br.com.salon.carine.lima.models.Pagamento;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;
import br.com.salon.carine.lima.repositories.AtendimentoRepository;
import br.com.salon.carine.lima.repositories.EnderecoRepository;
import br.com.salon.carine.lima.repositories.ServicoItemCarrinhoRepository;
import br.com.salon.carine.lima.response.ResponseMarcar;

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

	@Autowired
	private ValidationFormAtendimentoService validationFormAtendimento;

	@Autowired
	private EnderecoService enderecoService;

	public ResponseMarcar marcarAtendimento(MarcarAtendimentoDTO atendimentoDTO, HttpServletRequest request,
			BindingResult result) {

		Cliente clienteAtendimento = getClienteAtendimento(atendimentoDTO.getCliente());
		Endereco enderecoAtendimento = getEnderecoAtendimento(atendimentoDTO);
		BigDecimal valorTotalCarrinhoAtendimento = carrinhoService.getValorTotalCarrinho();
		Calendar data = getDataAtendimento(atendimentoDTO.getData());
		Time hora = getHoraAtendimento(atendimentoDTO.getHora());
		BigDecimal desconto = atendimentoDTO.getDesconto();
		BigDecimal taxa = atendimentoDTO.getTaxa();

		List<ServicoItemCarrinho> itensCarrinho = getItensCarrinho();
		Atendimento atendimento = builderAtendimento(itensCarrinho, clienteAtendimento, enderecoAtendimento,
				valorTotalCarrinhoAtendimento, data, hora, desconto, taxa);

		Pagamento pagamentoAtendimento = getFormaPagamento(atendimentoDTO, atendimento);

		atendimento.setPagamento(pagamentoAtendimento);

		validationFormAtendimento.isFormNotValid(null, result, atendimentoDTO, request);

		atendimentoRepository.marcarAtendimento(atendimento);

		for (ServicoItemCarrinho servicoItemCarrinho : itensCarrinho) {
			servicoItemCarrinho.setAtendimento(atendimento);
			itemRepository.salvarItem(servicoItemCarrinho);
		}

		esvaziarCarrinho();

		ResponseMarcar response = new ResponseMarcar();
		response.setAtendimento(atendimentoDTO);

		return response;
	}

	private void esvaziarCarrinho() {
		carrinhoService.esvaziarCarrinho();
	}

	private List<ServicoItemCarrinho> getItensCarrinho() {

		List<ServicoItemCarrinho> itensCarrinho = new ArrayList<>();

		List<ServicoItemCarrinhoDTO> itensCarrinhoDTO = carrinhoService.finalizarAtendimentoItensCarrinho();

		for (ServicoItemCarrinhoDTO servicoItemCarrinhoDTO : itensCarrinhoDTO) {

			ServicoItemCarrinho servicoItemCarrinho = new ServicoItemCarrinho();

			ServicoDTO servicoDTO = servicoService.buscarServicoPorId(servicoItemCarrinhoDTO.getServicoDTO().getId());

			Servico servico = ConvertersServico.deServicoDTOparaServico(servicoDTO);

			servicoItemCarrinho.setServico(servico);

			servicoItemCarrinho.setQuantidade(servicoItemCarrinhoDTO.getQuantidade());

			itensCarrinho.add(servicoItemCarrinho);
		}

		return itensCarrinho;

	}

	private Atendimento builderAtendimento(List<ServicoItemCarrinho> servicosItemCarrinho, Cliente cliente,
			Endereco endereco, BigDecimal valorTotal, Calendar data, Time hora, BigDecimal desconto,
			BigDecimal taxa) {

		Atendimento atendimento = new Atendimento();

		atendimento.setCliente(cliente);
		atendimento.setEndereco(endereco);
		atendimento.setValorTotal(valorTotal);
		atendimento.setData(data);
		atendimento.setHora(hora);
		atendimento.setStatus(StatusAtendimento.PENDENTE);
		atendimento.setDesconto(desconto);
		atendimento.setTaxa(taxa);

		return atendimento;

	}

	private Endereco getEnderecoAtendimento(MarcarAtendimentoDTO marcarAtendimentoDTO) {

		if (marcarAtendimentoDTO.getTipoEndereco().equals("endereco-cliente")) {
			Cliente cliente = getClienteAtendimento(marcarAtendimentoDTO.getCliente());
			Endereco endereco = enderecoRepository.buscarEnderecoPorId(cliente.getId());
			marcarAtendimentoDTO.getEnderecoDTOAtendimento().setBairro("null");
			return endereco;
		} else if (marcarAtendimentoDTO.getTipoEndereco().equals("casa")) {
			Endereco endereco = enderecoService.homeAdress(2);
			marcarAtendimentoDTO.getEnderecoDTOAtendimento().setBairro("null");
			return endereco;
		} else if (marcarAtendimentoDTO.getTipoEndereco().equals("outro-endereco")) {
			Endereco enderecoSalvo = enderecoRepository.salvarEndereco(
					ConvertersEndereco.deEnderecoDTOParaEndereco(marcarAtendimentoDTO.getEnderecoDTOAtendimento()));
			return enderecoSalvo;
		} else {
			marcarAtendimentoDTO.getEnderecoDTOAtendimento().setBairro("null");
			return ConvertersEndereco.deEnderecoDTOParaEndereco(marcarAtendimentoDTO.getEnderecoDTOAtendimento());
		}
	}

	private Pagamento getFormaPagamento(MarcarAtendimentoDTO atendimentoDTO, Atendimento atendimento) {

		if (atendimentoDTO.getFormaPagamento().equals("especie")) {
			Especie pagamentoEspecie = new Especie();
			atendimentoDTO.setBandeiraCartao("1");
			atendimentoDTO.setQuantidadeParcelas(1);
			return pagamentoEspecie;
		} else if (atendimentoDTO.getFormaPagamento().equals("debito")) {
			Debito pagamentoDebito = new Debito();
			pagamentoDebito.setBandeira(BandeiraCartao.getBandeiraCartao(atendimentoDTO.getBandeiraCartao()));
			atendimentoDTO.setQuantidadeParcelas(1);
			return pagamentoDebito;
		} else if (atendimentoDTO.getFormaPagamento().equals("credito")) {
			Credito pagamentoCredito = new Credito();
			pagamentoCredito.setBandeiraCartao(BandeiraCartao.getBandeiraCartao(atendimentoDTO.getBandeiraCartao()));
			pagamentoCredito.setQuantidadeParcelas(atendimentoDTO.getQuantidadeParcelas());

			return pagamentoCredito;
		} else {
			return null;
		}
	}

	private Cliente getClienteAtendimento(Integer idCliente) {
		if (idCliente > 0) {
			ClienteDTO clienteDTO = clienteService.buscarClientePorId(idCliente);
			Cliente cliente = ConvertersCliente.deClienteDTOParaCliente(clienteDTO);
			return cliente;
		}

		return null;
	}

	private Time getHoraAtendimento(String hora) {
		
		//String[] hour = hora.split(":");
		
		LocalTime localTime = LocalTime.parse(hora);

		return Time.valueOf(localTime);
		
	}

	private Calendar getDataAtendimento(String data) {

		String[] dateCharacter = data.split("-");
		
		Calendar CalendarDate = Calendar.getInstance();
		
		CalendarDate.set(Calendar.YEAR,Integer.parseInt(dateCharacter[0]));
		CalendarDate.set(Calendar.MONTH,(Integer.parseInt(dateCharacter[1]) - 1));
		CalendarDate.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dateCharacter[2]));
		
		return CalendarDate;
	}

	public List<AtendimentoListaDTO> listarTodos() {
		List<Atendimento> atendimentos = atendimentoRepository.listarTodos();
		List<AtendimentoListaDTO> atendimentosDTO = ConvertersAtendimento
				.deListaAtendimentoParaListaAtendimentoDTO(atendimentos);
		return atendimentosDTO;
	}

	public List<AtendimentoListaDTO> getAtendimentosFilterData(FiltroDataAtendimentoDTO filtro) {
		
		Calendar dataInicio = ConvertersDate.deStringDateParaCalendar(filtro.getDataInicio());
		Calendar dataFim = ConvertersDate.deStringDateParaCalendar(filtro.getDataFim());
		
		List<Atendimento> atendimentos = atendimentoRepository.getAtendimentosFilterData(dataInicio, dataFim);
		List<AtendimentoListaDTO> atendimentosDTO = ConvertersAtendimento
				.deListaAtendimentoParaListaAtendimentoDTO(atendimentos);

		return atendimentosDTO;
	}
}
