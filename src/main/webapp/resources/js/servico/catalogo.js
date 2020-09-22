function filtrar() {
	$('#servico-table').filterTable('#nome-filter');
}

function detalharCliente(id) {
	window.location.href = `/SalonCarineLima/servico/${id}`;
}

function removerCliente(){
	var idAtual = $('#registroAtual').val()
	$.ajax({
		url: `/SalonCarineLima/servico/remover/${idAtual}`,
		method: 'DELETE',
		
	});	
}
