$(function() {
	$('button[type=submit]').click(function(e) {

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

function removerServico(idServico) {
	$.post({
		url : `/SalonCarineLima/servico/remover/${idServico}`,
		method : 'DELETE'
	})
	.then(
		function(proximoServico) {
			console.log(proximoServico.servico);
			if (proximoServico.servico === null) {
				window.location = "/SalonCarineLima/servico/listar";
				} else {
					window.location = `/SalonCarineLima/servico/${proximoServico.servico.id}`;
				}
		});	
}

function servicoProximo(idServico) {
	$.get({
		url : `/SalonCarineLima/servico/proximo/${idServico}`,
		method : 'GET',
		success : function(data, textStatus, xhr) {
			if (xhr.status === 200) {
				console.log(data);
				window.location = `/SalonCarineLima/servico/${data.servico.id}`
			}
		}
	});
}

function servicoAnterior(idServico) {
	$.get({
		url : `/SalonCarineLima/servico/anterior/${idServico}`,
		method : 'GET',
		success : function(data, textStatus, xhr) {
			if (xhr.status === 200) {
				console.log(data);
				window.location = `/SalonCarineLima/servico/${data.servico.id}`
			}
		}
	});
}
