/* Ao carregar a página sempre chama função para atualização da cor do estado */
if($('#status').val() === 'Atendido'){
	$('#status').css("color","green");
}else{
	$('#status').css("color","red");
}

function updateStatusFromAtendido(){
	$('#status').val('Atendido');
	$('#status').css("color","green");
}

$(function() {
	$('button[type=submit]').click(function(e) {

		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/lancamento/lancar',
			method : 'POST',
			data : $('form[name=formDetalhe]').serialize(),
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

$('#modalSucesso').on('hidden.bs.modal', function (e) {
	updateStatusFromAtendido();
	topPage();
	
});

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}


