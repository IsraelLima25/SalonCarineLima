var contextCurrent = $('#contextPage').text();

$(function() {
	$('button[type=submit]').click(function(e) {
		e.preventDefault();
		showLoader();
		$.post({
			url : contextCurrent+'/cliente/alterar',
			method : 'POST',
			data : $('form[name=formDetalhar]').serialize(),

		})
		.then(function(data, textStatus, xhr) {
			if (xhr.status === 200) {
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
	showLoader();
	$.post({
		url : `${contextCurrent}/cliente/remover/${idCliente}`,
		method : 'DELETE'
	})
	.then(function() {
		showModalMessageExcluir();
		hideLoader();
	})
	.catch(function(err) {
		showModalExclusaoInvalida();
		hideLoader();
	});
}

function showModalMessageExcluir(){
	$('#title').text('Cliente');
	$('#body').text('cliente excluido com sucesso.');
	$('#modalMessageExclusaoValida').modal('show');
}

function showModalExclusaoInvalida(){
	$('#modalMessageExlusaoInvalida').modal('show');
}

$('#modalMessageExclusaoValida').on('hidden.bs.modal', function(e) {
	window.location.href = `${contextCurrent}/cliente/listar`;
})

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}
