/* Scroll Configuration */
$('.back-to-top').click(function() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
	return false;
});

$(window).scroll(function(event) {
	
	var scroll = $(window).scrollTop();
	
	if (scroll == 0) {
		$('.back-to-top').prop("hidden", true);
	} else {
		$('.back-to-top').prop("hidden", false);
	}
});

$('section').click(function() {
	$('.navbar-collapse').collapse('hide');
})

/*Lançar mensagens Erro Pagina*/
function thowErrorPage(errors){
	limparMessagesErrors();
	topPage();	
	let messageError;
	
	errors.listError.forEach(function(error) {
		/*
		 * Verifica se o a propriedade é um objeto, e se for quebra pegando a
		 * segunda parte que representa o seu atributo
		 */
		if(error.fieldName.includes('.')){
			error.fieldName = error.fieldName.split('.')[1];			
		}
		
		messageError = messageError + ', ' + $('.'+error.fieldName).text(error.message);
		$('.'+error.fieldName.split('.',1)).prop("hidden",false);		
	})	
}

/* Scripts Modal */

function showMessage(data) {
	$('#title').text(data.message.title);
	$('#body').text(data.message.body);
	$('#modalPage').modal('show');
}

function showMessageClient(title, body) {
	$('#title').text(title);
	$('#body').text(body);
	$('#modalPage').modal('show');
}
