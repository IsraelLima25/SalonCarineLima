$('.back-to-top').click(function() {
	$("html, body").animate({
		scrollTop : 0
	}, 600);
	return false;
});

$(window).scroll(function(event) {
	var scroll = $(window).scrollTop();
	if (scroll === 0) {
		$('.back-to-top').css("display", "none");
	} else {
		$('.back-to-top').css("display", "block");
	}
});

$('section').click(function() {
	$('.navbar-collapse').collapse('hide');
})

/* Scripts Modal */

function showMessage(data) {
	$('#title').text(data.message.title);
	$('#body').text(data.message.body);
	$('#modalPage').modal('show');
}

$('#modalPage').on('hidden.bs.modal', function(e) {
	document.location.reload(true);
})
