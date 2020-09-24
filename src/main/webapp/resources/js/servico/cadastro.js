$(function() {
	$('button[type=submit]').click(function(e) {
		e.preventDefault();
		$.post({
			url : '/SalonCarineLima/servico',
			method : 'POST',
			data : $('form[name=formCadastro]').serialize()
		})
		.then(function(data, textStatus, xhr) {
			if (xhr.status === 201) {
				showMessage(data);
				atualizarPagina();
			}
		})
		.catch(function(err) {
			thowErrorPage(err.responseJSON);
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
