setTimeout(() => {
			$('#mensagemServer').fadeOut('slow');
		}, 2000);

function filtrar() {
    $('#cliente-table').filterTable('#nome-filter');
}

function removerCliente(){
	var idAtual = $('#registroAtual').val()
	$.ajax({
		url: `http://localhost:8080/SalonCarineLima/cliente/remover/${idAtual}`,
		method: 'DELETE',
		
	}).then(function(data) {
		removeLineTable(data.id);
		$('#mensagemClient').addClass(`alert alert-${data.mensagem.classe}`);
		$('#mensagemClient').text(data.mensagem.text);
		$('#mensagemClient').prop("hidden", false);
		setTimeout(() => {
			$('#mensagemClient').fadeOut('slow');
		}, 2000);
	})
	.catch(function(err) {
		console.log('fail request')
		console.log(err);
	})
}

function atualRemover(id){
	$('#registroAtual').val('')
	$('#registroAtual').val(id)	
}

function removeLineTable(id){	
	$('#cliente-table tr').each(function(){
		var line = $(this);
	    $(this).find('td').each(function(){
	    	if($(this).text() == id){
	    		line.fadeOut('slow');
	    	}
	    })
	})
}
