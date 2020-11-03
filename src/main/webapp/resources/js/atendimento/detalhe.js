/* Ao carregar a página sempre chama função para atualização da cor do estado */
if($('#status').val() === 'Atendido'){
	$('#status').css("color","green");
}else{
	$('#status').css("color","red");
}

$(function() {
	$('button[type=submit][id=confirmarLancamento]').click(function(e) {

		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/lancamento/lancar',
			method : 'POST',
			data : $('form[name=formLancamento]').serialize(),
		})
		.then(function(data, textStatus, xhr) {
			//chama modal de sucesso
			$('#modalSucesso').modal('show');
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
		})
	})
});

$(function() {
	$('button[type=submit][id=cancelarAtendimento]').click(function(e) {

		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/atendimento/cancelar',
			method : 'POST',
			data : $('form[name=formCancelamento]').serialize(),
		})
		.then(function(data, textStatus, xhr) {
			//chama modal de cancelamento
			$('#modalCancelado').modal('show');
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
		})
	})
});

$('#modalSucesso').on('hidden.bs.modal', function (e) {
	var idAtendimento = $('#atendimentoId').val();
	/*Corrigir para passar o id do atendimento e não a linha*/
	window.location.href = `/SalonCarineLima/atendimento/${idAtendimento}`; 
});

$('#modalCancelado').on('hidden.bs.modal', function (e) {
	window.location.href = `/SalonCarineLima/atendimento/listar`; 
});




