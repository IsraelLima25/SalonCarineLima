function adicionarItemCarrinho (idServico){	
	$.get({
		url : `/SalonCarineLima/carrinho/add/${idServico}`,
		method : 'GET'		
	})
	.then(function(response) {
		showMessageClient("Serviço","Serviço adicionado ao carrinho com sucesso.");
	});
}