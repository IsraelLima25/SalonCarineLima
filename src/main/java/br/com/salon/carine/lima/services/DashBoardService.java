package br.com.salon.carine.lima.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.repositoriessdp.AtendimentoRepositorySJPA;

@Service
public class DashBoardService {
	
	@Autowired
	public AtendimentoRepositorySJPA atendimentoRepository;

	public String getDateActualFormatter() {
		
		Calendar calendar = getDateActual();
		
		Locale local = new Locale("pt","BR");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", 
				local);
		
		return dateFormat.format(calendar.getTime()).toString();
	}
	
	public Calendar getDateActual() {
		Calendar calendar = Calendar.getInstance();
		return calendar;
	}
	
	public List<Atendimento> atendimentosDiaAtual(){
		List<Atendimento> atendimentos = atendimentoRepository
			.findByDataBetween(getDateActual(), getDateActual());
		
		return atendimentos;
	}

}
