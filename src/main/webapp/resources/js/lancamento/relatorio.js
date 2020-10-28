$(function() {
	$('button[type=submit]').click(function(e) {
		e.preventDefault();
		$.post({
			url : '/SalonCarineLima/lancamento/filterData',
			data : $('form[name=formFiltroRelatorio]').serialize()
		})
		.then(function(data, textStatus, xhr) {
			limparMessagesErrors();
			rendererTabela(data);
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
		})

	})
});

function rendererTabela(data) {
	
	limparTabela();
	
	if(data.lancamentos.length === 0){
		$('#msg-nenhum-registro-encontrado').prop("hidden", false);
	}else{
		$('#msg-nenhum-registro-encontrado').prop("hidden", true);
	}
	data.lancamentos.forEach(function(lancamento) {
		
		var dataFormatada = formatData(lancamento.data);
		var valorLancamento = parseFloat(lancamento.valorTotal).toFixed(2);
				
		$('#lancamento-table').append(
			`
			<tr id = ${lancamento.id}>
				<td>${valorLancamento}</td>
				<td class="text-center">${dataFormatada}</td>
				<td class="text-center">
					<a class="fas fa-search btn btn-info"
					 href="/SalonCarineLima/atendimento/pageId/${lancamento.atendimento.id}" />
				</td>
				 <td class="text-center">
					<a class="fas fa-undo btn btn-danger" onClick="updateIdOpenModal(${lancamento.id})" />
				</td>
			</tr>	
			`
		);
		$('#valor').text(parseFloat(data.totalPeriodo).toFixed(2));
	})
}

function formatData(milessegundo){
	var data = new Date(milessegundo),
    dia  = data.getDate().toString().padStart(2, '0'),
    mes  = (data.getMonth()+1).toString().padStart(2, '0'), //+1 pois no getMonth Janeiro começa com zero.
    ano  = data.getFullYear().toString().substr(2,3);
	var dataFormatada = dia+"/"+mes+"/"+ano;
	return dataFormatada;
}

function limparTabela(){
	$('#lancamento-table tr td').remove();
}

function limparMessagesErrors(){
	
	$('.dataInicio').prop('hidden',true);
	$('.dataInicio').text('');
	
	$('.dataFim').prop('hidden',true);
	$('.dataFim').text('');
}

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}

function atualizarIds(idLancamento){
	$('#idLancamento').text('');
	$('#idLancamento').text(idLancamento);
}

function updateIdOpenModal(lancamentoId){
	atualizarIds(lancamentoId);
	$('#modalEstornar').modal('show');
}

function estornarLancamento(){
	var idLancamentoAtual = $('#idLancamento').text();
	$.get({
		url : `/SalonCarineLima/lancamento/estornar/${idLancamentoAtual}`
	})
	.then(function(data, textStatus, xhr) {
		removeElement(idLancamentoAtual);
		updateValues(data.totalPeriodo);
	})
	.catch(function(err) {
		console.log("Request fail");
		console.log(err);
	})
}

function updateValues(totalPeriodo){
	$('#valor').text(parseFloat(totalPeriodo).toFixed(2));
}

function removeElement(idLancamentoAtual){
	$('#'+idLancamentoAtual).remove();
	$('#'+idLancamentoAtual).remove();
}