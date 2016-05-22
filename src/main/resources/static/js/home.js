/**
 *  Cuando das clic sobre ver la rotación, scrollea la pantalla hasta mostrar la lista de campeoens
 */
$(function(){
	$('a[href^="#"]:not(a[href="#"])').on('click', function(e) {
		e.preventDefault();
		var target = this.hash;
		var $target = $(target);
		$('html, body').stop().animate({
			'scrollTop' : ($target.offset().top - 50)
		}, 900, 'swing');
	});
});

function showChampionDetails(champion){
	window.location.href = "/champions#" + normalizeChampionName(champion);
}