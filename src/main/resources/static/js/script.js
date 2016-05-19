/**
 * Esta función controla la bandera que se muestra en el header básandose en la
 * cookie enviada por el servidor
 */
$(function() {
	var locale = $.cookie("locale");
	$(".locale").each(function() {
		if ($(this).val() == locale) {
			$(this).attr("selected", "");
		}
	});
	$('a[href^="#"]:not(a[href="#"])').on('click', function(e) {
		e.preventDefault();
		var target = this.hash;
		var $target = $(target);
		$('html, body').stop().animate({
			'scrollTop' : ($target.offset().top - 50)
		}, 900, 'swing');
	});
});

$("img.lazy").lazyload({
	effect : "fadeIn"
});

function setActive() {
	var aObj = $('.menu-item').get();
	for (i = 0; i < aObj.length; i++) {
		if (document.location.href.indexOf($(aObj[i]).first().attr("href")) >= 0) {
			$(aObj[i]).parent().addClass("active");
		}
	}
}
window.onload = setActive;