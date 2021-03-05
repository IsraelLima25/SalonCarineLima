var contextCurrent = $('#contextPage').text();

function adicionarItemCarrinho (idServico){	
	showLoader();
	$.get({
		url : `${contextCurrent}/carrinho/add/${idServico}`,
		method : 'GET'		
	})
	.then(function(response) {
		showMessageClient("Serviço","Serviço adicionado ao carrinho com sucesso.");
		hideLoader();
	});
}