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

import br.com.salon.carine.lima.converters.ConvertersDate;
import br.com.salon.carine.lima.converters.ConvertersEndereco;
import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.enuns.BandeiraCartao;
import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.enuns.TipoEndereco;
import br.com.salon.carine.lima.enuns.TipoPagamento;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.models.Credito;
import br.com.salon.carine.lima.models.Debito;
import br.com.salon.carine.lima.models.Endereco;
import br.com.salon.carine.lima.models.Especie;
import br.com.salon.carine.lima.models.Pagamento;
import br.com.salon.carine.lima.models.Servico;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;
import br.com.salon.carine.lima.models.Usuario;
import br.com.salon.carine.lima.repositories.EnderecoRepository;
import br.com.salon.carine.lima.repositories.ServicoItemCarrinhoRepository;
import br.com.salon.carine.lima.repositoriessdp.AtendimentoRepositorySJPA;
import br.com.salon.carine.lima.repositoriessdp.UsuarioRepositorySJPA;
import br.com.salon.carine.lima.response.Message;
import br.com.salon.carine.lima.response.ResponseMarcar;
import br.com.salon.carine.lima.security.AuthenticationFacade;

@Service
public class AtendimentoService {

	@Autowired
	private ClienteService clienteService;

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
	private AuthenticationFacade usuarioAutenticado;
	
	@Autowired
	private UsuarioRepositorySJPA usuarioRepository;
	
	private Integer idLastAtendimento;
	
	private Integer idFirstAtendimento;
		
	public ResponseMarcar marcarAtendimento(MarcarAtendimentoDTO atendimentoDTO, HttpServletRequest request,
			BindingResult result) {

		Cliente clienteAtendimento = getClienteAtendimento(atendimentoDTO.getCliente());
		Endereco enderecoAtendimento = getEnderecoAtendimento(atendimentoDTO, clienteAtendimento);
		TipoEndereco tipoEndereco = atendimentoDTO.getTipoEndereco();
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

		atendimentoRepositorySJPA.save(atendimento);

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

			ServicoDTO servicoDTO = ConvertersServico
					.deServicoParaServicoDTO(servicoService.buscarServicoPorId(servicoItemCarrinhoDTO
							.getServicoDTO().getId()));

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

	private Endereco getEnderecoAtendimento(MarcarAtendimentoDTO marcarAtendimentoDTO,
				Cliente clienteAtendimento) {

		if (marcarAtendimentoDTO.getTipoEndereco() == TipoEndereco.ENDERECO_CLIENTE) {
			Endereco endereco = clienteAtendimento.getEndereco();
			return endereco;
		} else if (marcarAtendimentoDTO.getTipoEndereco() == TipoEndereco.CASA) {
			String emailUsuarioAutenticado = usuarioAutenticado.getAuthentication().getName();
			Optional<Usuario> optionalUser = usuarioRepository.findByEmail(emailUsuarioAutenticado);
			
			if(optionalUser.isPresent()) {
				return optionalUser.get().getEndereco();
			}
			
			return null;
			
		} else if (marcarAtendimentoDTO.getTipoEndereco() == TipoEndereco.OUTRO_ENDERECO) {
			Endereco enderecoSalvo = enderecoRepository.salvarEndereco(
					ConvertersEndereco.deEnderecoDTOParaEndereco(marcarAtendimentoDTO.getEnderecoDTOAtendimento()));
			return enderecoSalvo;
		} else {
			return ConvertersEndereco.deEnderecoDTOParaEndereco(marcarAtendimentoDTO.getEnderecoDTOAtendimento());
		}
	}

	private Pagamento getFormaPagamento(MarcarAtendimentoDTO atendimentoDTO, Atendimento atendimento) {

		if (atendimentoDTO.getTipoPagamento() == TipoPagamento.ESPECIE) {
			Especie pagamentoEspecie = new Especie();
			return pagamentoEspecie;
		} else if (atendimentoDTO.getTipoPagamento() == TipoPagamento.DEBITO) {
			Debito pagamentoDebito = new Debito();
			pagamentoDebito.setBandeiraCartao(BandeiraCartao.getBandeiraCartao(atendimentoDTO.getBandeiraCartao()));
			return pagamentoDebito;
		} else if (atendimentoDTO.getTipoPagamento() == TipoPagamento.CREDITO) {
			Credito pagamentoCredito = new Credito();
			pagamentoCredito.setBandeiraCartao(BandeiraCartao.getBandeiraCartao(atendimentoDTO.getBandeiraCartao()));
			pagamentoCredito.setQuantidadeParcelas(atendimentoDTO.getQuantidadeParcelas());

			return pagamentoCredito;
		} else {
			
			return null;
		}
	}

	private Cliente getClienteAtendimento(Integer idCliente) {
			Cliente cliente = clienteService.buscarClientePorId(idCliente);
			return cliente;
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
		
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Atendimento> pagesAtendimento = atendimentoRepositorySJPA
				.findAllAtendimentoFetchEnderecoCliente(pageRequest);
		
		return pagesAtendimento;
		
	}

	public Page<Atendimento> getAtendimentosFilterData(FiltroDataAtendimentoDTO filtro) {
		
		if(filtro.getDataInicio() != "" && filtro.getDataFim() != "") {
			Calendar dataInicio = ConvertersDate.deStringDateParaCalendar(filtro.getDataInicio());
			Calendar dataFim = ConvertersDate.deStringDateParaCalendar(filtro.getDataFim());
							
			Page<Atendimento> atendimentosDate = atendimentoRepositorySJPA
					.findByDataBetween(dataInicio, dataFim, null);
			
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

	public Page<Atendimento> filtrarAtendimentoPorCliente(String nome) {
		
		if(!nome.equals("")) {
			Page<Atendimento> atendimentos = atendimentoRepositorySJPA.searchNomeFilter(nome, null);
			
			return atendimentos;
			
		}else {
			
			Page<Atendimento> findPageAtendimento = findPageAtendimento(0, 5);
			return findPageAtendimento;
		}
	}

	public Atendimento nextAtendimento(Integer idAtendimentoAtual) {
		
		if(isLastAtendimento(idAtendimentoAtual)) {
			return firstAtendimento();
		}else {
			Integer idAtendimentoProximo = atendimentoRepositorySJPA.idAtendimentoProximo(idAtendimentoAtual);
			return atendimentoRepositorySJPA.findById(idAtendimentoProximo).get();
		}
	}
	
	public Atendimento previousAtendimento(Integer idAtendimentoAtual) {
		
		if(isFirstAtendimento(idAtendimentoAtual)) {
			return lastAtendimento();
		}else {
			Integer idAtendimentoAnterior = atendimentoRepositorySJPA.idAtendimentoAnterior(idAtendimentoAtual);
			return atendimentoRepositorySJPA.findById(idAtendimentoAnterior).get();
		}
	}
	
	public Atendimento lastAtendimento() {
		Integer idlastAtendimento = atendimentoRepositorySJPA.idLastAtendimento();
		Optional<Atendimento> optionalAtendimento = atendimentoRepositorySJPA.findById(idlastAtendimento);
		if(optionalAtendimento.isPresent()) {
			return optionalAtendimento.get();
		}
		
		return null;
	}
	
	public boolean isFirstAtendimento(Integer idAtendimentoAtual) {
		
		if(idFirstAtendimento == idAtendimentoAtual) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isLastAtendimento(Integer idAtendimentoAtual) {
		
		if(idLastAtendimento == idAtendimentoAtual) {
			return true;
		}else {
			return false;
		}
	}
	
	public Atendimento firstAtendimento() {
		
		Integer idFirstAtendimento = atendimentoRepositorySJPA.idFirstAtendimento();
		Optional<Atendimento> optionalAtendimento = atendimentoRepositorySJPA.findById(idFirstAtendimento);
		if(optionalAtendimento.isPresent()) {
			return optionalAtendimento.get();
		}
		
		return null;
	}
	
	public void alterarStatusAtendimento(Integer idAtendimento) {
		
		Optional<Atendimento> atendimento = atendimentoRepositorySJPA.findById(idAtendimento);
		
		if(atendimento.isPresent()) {
			atendimento.get().setStatus(StatusAtendimento.ATENDIDO);
			atendimentoRepositorySJPA.save(atendimento.get());
		}
	}
	
	public void alterarStatusAtendimento(StatusAtendimento statusAtendimento, Integer idAtendimento) {
		Atendimento atendimento = buscarAtendimentoPorId(idAtendimento);
		atendimento.setStatus(statusAtendimento);
		
		atendimentoRepositorySJPA.save(atendimento);
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
	
	public Atendimento buscarAtendimentoPorId(Integer id) {
		Optional<Atendimento> atendimento = atendimentoRepositorySJPA.findById(id);
		if(atendimento.isPresent()) {
			Atendimento atendimentoBusca = atendimento.get();
			return atendimentoBusca;
		}else {
			return null;
		}
	}
	
	public List<Atendimento> findAllAtendimentosList(Calendar de, Calendar para) {
		return atendimentoRepositorySJPA.findByDataBetween(de, para);
	}
	
	public void atualizarLastId(Integer id) {
		idLastAtendimento = id;
	}

	public void atualizarFirstId(Integer id) {
		idFirstAtendimento = id;
	}
	
	public Integer idLastAtendimento() {
		return atendimentoRepositorySJPA.idLastAtendimento();
	}
	
	public Integer idFirstAtendimento() {
		return atendimentoRepositorySJPA.idFirstAtendimento();
	}
}
