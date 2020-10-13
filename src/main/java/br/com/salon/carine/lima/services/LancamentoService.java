package br.com.salon.carine.lima.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.enuns.StatusAtendimento;
import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.models.Lancamento;
import br.com.salon.carine.lima.repositories.AtendimentoRepository;
import br.com.salon.carine.lima.repositories.LancamentoRepository;
import br.com.salon.carine.lima.response.Message;

@Service
public class LancamentoService {

	@Autowired
	public AtendimentoRepository atendimentoRepository;
	
	@Autowired
	public LancamentoRepository lancamentoRepository;
	
	public Message lancar(Integer id) {
		
		Atendimento atendimento = atendimentoRepository.buscarAtendimentoPorId(id);
		if (atendimento != null) {
			atendimento.setStatus(StatusAtendimento.ATENDIDO);
			atendimentoRepository.atualizarAtendimento(atendimento);
			
			Lancamento lancamento = new Lancamento();
//			lancamento.setAtendimento(atendimento);
//			lancamento.setData(Calendar.getInstance());
//			lancamento.setValorTotal(atendimento.getValorTotal());
			
			lancamentoRepository.lancar(lancamento);
			
			return new Message("Lançamento", "Atendimento lançado com sucesso");
		}
		return null;
	}


}
