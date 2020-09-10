$(function() {
	$('button[type=submit]').click(function(e) {

		e.preventDefault();

		$.post({
			url : '/SalonCarineLima/cliente',
			method : 'POST',
			data : $('form[name=formCadastro]').serialize(),
			success : function(data, textStatus, xhr) {

				if (xhr.status === 201) {
					showMessage(data);
				}
			}
		})
	})
});
