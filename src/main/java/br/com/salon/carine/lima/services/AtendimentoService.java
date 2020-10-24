package br.com.salon.carine.lima.services;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.com.salon.carine.lima.converters.ConvertersCliente;
import br.com.salon.carine.lima.converters.ConvertersDate;
import br.com.salon.carine.lima.converters.ConvertersEndereco;
import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.ClienteDTO;
import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.enuns.BandeiraCartao;
import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.enuns.TipoEndereco;
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
import br.com.salon.carine.lima.repositoriessdp.AtendimentoRepositorySJPA;
import br.com.salon.carine.lima.response.Message;
import br.com.salon.carine.lima.response.ResponseMarcar;

@Service
public class AtendimentoService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private AtendimentoRepositorySJPA atendimentoRepositorySJPA;

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
	
	private List<Atendimento> rowAtendimentosList = new ArrayList<>();

	public ResponseMarcar marcarAtendimento(MarcarAtendimentoDTO atendimentoDTO, HttpServletRequest request,
			BindingResult result) {

		Cliente clienteAtendimento = getClienteAtendimento(atendimentoDTO.getCliente());
		Endereco enderecoAtendimento = getEnderecoAtendimento(atendimentoDTO);
		TipoEndereco tipoEndereco = getTipoEndereco(atendimentoDTO);
		Calendar data = getDataAtendimento(atendimentoDTO.getData());
		Time hora = getHoraAtendimento(atendimentoDTO.getHora());
		BigDecimal desconto = atendimentoDTO.getDesconto();
		BigDecimal taxa = atendimentoDTO.getTaxa();
		BigDecimal totalBruto = carrinhoService.getValorTotalCarrinho();
		BigDecimal totalLiquido = getTotalLiquido(taxa, desconto);
		List<ServicoItemCarrinho> itensCarrinho = getItensCarrinho();
		Atendimento atendimento = builderAtendimento(itensCarrinho, clienteAtendimento, enderecoAtendimento,
				tipoEndereco, totalLiquido, totalBruto, data, hora, desconto, taxa);

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

	private TipoEndereco getTipoEndereco(MarcarAtendimentoDTO marcarAtendimentoDTO) {
		if (marcarAtendimentoDTO.getTipoEndereco().equals("endereco-cliente")) {
			return TipoEndereco.ENDERECO_CLIENTE;
		} else if (marcarAtendimentoDTO.getTipoEndereco().equals("casa")) {
			return TipoEndereco.CASA;
		} else if (marcarAtendimentoDTO.getTipoEndereco().equals("outro-endereco")) {
			return TipoEndereco.OUTRO_ENDERECO;
		}

		return null;
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
			Endereco endereco, TipoEndereco tipoEndereco, BigDecimal totalLiquido, BigDecimal totalBruto,
			Calendar data, Time hora, BigDecimal desconto, BigDecimal taxa) {

		Atendimento atendimento = new Atendimento();

		atendimento.setCliente(cliente);
		atendimento.setEndereco(endereco);
		atendimento.setTipoEndereco(tipoEndereco);
		atendimento.setTotalLiquido(totalLiquido);
		atendimento.setTotalBruto(totalBruto);
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
			Endereco endereco = enderecoRepository.buscarEnderecoPorCliente(cliente);
			marcarAtendimentoDTO.getEnderecoDTOAtendimento().setBairro("null");
			return endereco;
		} else if (marcarAtendimentoDTO.getTipoEndereco().equals("casa")) {
			/*
			 * Este endereço virá do cadastro do usuário no módulo controle de acesso em
			 * fase de desenvolvimento
			 */
			return null;
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
			pagamentoDebito.setBandeiraCartao(BandeiraCartao.getBandeiraCartao(atendimentoDTO.getBandeiraCartao()));
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

		if (!(hora.equals(""))) {
			LocalTime localTime = LocalTime.parse(hora);

			return Time.valueOf(localTime);
		}

		return null;
	}

	private Calendar getDataAtendimento(String data) {

		if (!data.equals("")) {

			String[] dateCharacter = data.split("-");

			Calendar CalendarDate = Calendar.getInstance();

			CalendarDate.set(Calendar.YEAR, Integer.parseInt(dateCharacter[0]));
			CalendarDate.set(Calendar.MONTH, (Integer.parseInt(dateCharacter[1]) - 1));
			CalendarDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateCharacter[2]));

			return CalendarDate;
		}

		return null;
	}
	
	private BigDecimal getTotalLiquido(BigDecimal taxa, BigDecimal desconto) {
		
		BigDecimal valorCalculado = carrinhoService.getValorTotalCarrinho();
		
		if(taxa != null) {
			valorCalculado = valorCalculado.add(taxa);
		}
		
		if(desconto != null) {
			valorCalculado = valorCalculado.subtract(desconto);
		}
		
		return valorCalculado;
	}

	public Page<Atendimento> findPageAtendimento(Integer page, Integer size) {
		
		if(page > 0) {
		
			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Atendimento> pagesAtendimento = atendimentoRepository
					.findAllPagingRowNumber(pageRequest);
			
			int pointInit = page * 5;
			
			for (Atendimento atendimento : pagesAtendimento) {
				atendimento.setRowNumber(pointInit);
				pointInit++;
			}
			
			return pagesAtendimento;
			
		}else {
			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Atendimento> pagesAtendimento = atendimentoRepository
					.findAllPagingRowNumber(pageRequest);
			
			return pagesAtendimento;
		}
	}

	public Page<Atendimento> getAtendimentosFilterData(FiltroDataAtendimentoDTO filtro) {
		
		if(filtro.getDataInicio() != "" && filtro.getDataFim() != "") {
			Calendar dataInicio = ConvertersDate.deStringDateParaCalendar(filtro.getDataInicio());
			Calendar dataFim = ConvertersDate.deStringDateParaCalendar(filtro.getDataFim());
						
			Page<Atendimento> atendimentosDate = atendimentoRepository
					.findByDataBetweenRowNumber(dataInicio, dataFim);
			
			return atendimentosDate;
			
		}else {
			
			return findPageAtendimento(0, 5);
		}
	}

	public Page<Atendimento> buscarAtendimentoRowNumber(Integer rowNumber) {
		
		PageRequest pageRequest = PageRequest.of(rowNumber, 1);
		
		Page<Atendimento> pageAtendimento = atendimentoRepositorySJPA.findAll(pageRequest);
		
		return pageAtendimento;
	}
	
	private Integer findRowListAtendimento(Atendimento atendimento) {
		
		long quantidadeRegistros = atendimentoRepositorySJPA.count();
		
		if(rowAtendimentosList.isEmpty() || rowAtendimentosList.size() < quantidadeRegistros) {
			loadList();
			return retornarLineNumber(atendimento);
		}else {
			return retornarLineNumber(atendimento);
		}
	}
	
	private void loadList() {
		rowAtendimentosList.clear();
		List<Atendimento> Atendimentos = (List<Atendimento>) atendimentoRepositorySJPA.findAll();
		Atendimentos.stream().forEach(atendimento -> rowAtendimentosList.add(atendimento));
	}
	
	private Integer retornarLineNumber(Atendimento atendimento) {
		
		if(rowAtendimentosList.contains(atendimento)) {
			return rowAtendimentosList.indexOf(atendimento);
		}else {
			addNewLine(atendimento);
			return rowAtendimentosList.indexOf(atendimento);
		}
	}
	
	private void addNewLine (Atendimento atendimento) {
		rowAtendimentosList.add(atendimento);
	}

	public List<Atendimento> filtrarAtendimentoPorCliente(String nome) {
		
		if(!nome.equals("")) {
			List<Atendimento> atendimentos = atendimentoRepository.searchNomeFilterRowNumber(nome);
			
			return atendimentos;
			
		}else {
			
			Page<Atendimento> findPageAtendimento = findPageAtendimento(0, 5);
			return findPageAtendimento.getContent();
		}
	}

	public Page<Atendimento> nextPageService(boolean isLast, Integer number) {
		
		if(isLast) {
			PageRequest pageRequest = PageRequest.of(0, 1);
			Page<Atendimento> paginaProxima = atendimentoRepositorySJPA.findAll(pageRequest);
			
			return paginaProxima;
			
		}else {
			PageRequest pageRequest = PageRequest.of(number + 1, 1);
			Page<Atendimento> paginaProxima = atendimentoRepositorySJPA.findAll(pageRequest);
			
			return paginaProxima;
		}
	}

	public Page<Atendimento> previousPageService(boolean isFirst, Integer number) {
		
		if(isFirst) {			
			int lastPage = (int) atendimentoRepositorySJPA.count() - 1;
			PageRequest pageRequest = PageRequest.of(lastPage, 1);
			Page<Atendimento> paginaAterior = atendimentoRepositorySJPA.findAll(pageRequest);
			return paginaAterior;
			
		}else {
			PageRequest pageRequest = PageRequest.of(number - 1, 1);
			Page<Atendimento> paginaAterior = atendimentoRepositorySJPA.findAll(pageRequest);
			
			return paginaAterior;
		}
	}
	
	public void alterarStatusAtendimento(Integer idAtendimento) {
		
		Optional<Atendimento> atendimento = atendimentoRepositorySJPA.findById(idAtendimento);
		
		if(atendimento.isPresent()) {
			atendimento.get().setStatus(StatusAtendimento.ATENDIDO);
			atendimentoRepositorySJPA.save(atendimento.get());
		}
	}

	public Message cancelar(Integer idAtendimento) {
		
		Message message = new Message();
		Optional<Atendimento> atendimento = atendimentoRepositorySJPA.findById(idAtendimento);
		
		if(atendimento.isPresent()) {
			atendimentoRepositorySJPA.delete(atendimento.get());
			message.setTitle("Atendimento");
			message.setBody("Atendimento cancelado com sucesso");
		}
		
		return message;
	}
}
