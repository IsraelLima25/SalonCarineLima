$(function() {
	$('button[type=submit]').click(function(e) {
		e.preventDefault();
		$.post({
			url : '/SalonCarineLima/atendimento/marcar',
			method : 'POST',
			data : $('form[name=formMarcar]').serialize()
		})
		.then(function(data, textStatus, xhr) {
			 if (xhr.status === 201) {
			 showMessage(data);
			 atualizarPagina();
			}
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
			console.log("Fail request");
			console.log(err);
		})

	})
});

function atualizarPagina(){
	limparCampos();
	limparMessagesErrors();
	topPage();
}

function limparCampos() {
	$('#slcClientes option:contains(Selecionar Cliente)').attr('selected',true);
	$('#slcFormasPagamento option:contains(Selecionar Pagamento)').attr('selected',true);
	$('#slcTiposEndereco option:contains(Selecionar Endereco)').attr('selected',true);
	$('#valorTotal').val('');
	$('#desconto').val('');
	$('#taxa').val('');
	$('#dataAtendimento').val('');
	$('#horaAtendimento').val('');
}

function limparMessagesErrors(){
	
	$('.cliente').prop('hidden',true);
	$('.cliente').text('');
	
	$('.formaPagamento').prop('hidden',true);
	$('.formaPagamento').text('');
	
	$('.tipoEndereco').prop('hidden',true);
	$('.tipoEndereco').text('');
	
	$('.data').prop('hidden',true);
	$('.data').text('');
	
	$('.hora').prop('hidden',true);
	$('.hora').text('');
}

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}

$('#slcTiposEndereco').change(function() {
	var tipoSelecionado = $('#slcTiposEndereco option:selected').val();
	
	if(tipoSelecionado === 'outro-endereco'){
		$('#outroEndereco').prop('hidden', false);
	}else{
		$('#outroEndereco').prop('hidden', true);

	}
})

$('#slcFormasPagamento').change(function() {
	var valueSelected = $('#slcFormasPagamento option:selected').val();

	if (valueSelected === 'carteira') {
		$('#groupBandeiras').prop('hidden', true);
		$('#groupQuantidadeParcelas').prop('hidden', true);
	} else if (valueSelected === 'credito') {
		$('#groupBandeiras').prop('hidden', false);
		$('#groupQuantidadeParcelas').prop('hidden', false);
	}else if(valueSelected == 0){
		$('#groupBandeiras').prop('hidden', true);
		$('#groupQuantidadeParcelas').prop('hidden', true);
	} 
	else {
		$('#groupBandeiras').prop('hidden', false);
		$('#groupQuantidadeParcelas').prop('hidden', true);
	}

})