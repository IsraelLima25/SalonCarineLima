var contextCurrent = $('#contextPage').text();

$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function incrementarItem(idServico){
	$.ajax({
		url: `${contextCurrent}/carrinho/add/${idServico}`,
		method: 'GET'
		
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
		url: `${contextCurrent}/carrinho/remover/${idServico}`,
		method: 'GET'
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
		url: `${contextCurrent}/carrinho/remover/servico/${idServico}`,
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
		url: contextCurrent+'/carrinho/totalCarrinho',
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
	window.location.href = contextCurrent+"/carrinho/itensCarrinho";
}

function marcarAtendimento(){
	showLoader();
	$.ajax({
		url: contextCurrent+"/carrinho/totalCarrinho",
		method: 'GET',
		
	}).then(function(valorTotalCarrinho) {
		if(valorTotalCarrinho > 0){
			window.location.href = contextCurrent+"/atendimento/formMarcar";
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







