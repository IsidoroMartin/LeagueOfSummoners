var champions = $('.champion');
var filtrosMobile = $("#filtros-mobile select").change(filterChampions);

function filterChampions() {
	var inputValue = $("#campo-busqueda").val();
	campeonesFiltrados = filterChampionsByName(inputValue);
	var type = (this != undefined && this.nodeName == "SELECT") ? getSelectedSelectType() : getSelectedRadioType(); 
	campeonesFiltrados = filterChampionsByType(campeonesFiltrados, type);
	
	var html = buildHtmlChampions(campeonesFiltrados);
	if (html == "")
		html = "<div class='alert alert-warning fade in'>No existen campeones con estas caracteristicas</div>";
	$('#champions').hide().html(html).fadeIn(500);
}


/**
 * Filtra los campeones por nombre
 * @param inputValue
 * @returns {Array}
 */
function filterChampionsByName(inputValue) {
	// Me creo una expresión regular con lo que ha introducido el usuario
	var regex = new RegExp(inputValue, "i");
	var champsFiltrados = [];
	champions.each(function(index, value) {
		var championImg = $(value).find("img")
		var champName = championImg.attr("title");
		if (champName.search(regex) != -1) {
			champsFiltrados.push($(value).find("img"));
		}
	});
	return champsFiltrados;
}

function filterChampionsByType(listaFiltrada, type){
	var filtroType = [];
	if(type != "no-type"){
	$(listaFiltrada).each(function(index,value){
		if($(value).attr("data-type") == type){
			filtroType.push(value);
		}
	});
	}else{
		filtroType = listaFiltrada;
	}
	return filtroType;
}

function buildHtmlChampions(listaChampsFiltrada){
	var html = "";
		$(listaChampsFiltrada).each(function(index, championImg) {
			var champName = championImg.attr("title");
				html += "<div class=\"champion\">";
				html += '<div class="col-xs-6 col-md-2 img-separation">';
				html += '<div class="panel panel-default" onclick="document.getElementById(this.getAttribute(\'data-model\')).click()" data-model="link' + normalizeChampionName(champName) + '">';
				html += "<div class=\"panel-heading\">";
				html += "<span>" + champName + "<\/span>";
				html += "<\/div>";
				html += "<div class=\"panel-body\">";
				html += "<img class=\"img-responsive lazy\" name=\"champion\" data-original=\"" + championImg.attr("data-original") + "\" data-type=\"Mago\"" +
						" src=\"" + championImg.attr("src") + "\" title=\"" + champName + "\" style=\"display: block;\">";
				html += "<\/div>";
				html += "<\/div>";
				html += "<\/div>";
				html += "<\/div>";
			}
		);
	return html;
}


function getSelectedRadioType(){
	var types = $("#filtros input");
	var type = "no-type";
	types.each(function(index, value) {
		if(value.checked){
			type = value.value; 
			return;
		}
	});
	return type;
}

function getSelectedSelectType(){
	return (filtrosMobile.val() != "Filtrar por tipo campeón") ? filtrosMobile.val() : "no-type";
}


/**
 * Este método controla que los radibox se puedan desactivar,además también sirve de listener
 * para filtrar campeones, ya que dependede que elcheckbox cambie de estado
 */
var $unique = $('input.unique');
$unique.on('click', function(event){
    var $this = $(this);
    var was_checked = $this.data('checked');
    $('input[name='+$this.prop('name')+']').data('checked', false);
    $this.data('checked', !was_checked).prop('checked', !was_checked);
    filterChampions("");
});


