$('.back-to-top').click(function () {
    $("html, body").animate({ scrollTop: 0 }, 600);
    return false;
});

$(window).scroll(function (event) {
    var scroll = $(window).scrollTop();
    if (scroll === 0) {
        $('.back-to-top').css("display", "none");
    } else {
        $('.back-to-top').css("display", "block");
    }
});

$('section').click(function () {
    $('.navbar-collapse').collapse('hide');
})