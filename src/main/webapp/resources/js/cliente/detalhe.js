$(function() {
	$('button[type=submit]').click(function(e) {

		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/cliente/alterar',
			method : 'POST',
			data : $('form[name=formDetalhar]').serialize(),

		})
		.then(function(data, textStatus, xhr) {
			if (xhr.status === 200) {
			showMessage(data);
			atualizarPagina();
		}
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
		})
	})
});

function thowErrorPage(errors){
	topPage();	
	let messageError;
	errors.listError.forEach(function(error) {
		messageError = messageError + ', ' + $('.'+error.fieldName).text(error.message);
		$('.'+error.fieldName).prop("hidden",false);		
	})	
}

function atualizarPagina(){
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

function removerCliente(idCliente) {
	$.post({
		url : `/SalonCarineLima/cliente/remover/${idCliente}`,
		method : 'DELETE'
	})
	.then(function() {
		window.location.href = `/SalonCarineLima/cliente/listar`;
	});
}

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}
