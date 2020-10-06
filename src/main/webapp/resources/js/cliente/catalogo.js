function filtrar() {
    $('#cliente-table').filterTable('#nome-filter');
}

function detalharCliente(idCliente){
	
	window.location.href = `/SalonCarineLima/cliente/${idCliente}`;
}


