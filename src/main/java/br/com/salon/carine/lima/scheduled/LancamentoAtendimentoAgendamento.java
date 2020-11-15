package br.com.salon.carine.lima.scheduled;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.repositoriessdp.AtendimentoRepositorySJPA;
import br.com.salon.carine.lima.services.LancamentoService;

@Component
@EnableScheduling
public class LancamentoAtendimentoAgendamento {

	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	private Logger logger = Logger.getLogger("br.com.salon.carine.lima.Cliente");

	@Autowired
	private LancamentoService serviceLancamento;

	@Autowired
	private AtendimentoRepositorySJPA atendimentoRepository;

	@Scheduled(cron = "0 08 00 \\* \\* \\*", zone = TIME_ZONE)
	public void testScheduling() {
		logger.info("Iniciando Lancamentos");
		Calendar dataOntem = Calendar.getInstance();
		dataOntem.add(Calendar.DAY_OF_MONTH, -1);
		List<Atendimento> atendimentos = atendimentoRepository
				.findByDataBetween(dataOntem, dataOntem);
		atendimentos.stream().forEach(atendimento -> serviceLancamento.lancar(atendimento.getId()));
		logger.info("Lancamentos Finalizados");
	}
}
