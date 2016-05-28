var guides = $('.guide');
function findGuidesByChampionOrGuideTitle(filter) {
	var inputValue = $('#campo-busqueda').val();
	if (filter != undefined)
		inputValue = filter;
	// Me creo una expresión regular con lo que ha introducido el usuario
	defaults.perPage = 5;
	var regex = new RegExp(inputValue, "i");
	var html = "";
	guides.each(function(index, value) {
		var div = $(this);
		var champName = div.find("h2")[0].innerHTML;
		var guideTitle = div.find("h3")[0].innerHTML;
		var username = div.find("p")[0].innerHTML;
		if (champName.search(regex) != -1 || guideTitle.search(regex) != -1
				|| username.search(regex) != -1 || inputValue == "") {
			html += div[0].outerHTML;
		}
	});
	if (html == "")
		html = "<div class='alert alert-warning fade in'>No existen guías con estas caracteristicas</div>";

	$('#guias').hide().html(html).fadeIn(500);

	$("div.holder").jPages({
		containerID : "guias"
	});
}

$(findGuidesByChampionOrGuideTitle(""));

$(".tt-menu").click(function() {
	findGuidesByChampionOrGuideTitle($('#campo-busqueda').val());
});

$(function() {
	var search_input = getUrlParameter("search_input");
	if (search_input != undefined) {
		$('#campo-busqueda').val(search_input);
		findGuidesByChampionOrGuideTitle(search_input);
	}
});

$('.unique').click(function() {
	findGuidesByChampionOrGuideTitle(this.value != "Todos" ? this.value : "");
});