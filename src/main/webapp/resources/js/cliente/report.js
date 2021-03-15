function gerarRelatorio(acao){
	
	let contextPage = $('#contextPage').text();
	let reportSelected = $('#slcTipoRelatorio option:selected').val();
	let uri = contextPage+"/jasperClientes/relatorios/pdf/clientesCadastrados?code="+reportSelected+"&acao="+acao; 
	window.location.href = uri;
}