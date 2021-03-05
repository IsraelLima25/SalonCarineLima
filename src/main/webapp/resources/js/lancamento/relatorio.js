var contextCurrent = $('#contextPage').text();

$(function() {
	$('button[type=submit]').click(function(e) {
		showLoader();
		e.preventDefault();
		$.post({
			url : contextCurrent+'/lancamento/filterData',
			data : $('form[name=formFiltroRelatorio]').serialize()
		})
		.then(function(data, textStatus, xhr) {
			limparMessagesErrors();
			rendererTabela(data);
			hideLoader();
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
			hideLoader();
		})

	})
});

function rendererTabela(data) {
	
	limparTabela();
	
	if(data.lancamentos.length === 0){
		$('#msg-nenhum-registro-encontrado').prop("hidden", false);
		$('#valor').text(parseFloat(0).toFixed(2));
	}else{
		$('#msg-nenhum-registro-encontrado').prop("hidden", true);
	
		data.lancamentos.forEach(function(lancamento) {
			
			var dataFormatada = formatData(lancamento.data);
			var valorLancamento = lancamento.valorTotal.toLocaleString('pt-BR', 
					{ style: 'currency', currency: 'BRL' });
					
			$('#lancamento-table').append(
				`
				<tr id = ${lancamento.id}>
					<td>${valorLancamento}</td>
					<td class="text-center">${dataFormatada}</td>
					<td class="text-center">
						<a class="fas fa-search btn btn-info"
						 href="${contextCurrent}/atendimento/${lancamento.atendimento.id}" />
					</td>
					 <td class="text-center">
						<a class="fas fa-undo btn btn-danger" onClick="updateIdOpenModal(${lancamento.id})" />
					</td>
				</tr>	
				`
			);
			$('#valor').text(data.totalPeriodo.toLocaleString('pt-BR', 
					{ style: 'currency', currency: 'BRL' }));
		})
}
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
	showLoader();
	$.get({
		url : `${contextCurrent}/lancamento/estornar/${idLancamentoAtual}`
	})
	.then(function(data, textStatus, xhr) {
		removeElement(idLancamentoAtual);
		updateValues(data.totalPeriodo);
		hideLoader();
	})
	.catch(function(err) {
		console.log("Request fail");
		console.log(err);
		hideLoader();
	})
}

function updateValues(totalPeriodo){
	$('#valor').text(parseFloat(totalPeriodo).toFixed(2));
}

function removeElement(idLancamentoAtual){
	$('#'+idLancamentoAtual).remove();
	$('#'+idLancamentoAtual).remove();
}