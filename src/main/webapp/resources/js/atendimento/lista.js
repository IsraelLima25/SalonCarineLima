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

function rendererTabela (atendimentos) {
	limparTabela();
	
	if(atendimentos.length === 0){
		$('#msg-nenhum-registro-encontrado').prop("hidden", false);
	}else{
		$('#msg-nenhum-registro-encontrado').prop("hidden", true);
	}
	atendimentos.forEach(function(atendimento) {
		$('#atendimento-table').append(
			`
			<tr>
				<td>${atendimento.clienteNome}</td>
				<td>${atendimento.data}</td>
				<td>${atendimento.hora}</td>
				<td class="text-center">
				<a type="button" class="fas fa-search btn btn-info"
				</td>
				
			</tr>	
			`
		);
	})
}

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
