$(function() {
	$('button[type=submit]').click(function(e) {
		e.preventDefault();
		$.post({
			url : '/SalonCarineLima/atendimento/marcar',
			method : 'POST',
			data : $('form[name=formMarcar]').serialize()
		})
		.then(function(data, textStatus, xhr) {
// if (xhr.status === 201) {
// showMessage(data);
// atualizarPagina();
// }
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
			console.log("Fail request");
			console.log(err);
		})

	})
});

function limparMessagesErrors(){
	
	$('.cliente').prop('hidden',true);
	$('.cliente').text('');
}

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}

$('#slcTipoEndereco').change(function() {
	var tipoSelecionado = $('#slcTipoEndereco option:selected').val();
	
	if(tipoSelecionado === 'outro-endereco'){
		$('#outroEndereco').prop('hidden', false);
	}else{
		$('#outroEndereco').prop('hidden', true);

	}
})

$('#slcFormaPagamento').change(function() {
	var valueSelected = $('#slcFormaPagamento option:selected').val();

	if (valueSelected === 'carteira') {
		$('#groupBandeiras').prop('hidden', true);
		$('#groupQuantidadeParcelas').prop('hidden', true);
	} else if (valueSelected === 'credito') {
		$('#groupBandeiras').prop('hidden', false);
		$('#groupQuantidadeParcelas').prop('hidden', false);
	} else {
		$('#groupBandeiras').prop('hidden', false);
		$('#groupQuantidadeParcelas').prop('hidden', true);
	}

})