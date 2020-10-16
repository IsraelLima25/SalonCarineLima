$(function() {
	$('button[type=submit]').click(function(e) {
		e.preventDefault();
		$.post({
			url : '/SalonCarineLima/atendimento/filterData',
			method : 'GET',
			data : $('form[name=formFiltro]').serialize()
		})
		.then(function(data, textStatus, xhr) {
			atualizarTable(data);
		})
		.catch(function(err) {
			console.log(err);
		})

	})
});

function atualizarPagina(){
	window.location.href = "/SalonCarineLima/atendimento/listar";
}

function atualizarTable(data){
	var atendimentos = JSON.parse(data);	
	rendererTabela(atendimentos);
}


/*Scripts de paginação*/

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
		url: `/SalonCarineLima/atendimento/listar/json?page=${numeroPagina+1}`,
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
		url: `/SalonCarineLima/atendimento/listar/json?page=${numeroPagina-1}`,
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
		url: `/SalonCarineLima/atendimento/listar/json?page=${numeroPagina}`,
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

function rendererTabela (atendimentos) {
	limparTabela();
	
	if(atendimentos.length === 0){
		$('#msg-nenhum-registro-encontrado').prop("hidden", false);
	}else{
		$('#msg-nenhum-registro-encontrado').prop("hidden", true);
	}
	atendimentos.forEach(function(atendimento) {
		
		var dataFormatada = formatData(atendimento.data);
		var horaformatada = formatHora(atendimento.hora);
				
		$('#atendimento-table').append(
			`
			<tr>
				<td>${atendimento.cliente.nome}</td>
				<td>${dataFormatada}</td>
				<td>${horaFormatada}</td>
				<td class="text-center">
				<a type="button" class="fas fa-search btn btn-info"
				</td>
				
			</tr>	
			`
		);
	})
}

/**************************************************************************************/

function limparTabela(){
	$('#atendimento-table tr td').remove();
}

function redirect() {
    window.location.href = "../../modules/atendimento/manutencaoAtendimento.html";
}

function filtrar() {
    $('#atendimento-table').filterTable('#nome-filter');
}

function filtrar() {
    $('#atendimento-table').filterTable('#cliente-atendimento-filter');
}

$('#rbFilterCliente').click(function () {
    $('#groupFilterByData').prop('hidden', true);
    $('#groupFilterByClient').prop('hidden', false);
})

$('#rbFilterData').click(function () {
    $('#groupFilterByClient').prop('hidden', true)
    $('#groupFilterByData').prop('hidden', false);
})


