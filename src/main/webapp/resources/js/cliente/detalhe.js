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

function clienteProximo(idCliente) {
	$.get({
		url : `/SalonCarineLima/cliente/proximo/${idCliente}`,
		method : 'GET',
		success : function(data, textStatus, xhr) {
			if (xhr.status === 200) {
				console.log(data);
				window.location = `/SalonCarineLima/cliente/${data.cliente.id}`
			}
		}
	});
}

function clienteAnterior(idCliente) {
	$.get({
		url : `/SalonCarineLima/cliente/anterior/${idCliente}`,
		method : 'GET',
		success : function(data, textStatus, xhr) {
			if (xhr.status === 200) {
				console.log(data);
				window.location = `/SalonCarineLima/cliente/${data.cliente.id}`
			}
		}
	});
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
	.then(
		function(proximoCliente) {
			console.log(proximoCliente.cliente);
			if (proximoCliente.cliente === null) {
				window.location = "/SalonCarineLima/cliente/listar";
				} else {
					window.location = `/SalonCarineLima/cliente/${proximoCliente.cliente.id}`;
				}
		});
	
}

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}
