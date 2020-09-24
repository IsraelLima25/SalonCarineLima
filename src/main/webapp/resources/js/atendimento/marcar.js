function adicionarItemSelecionadoAoCarrinho() {
	let idServico = ($("#selectServicos option:selected").text().split("-")[0]);
	buscarItemServer(idServico);
}

function adicionarItemLineTableCarrinho(response) {

	$("table tbody").empty();
	
	response.forEach(function(item) {
		var line = "<tr>" +
		"<td>"+item.servico.descricao+"</td>" +
		"<td class='text-center'><a href=''><span>-</span></a>"+item.quantidade+"<a href=''><span>+</span></a></td>" +
		"<td class='text-center'><a type=button><i class= 'fas fa-trash-alt obrigatorio'></i></a></td>"
		"</tr>";
		
		$("table tbody").append(line);
	});
}

$('#rbOutroEndereco').click(function() {
	$('#outroEndereco').prop('hidden', false);
})

$('#rbEnderecoCliente').click(function() {
	$('#outroEndereco').prop('hidden', true);
})

$('#slcFormaPagamento').change(function() {
	var valueSelected = $('#slcFormaPagamento option:selected').val();

	if (valueSelected === 'cart') {
		$('#groupBandeiras').prop('hidden', true);
		$('#groupQuantidadeParcelas').prop('hidden', true);
	} else if (valueSelected === 'cred') {
		$('#groupBandeiras').prop('hidden', false);
		$('#groupQuantidadeParcelas').prop('hidden', false);
	} else {
		$('#groupBandeiras').prop('hidden', false);
		$('#groupQuantidadeParcelas').prop('hidden', true);
	}

})