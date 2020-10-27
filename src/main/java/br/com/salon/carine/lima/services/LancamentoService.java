package br.com.salon.carine.lima.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersDate;
import br.com.salon.carine.lima.dto.CalendarDTO;
import br.com.salon.carine.lima.dto.RelatorioLancamentoDTO;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Lancamento;
import br.com.salon.carine.lima.repositoriessdp.AtendimentoRepositorySJPA;
import br.com.salon.carine.lima.repositoriessdp.LancamentoRepository;
import br.com.salon.carine.lima.response.Message;

@Service
public class LancamentoService {

	@Autowired
	public AtendimentoService atendimentoService;
	
	@Autowired
	public AtendimentoRepositorySJPA atendimentoRepository;

	@Autowired
	public LancamentoRepository lancamentoRepository;
	
	
	
	public Message lancar(Integer id) {

		atendimentoService.alterarStatusAtendimento(id);
		
		Atendimento atendimento = atendimentoRepository.findById(id).get();
		
		Lancamento lancamento = new Lancamento();
		lancamento.setAtendimento(atendimento);
		lancamento.setData(Calendar.getInstance());
		lancamento.setValorTotal(atendimento.getTotalLiquido());

		lancamentoRepository.save(lancamento);

		return new Message("Lançamento", "Atendimento lançado com sucesso");
	}

	public RelatorioLancamentoDTO getRelatorioPeriodo() {

		CalendarDTO filterMonthActual = ConvertersDate.filterMonthActual();
		
		List<Lancamento> lancamentos = 
				lancamentoRepository.findByDataBetween(filterMonthActual.getDe(), 
						filterMonthActual.getPara());
		
		BigDecimal valorTotalPeriodo = calcularValorPeriodo(lancamentos);
		
		RelatorioLancamentoDTO relatorio = new RelatorioLancamentoDTO();
		relatorio.setLancamentos(lancamentos);
		relatorio.setTotalPeriodo(valorTotalPeriodo);
		
		return relatorio;
	}
	
	public BigDecimal calcularValorPeriodo(List<Lancamento> lancamentos) {
		
		BigDecimal valorTotal = lancamentos.stream()
			    .map(Lancamento::getValorTotal)
			    .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return valorTotal;
	}
	
}
