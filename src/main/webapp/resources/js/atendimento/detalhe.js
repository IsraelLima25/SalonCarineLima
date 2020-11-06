/* Ao carregar a página sempre chama função para atualização da cor do estado */
if($('#status').val() === 'Atendido'){
	$('#status').css("color","green");
}else{
	$('#status').css("color","red");
}

$(function() {
	$('button[type=submit][id=confirmarLancamento]').click(function(e) {
		showLoader();
		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/lancamento/lancar',
			method : 'POST',
			data : $('form[name=formLancamento]').serialize(),
		})
		.then(function(data, textStatus, xhr) {
			hideLoader();
			//chama modal de sucesso
			$('#modalSucesso').modal('show');
			
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
			hideLoader();
		})
	})
});

$(function() {
	$('button[type=submit][id=cancelarAtendimento]').click(function(e) {
		showLoader();
		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/atendimento/cancelar',
			method : 'POST',
			data : $('form[name=formCancelamento]').serialize(),
		})
		.then(function(data, textStatus, xhr) {
			hideLoader();
			//chama modal de cancelamento
			$('#modalCancelado').modal('show');
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
			hideLoader();
		})
	})
});

$('#modalSucesso').on('hidden.bs.modal', function (e) {
	var idAtendimento = $('#atendimentoId').val();
	window.location.href = `/SalonCarineLima/atendimento/${idAtendimento}`; 
});

$('#modalCancelado').on('hidden.bs.modal', function (e) {
	window.location.href = `/SalonCarineLima/atendimento/listar`; 
});




