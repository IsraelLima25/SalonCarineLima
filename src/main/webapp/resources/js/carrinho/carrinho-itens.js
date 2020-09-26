function incrementarItem(idServico){
	$.ajax({
		url: `/SalonCarineLima/carrinho/add/${idServico}`,
		method: 'GET',
		
	}).then(function(response) {
		atualizarValoresServico(response);
		atualizarTotalCarrinho();
	}).catch(function(err) {
		console.log('fail request');
		console.log(err);
	})
}

function removerItem(idServico){
	$.ajax({
		url: `/SalonCarineLima/carrinho/remover/${idServico}`,
		method: 'GET',
		
	}).then(function(response) {
		atualizarValoresServico(response);
		atualizarTotalCarrinho();
	}).catch(function(err) {
		console.log('fail request');
		console.log(err);
	})
}

function removerServico(idServico){
	$.ajax({
		url: `/SalonCarineLima/carrinho/remover/servico/${idServico}`,
		method: 'DELETE',
		
	}).then(function(response) {
		atualizaCardsServicos(idServico);
	}).catch(function(err) {
		console.log('fail request');
		console.log(err);
	})
}

function atualizarTotalCarrinho(){
	$.ajax({
		url:'/SalonCarineLima/carrinho/totalCarrinho',
		method: 'GET'
	}).then(function(data) {
		atualizarValorTotalCarrinho(data);
	}).catch(function(err) {
		console.log('fail request');
		console.log(err);
	})
}

function atualizarValoresServico(servicoAtualizado){
	$("#quantidade-"+servicoAtualizado.servicoDTO.id).text(servicoAtualizado.quantidade);
	$("#total-"+servicoAtualizado.servicoDTO.id).text("R$ "+ servicoAtualizado.precoTotal.toFixed(2));
}

function atualizarValorTotalCarrinho(valorTotal){
	$("#total").text("R$ " + valorTotal.toFixed(2));
}

function atualizaCardsServicos(idServico){
	atualizarTotalCarrinho();
	window.location.href = "/SalonCarineLima/carrinho/itensCarrinho";
}







