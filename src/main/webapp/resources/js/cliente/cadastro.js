$(function() {
	$('button[type=submit]').click(function(e) {
		e.preventDefault();
		showLoader();
		$.post({
			url : '/SalonCarineLima/cliente',
			method : 'POST',
			data : $('form[name=formCadastro]').serialize()
		})
		.then(function(data, textStatus, xhr) {
			if (xhr.status === 201) {
				showMessage(data);
				atualizarPagina();
				hideLoader();
			}
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
			hideLoader();
		})
	})
});

function limparCampos() {
	$('#nome').val('');
	$('#email').val('');
	$('#telefone').val('');
	$('#cep').val('');
	$('#logradouro').val('');
	$('#numero').val('');
	$('#ponto-referencia').val('');
	$('#complemento').val('');
	$('#bairro').val('');
}

function atualizarPagina(){
	limparCampos();
	limparMessagesErrors();
	topPage();
}

function limparMessagesErrors(){
	
	$('.nome').prop('hidden',true);
	$('.nome').text('');
	
	$('.email').prop('hidden',true);
	$('.email').text('');
	
	$('.telefone').prop('hidden',true);
	$('.telefone').text('');
	
	$('.logradouro').prop('hidden',true);
	$('.logradouro').text('');
	
	$('.bairro').prop('hidden',true);
	$('.bairro').text('');
	
	$('.complemento').prop('hidden',true);
	$('.complemento').text('');
	
	$('.numero').prop('hidden',true);
	$('.numero').text('');
	
	$('.pontoReferencia').prop('hidden',true);
	$('.pontoReferencia').text('');
}

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}
