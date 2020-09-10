function habilitarCampos() {
	$('#nome').prop('disabled', false);
	$('#email').prop('disabled', false);
	$('#telefone').prop('disabled', false);
	$('#cep').prop('disabled', false);
	$('#logradouro').prop('disabled', false);
	$('#numero').prop('disabled', false);
	$('#ponto-referencia').prop('disabled', false);
	$('#complemento').prop('disabled', false);
	$('#bairro').prop('disabled', false);
	$('#btn-confirmar-alteracao').prop('disabled', false);
}

$(function() {
	$('button[type=submit]').click(function(e) {

		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/cliente/alterar',			
			method : 'POST',
			data : $('form[name=formDetalhar]').serialize(),
			success : function(data, textStatus, xhr) {

				if (xhr.status === 200) {
					showMessage(data);
				}
			}
		});
	})
});

function removerCliente(idCliente){
	
	$.post({
		url : `/SalonCarineLima/cliente/remover/${idCliente}`,			
		method : 'DELETE'
	})
	.then(function(proximoCliente) {
		console.log(proximoCliente);		
	});
}









