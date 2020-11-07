function incrementarItem(idServico){
	$.ajax({
		url: `/SalonCarineLima/carrinho/add/${idServico}`,
		method: 'GET',
		
	}).then(function(response) {
		console.log(response);
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
	$("#total-"+servicoAtualizado.servicoDTO.id).text
	(servicoAtualizado.precoTotal.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }));
}

function atualizarValorTotalCarrinho(valorTotal){
	$("#total").text(valorTotal.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }));
}

function atualizaCardsServicos(idServico){
	atualizarTotalCarrinho();
	window.location.href = "/SalonCarineLima/carrinho/itensCarrinho";
}

function marcarAtendimento(){
	showLoader();
	$.ajax({
		url: "/SalonCarineLima/carrinho/totalCarrinho",
		method: 'GET',
		
	}).then(function(valorTotalCarrinho) {
		if(valorTotalCarrinho > 0){
			window.location.href = "/SalonCarineLima/atendimento/formMarcar";
			hideLoader();
		}else{
			showMessageClient("Carrinho Vazio","Nenhum serviço foi adicionado ao carrinho");
			hideLoader();
		}
	}).catch(function(err) {
		console.log('fail request');
		console.log(err);
		hideLoader();
	})
}







