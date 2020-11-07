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

$('#servico-filter').keyup(function() {
	var nomeFilter = $(this).val();
	setTimeout(() => {
		$.get({
			url: `/SalonCarineLima/servico/filter?nome=${nomeFilter}`,
			success: function(response) {
				rendererTabela(response);
			},
			fail: function(erro) {
				console.log('Fail Request');
				console.log(erro);
			}
		})
	}, 1000);
});

/****************** Scripts de paginação ***********************************/


function removerActiveButton(){
	$("nav ul li").removeClass("active");
}

function updateButtonActive(numeroPagina){
	removerActiveButton();
	$("#"+numeroPagina).addClass("active");
	atualizarPaginaAtualPagina(numeroPagina);
}

function buttonAtual(){
	return	$("li").filter("[class~='active']")[0].id;
}

function updateNextButtonActive(numeroPagina){
	var idProximo = parseInt(buttonAtual())+1;
	removerActiveButton();
	$("#"+idProximo).addClass("active");
}

function updatePreviousButtonActive(numeroPagina){
	var idAnterior = parseInt(buttonAtual())-1;
	removerActiveButton();
	$("#"+idAnterior).addClass("active");
}

function proximaPagina(totalPaginas){
	var pageAtual = parseInt($("#paginaAtual").text());
	if(pageAtual < totalPaginas-1){
		getNextPage(pageAtual);
		atualizarPaginaAtualPagina(pageAtual+1);
	}
}

function atualizarPaginaAtualPagina(pageAtual){
	$("#paginaAtual").text(pageAtual);
}

function paginaAnterior (totalPaginas){
	var pageAtual = parseInt($("#paginaAtual").text());
	if(pageAtual !== 0){
		getPreviousPage(pageAtual);
		atualizarPaginaAtualPagina(pageAtual-1);
	}
}

function getNextPage(numeroPagina){
	$.get({
		url: `/SalonCarineLima/servico/listar/json?page=${numeroPagina+1}`,
		success : function(response) {
			rendererTabela(response.content);
			updateNextButtonActive(numeroPagina);
		},
		fail: function(erro) {
			console.log("Request fail");
			console.log(erro);
		}
	})
}

function getPreviousPage(numeroPagina){
	$.get({
		url: `/SalonCarineLima/servico/listar/json?page=${numeroPagina-1}`,
		success : function(response) {
			rendererTabela(response.content);
			updatePreviousButtonActive(numeroPagina);
		},
		fail: function(erro) {
			console.log("Request fail");
			console.log(erro);
		}
	})
}

function getPage(numeroPagina){
	$.get({
		url: `/SalonCarineLima/servico/listar/json?page=${numeroPagina}`,
		success : function(response) {
			rendererTabela(response.content);
			updateButtonActive(numeroPagina);
		},
		fail: function(erro) {
			console.log("Request fail");
			console.log(erro);
		}
	})
}

function formatData(milessegundo){
	var data = new Date(milessegundo),
    dia  = data.getDate().toString().padStart(2, '0'),
    mes  = (data.getMonth()+1).toString().padStart(2, '0'), //+1 pois no getMonth Janeiro começa com zero.
    ano  = data.getFullYear();
	var dataFormatada = dia+"/"+mes+"/"+ano;
	return dataFormatada;
}

function formatHora(hora){
	var array= hora.split(":");
	horaFormatada = array[0]+":"+array[1];
	return horaFormatada;
}

function rendererTabela (servicos) {
	limparTabela();
	
	if(servicos.length === 0){
		$('#msg-nenhum-registro-encontrado').prop("hidden", false);
	}else{
		$('#msg-nenhum-registro-encontrado').prop("hidden", true);
	}
	servicos.forEach(function(servico) {
		
		//var preco = parseFloat(servico.preco).toFixed(2);
		var preco = servico.preco.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
		$('#servico-table').append(
			`
			<tr>
				<td>${servico.descricao}</td>
				<td>${preco}</td>
				<td class="text-center">
				<a class="fas fa-search btn btn-info"
				 href="/SalonCarineLima/servico/${servico.id}" />
				</td>
			</tr>	
			`
		);
	})
}

function limparTabela(){
	$('#servico-table tr td').remove();
}

/**************************************************************************************/
