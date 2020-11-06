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
		url: `/SalonCarineLima/cliente/listar/json?page=${numeroPagina+1}`,
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
		url: `/SalonCarineLima/cliente/listar/json?page=${numeroPagina-1}`,
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
		url: `/SalonCarineLima/cliente/listar/json?page=${numeroPagina}`,
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

function rendererTabela (clientes) {
	limparTabela();
	
	if(clientes.length === 0){
		$('#msg-nenhum-registro-encontrado').prop("hidden", false);
	}else{
		$('#msg-nenhum-registro-encontrado').prop("hidden", true);
	}
	clientes.forEach(function(cliente) {
		
		$('#cliente-table').append(
			`
			<tr>
				<td>${cliente.id}</td>
				<td>${cliente.nome}</td>
				<td class="text-center">
				<a class="fas fa-search btn btn-info"
				 href="/SalonCarineLima/cliente/${cliente.id}" />
				</td>
			</tr>	
			`
		);
	})
}

$('#nome-filter').keyup(function() {
	var nomeFilter = $(this).val();
	setTimeout(() => {
		$.get({
			url: `/SalonCarineLima/cliente/filter?nome=${nomeFilter}`,
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

function limparTabela(){
	$('#cliente-table tr td').remove();
}

/**************************************************************************************/
