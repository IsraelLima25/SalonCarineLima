package br.com.salon.carine.lima.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
