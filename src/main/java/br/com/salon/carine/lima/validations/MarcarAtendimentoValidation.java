package br.com.salon.carine.lima.validations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.salon.carine.lima.converters.ConvertersDate;
import br.com.salon.carine.lima.dto.MarcarAtendimentoDTO;
import br.com.salon.carine.lima.enuns.TipoEndereco;
import br.com.salon.carine.lima.enuns.TipoPagamento;
import br.com.salon.carine.lima.exceptions.FieldMessage;

public class MarcarAtendimentoValidation
		implements ConstraintValidator<AtendimentoInsertValidator, MarcarAtendimentoDTO> {

	List<FieldMessage> list = new ArrayList<>();

	@Override
	public boolean isValid(MarcarAtendimentoDTO atendimento, ConstraintValidatorContext context) {
		
		list.clear();
		
		validCliente(atendimento.getCliente());
		validFormaPagamento(atendimento.getTipoPagamento());
		validBandeiraCartao(atendimento);
		validEndereco(atendimento);
		validData(atendimento.getData());
		validHora(atendimento.getHora());
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}

		return list.isEmpty();
	}
	
	private void validHora(String hora) {
		if(hora.equals("")) {
			adicionarErroLista("hora", "Hora inválida");
		}
	}

	private void validData(String data) {
		
		Calendar date = ConvertersDate.deStringDateParaCalendar(data);
		
		if(date == null) {
			adicionarErroLista("data","Data inválida");
		}else if(date.before(Calendar.getInstance())) {
			adicionarErroLista("data","Data anterior a atual não permitida");
		}		
	}

	private void validEndereco(MarcarAtendimentoDTO atendimento) {
		
		if(atendimento.getTipoEndereco() == null) {
			
			adicionarErroLista("tipoEndereco", "Endereço obrigatório");
			
		} else if(atendimento.getTipoEndereco() == TipoEndereco.OUTRO_ENDERECO) {
			
			if(atendimento.getEnderecoDTOAtendimento().getBairro().equals("")) {
				adicionarErroLista("enderecoDTOAtendimento.bairro", "Bairro inválido");
			}
		}
		

	}

	private void validBandeiraCartao(MarcarAtendimentoDTO atendimento) {
		
		if((atendimento.getTipoPagamento() == TipoPagamento.DEBITO) 
				&& atendimento.getBandeiraCartao() == null) {
			
			adicionarErroLista("bandeiraCartao", "Bandeira obrigatório(a)");
			
		}else if((atendimento.getTipoPagamento() == TipoPagamento.CREDITO)
				&& (atendimento.getBandeiraCartao() == null)) {
				
			adicionarErroLista("bandeiraCartao", "Bandeira obrigatório(a)");
			adicionarErroLista("quantidadeParcelas", "Quantidade de parcelas inválida");
			
		}else if((atendimento.getTipoPagamento() == TipoPagamento.CREDITO)
				&& (atendimento.getBandeiraCartao() != null)
				&& (atendimento.getQuantidadeParcelas() == null 
					|| atendimento.getQuantidadeParcelas() == 0 )) {
				
			adicionarErroLista("quantidadeParcelas", "Quantidade de parcelas inválida");
		}
	}

	private void validFormaPagamento(TipoPagamento tipoPagamento) {
		if(tipoPagamento == null) {
			adicionarErroLista("tipoPagamento", "Pagamento inválido");
		}else {
			
		}
	}

	private void validCliente(Integer clienteId){
		if(clienteId < 1) {
			adicionarErroLista("cliente","Cliente obrigatório");
		}
	}
	
	private void adicionarErroLista(String atributo, String message ) {
		
		list.add(new FieldMessage(atributo, message));
	}
}
