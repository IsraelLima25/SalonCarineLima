$(function() {
	$('button[type=submit]').click(function(e) {
		showLoader();
		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/servico/alterar',
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

function atualizarPagina(){
	limparCampos();
	limparMessagesErrors();
	topPage();
}

function limparCampos() {
	$('#descricao').val('');
	$('#preco').val('');
}

function limparMessagesErrors(){

	$('.descricao').prop('hidden',true);
	$('.descricao').text('');
	
	$('.preco').prop('hidden',true);
	$('.preco').text('');
}

function topPage() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
}

function removerServico(idServico) {
	showLoader();
	$.post({
		url : `/SalonCarineLima/servico/remover/${idServico}`,
		method : 'DELETE'
	})
	.then(
		function() {
			showModalMessageExcluir();
			hideLoader();
		})
	.catch(function(err) {
		showModalExclusaoInvalida();
		hideLoader();
	});	
}

function showModalMessageExcluir(){
	$('#modalMessageExclusaoValida').modal('show');
}

$('#modalMessageExclusaoValida').on('hidden.bs.modal', function(e) {
	window.location.href = "/SalonCarineLima/servico/listar";
});

function showModalExclusaoInvalida(){
	$('#modalMessageExlusaoInvalida').modal('show');
}




