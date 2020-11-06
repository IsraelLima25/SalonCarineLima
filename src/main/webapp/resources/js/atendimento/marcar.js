$(function() {
	$('button[type=submit]').click(function(e) {
		showLoader();
		e.preventDefault();
		$.post({
			url : '/SalonCarineLima/atendimento',
			method : 'POST',
			data : $('form[name=formMarcar]').serialize()
		})
		.then(function(data, textStatus, xhr) {
			 if (xhr.status === 201) {
		     limparMessagesErrors();
			 showModal(data);
			 hideLoader();
			}
		})
		.catch(function(err) {
			console.log(err);
			thowErrorPage(err.responseJSON);
			console.log("Fail request");
			console.log(err);
			hideLoader();
		})

	})
});

$('#modalPageMarcar').on('hidden.bs.modal', function(e) {
	window.location.href = "/SalonCarineLima/loja/itensLoja";
})

function showModal(data){
	$('#modalPageMarcar').modal('show');
}

function limparMessagesErrors(){
	
	$('.cliente').prop('hidden',true);
	$('.cliente').text('');
	
	$('.tipoPagamento').prop('hidden',true);
	$('.tipoPagamento').text('');
	
	$('.bandeiraCartao').prop('hidden',true);
	$('.bandeiraCartao').text('');
	
	$('.quantidadeParcelas').prop('hidden',true);
	$('.quantidadeParcelas').text('');
	
	$('.tipoEndereco').prop('hidden',true);
	$('.tipoEndereco').text('');
	
	$('.bairro').prop('hidden',true);
	$('.bairro').text('');
	
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
	
	if(tipoSelecionado === 'OUTRO_ENDERECO'){
		$('#outroEndereco').prop('hidden', false);
	}else{
		$('#outroEndereco').prop('hidden', true);

	}
})

$('#slcFormasPagamento').change(function() {
	var valueSelected = $('#slcFormasPagamento option:selected').val();

	if (valueSelected === 'ESPECIE') {
		$('#groupBandeiras').prop('hidden', true);
		$('#groupQuantidadeParcelas').prop('hidden', true);
	} else if (valueSelected === 'CREDITO') {
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