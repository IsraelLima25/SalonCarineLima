package br.com.salon.carine.lima.converters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.salon.carine.lima.dto.AtendimentoListaDTO;
import br.com.salon.carine.lima.models.Atendimento;

public class ConvertersAtendimento {
	
	public static List<AtendimentoListaDTO> deListaAtendimentoParaListaAtendimentoDTO
			(List<Atendimento> listaAtendimentos){
		
		List<AtendimentoListaDTO> atendimentosDTO = new ArrayList<>();
		
		for (Atendimento atendimento : listaAtendimentos) {
			AtendimentoListaDTO atendimentoLista = new AtendimentoListaDTO();
			atendimentoLista.setId(atendimento.getId());
			atendimentoLista.setCliente(atendimento.getCliente());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			atendimentoLista.setData(dateFormat.format(atendimento.getData().getTime()));
			SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
			atendimentoLista.setHora(horaFormat.format(atendimento.getHora().getTime()));
			atendimentosDTO.add(atendimentoLista);
		}
		
		return atendimentosDTO;
		
	}
}
