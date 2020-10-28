function atualizarIds(idLancamento, totalPeriodo){
	$('#idLancamento').text('');
	$('#idLancamento').text(idLancamento);
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
}