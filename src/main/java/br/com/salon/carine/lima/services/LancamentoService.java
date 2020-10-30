package br.com.salon.carine.lima.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersDate;
import br.com.salon.carine.lima.dto.CalendarDTO;
import br.com.salon.carine.lima.dto.FiltroDataAtendimentoDTO;
import br.com.salon.carine.lima.dto.RelatorioLancamentoDTO;
import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Lancamento;
import br.com.salon.carine.lima.repositoriessdp.LancamentoRepository;
import br.com.salon.carine.lima.response.Message;

@Service
public class LancamentoService {

	@Autowired
	public AtendimentoService atendimentoService;

	@Autowired
	public LancamentoRepository lancamentoRepository;

	private CalendarDTO filtroActualFind = new CalendarDTO();

	public Message lancar(Integer id) {

		atendimentoService.alterarStatusAtendimento(id);

		Atendimento atendimento = atendimentoService.buscarAtendimentoPorId(id);

		Lancamento lancamento = new Lancamento();
		lancamento.setAtendimento(atendimento);
		lancamento.setData(Calendar.getInstance());
		lancamento.setValorTotal(atendimento.getTotalLiquido());

		lancamentoRepository.save(lancamento);

		return new Message("Lançamento", "Atendimento lançado com sucesso");
	}

	public RelatorioLancamentoDTO getRelatorioPeriodo() {

		filtroActualFind = ConvertersDate.filterMonthActual();

		RelatorioLancamentoDTO relatorio = gerarRelatorio();

		return relatorio;
	}

	private RelatorioLancamentoDTO gerarRelatorio() {

		List<Lancamento> lancamentos = lancamentoRepository.findByDataBetween(filtroActualFind.getDe(),
				filtroActualFind.getPara());

		BigDecimal valorTotalPeriodo = calcularValorPeriodo(lancamentos);

		RelatorioLancamentoDTO relatorio = new RelatorioLancamentoDTO();
		relatorio.setLancamentos(lancamentos);
		relatorio.setTotalPeriodo(valorTotalPeriodo);

		return relatorio;
	}

	public BigDecimal calcularValorPeriodo(List<Lancamento> lancamentos) {

		BigDecimal valorTotal = lancamentos.stream().map(Lancamento::getValorTotal).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		return valorTotal;
	}

	public RelatorioLancamentoDTO estornarLancamento(Integer idLancamento) {

		Optional<Lancamento> optionalLancamento = lancamentoRepository.findById(idLancamento);

		if (optionalLancamento.isPresent()) {
			Lancamento lancamento = optionalLancamento.get();
			lancamentoRepository.delete(lancamento);
			atendimentoService.alterarStatusAtendimento(StatusAtendimento.PENDENTE,
					lancamento.getAtendimento().getId());

			RelatorioLancamentoDTO relatorioAtualizado = gerarRelatorio();

			return relatorioAtualizado;

		} else {
			return null;
		}
	}

	public RelatorioLancamentoDTO findLancamentoBetweenDate(@Valid FiltroDataAtendimentoDTO filtroData) {

		Calendar dataInicio = ConvertersDate.deStringDateParaCalendar(filtroData.getDataInicio());
		Calendar dataFim = ConvertersDate.deStringDateParaCalendar(filtroData.getDataFim());

		CalendarDTO calendarDTO = new CalendarDTO();
		calendarDTO.setDe(dataInicio);
		calendarDTO.setPara(dataFim);

		filtroActualFind = calendarDTO;

		RelatorioLancamentoDTO relatorioGerado = gerarRelatorio();

		return relatorioGerado;

	}
}
