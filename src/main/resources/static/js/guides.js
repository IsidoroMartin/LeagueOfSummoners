var guides = $('.guide');
function findGuidesByChampionOrGuideTitle(inputValue){
	   // Me creo una expresión regular con lo que ha introducido el usuario
    var regex = new RegExp(inputValue, "i");
    var html = "";
    guides.each(function (index, value) {
    	var div= $(this);
    	var champName = div.find("h2")[0].innerHTML;
    	var guideTitle = div.find("h3")[0].innerHTML;
    	var username = div.find("p")[0].innerHTML;
        if (champName.search(regex) != -1 || guideTitle.search(regex)  != -1 || username.search(regex) != -1) {
            html += div.html();
        }
    });
    if (html == "")
    	html = "<div class='alert alert-warning fade in'>No existen guías con estas caracteristicas</div>";
        $('#guias').hide().html(html).fadeIn(500);
}

$(".tt-menu").click(function() {
	findGuidesByChampionOrGuideTitle($('#campo-busqueda').val());
});