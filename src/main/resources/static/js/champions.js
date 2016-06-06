/*
Autores= Juan José Ramírez & Isidoro Martín
Fecha= Junio de 2016
Licencia=  gp130
Version= 1.0
Descripcion= Proyecto final desarrollo de aplicaciones web. League of Summoners es una aplicación
enfocada a los jugadores del popular juego League of Legends, usando esta aplicación podrán acceder
a guías, detalles sobre campeones e incluso sus últimas partidas.

Copyright (C) 2016 Juan José Ramírez & Isidoro Martín
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/* este JS gestiona el javascript en la página de campeones. */

//Obtiene la lista de campeones
var champions = $('.champion');
// Llama a filter champions en el listener change
var filtrosMobile = $("#filtros-mobile select").change(filterChampions);

var divHolder = $("div.holder");

/**
 * Filtra los campeones.
 */
function filterChampions() {

	var inputValue = $("#campo-busqueda").val();
	campeonesFiltrados = filterChampionsByName(inputValue);
	var type = (this != undefined && this.nodeName == "SELECT") ? getSelectedSelectType()
			: getSelectedRadioType();
	campeonesFiltrados = filterChampionsByType(campeonesFiltrados, type);

	var html = buildHtmlChampions(campeonesFiltrados);
	if (html == "")
		html = "<div class='alert alert-warning fade in'>No existen campeones con estas caracteristicas</div>";
	$('#champions').hide().html(html).fadeIn(500);
	divHolder.jPages({
		containerID : "champions"
	});
}

/**
 * Filtra los campeones por nombre
 * 
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

/**
 * Filtra los campeones por tipo de campeon
 * 
 * @param listaFiltrada
 * @param type
 * @returns El Array con los campeones filtraods
 */
function filterChampionsByType(listaFiltrada, type) {
	var filtroType = [];
	if (type != "Todos") {
		$(listaFiltrada).each(function(index, value) {
			if ($(value).attr("data-type") == type) {
				filtroType.push(value);
			}
		});
	} else {
		filtroType = listaFiltrada;
	}
	return filtroType;
}
/**
 * Construye el HTML de champions.
 * 
 * @param listaChampsFiltrada
 * @returns Un string con el HTML
 */
function buildHtmlChampions(listaChampsFiltrada) {
	var html = "";
	var length = listaChampsFiltrada.length;
	$(listaChampsFiltrada)
			.each(
					function(index, championImg) {
						var classReady = (length - 1 == index) ? "ready" : "";
						var champName = championImg.attr("title");
						html += "<div class=\"champion\">";
						html += '<div class="col-xs-6 col-md-2 img-separation">';
						html += '<div class="panel panel-default" onclick="prepareModalWindow('
								+ determineIndexChampion(champName) + ')">';
						html += "<div class=\"panel-heading\">";
						html += "<span>" + champName + "<\/span>";
						html += "<\/div>";
						html += "<div class=\"panel-body\">";
						html += "<img alt='" + champName
								+ "' class=\"img-responsive lazy " + classReady
								+ "\" name=\"champion\" data-original=\""
								+ championImg.attr("data-original")
								+ "\" data-type=\"Mago\"" + " src=\""
								+ championImg.attr("src") + "\" title=\""
								+ champName + "\" style=\"display: block;\">";
						html += "<\/div>";
						html += "<\/div>";
						html += "<\/div>";
						html += "<\/div>";
					});
	return html;
}

/**
 * Obtiene el radio seleccionado.
 * 
 * @returns {El valor delradio}
 */
function getSelectedRadioType() {
	var types = $("#filtros input");
	var type = "no-type";
	types.each(function(index, value) {
		if (value.checked) {
			type = value.value;
			return;
		}
	});
	return type;
}

/*
 * Obtiene el tipo seleccionado en el select en la interfaz de móviles.
 */
function getSelectedSelectType() {
	return (filtrosMobile.val() != "Filtrar por tipo campeón") ? filtrosMobile
			.val() : "Todos";
}

/**
 * Filtra los campeones cuando un radio es pulsado
 */
var $unique = $('label.unique');
$unique.on('click', function(event) {
	if (!this.firstElementChild.checked) {
		this.firstElementChild.checked = true;
		filterChampions();
	}
});

/**
 * Esta funcion sirve para desplegar los detalles del campeón cuando la ruta sea
 * requerida:
 */
$(function displayChampionDetails() {
	var hash = window.location.hash;
	if (hash != "") {
		hash = hash.substring(1);
		if (hash == "Mago" || hash == "Tanque" || hash == "Jungla"
				|| hash == "Support" || hash == "Marksman") {
			$("#" + hash).click();
		}
		prepareModalWindow(determineIndexChampion(hash));
	}
});

/**
 * Prepara la ventana modal para el campeón seleccionado, (Así evitamos tener
 * que cargar todas las ventanas modales).
 * 
 * @param championIndex
 */
function prepareModalWindow(championIndex) {
	var championClicked = jsonChampions[championIndex];
	if (championClicked != undefined) {
		var modalLink = $('#modal-link');
		var normalizedName = normalizeChampionName(championClicked.championName);
		modalLink.attr("href", "#modals");
		console.log(championClicked)
		var modalWindow = "";
		modalWindow += '<div id="wrapper-modal" class="row">';
		modalWindow += '<div class="close-modals">';
		modalWindow += '<div class="btn-close-modal">X</div>';
		modalWindow += '</div>'
		modalWindow += '<div class="modal-contenido">';
		modalWindow += '<div class="nombre-campeones col-md-12 ">';
		modalWindow += '<h1>' + championClicked.championName + '</h1>';
		modalWindow += '<h3 class="champion-title" >'
				+ championClicked.championTitleES + '</h3>';
		modalWindow += '</div>'
		modalWindow += '<div class="champion-lore col-md-10"><h2>Historia de '
				+ championClicked.championName + "</h2>";
		modalWindow += '<p>'
				+ normalizeDescription(championClicked.championLoreES) + '</p>';
		modalWindow += '</div>'
		modalWindow += '<div class="spells">';
		modalWindow += '<h2>Hechizos</h2>';
		modalWindow += '<img src="'
				+ championClicked.passive.passiveIcon
				+ '" data-toggle="tooltip" data-html="true"  class="img-responsive lazy champion-spell" title=" '
				+ normalizeDescription(championClicked.passive.passiveDescriptionEs)
				+ '">';
		for (var i = 0; i < championClicked.spellsList.length; i++) {
			var title = normalizeDescription("<strong>"
					+ championClicked.spellsList[i].spellNameEs
					+ "</strong><br>"
					+ championClicked.spellsList[i].spellDescriptionEs);
			modalWindow += '<img src="'
					+ championClicked.spellsList[i].spellIcon
					+ '" data-toggle="tooltip" data-html="true"  class="img-responsive lazy champion-spell" title=" '
					+ title + '">';
		}
		modalWindow += '</div>';
		modalWindow += '</div>';
		modalWindow += '</div>';
		modalWindow += '</div>';
		$('#modals').html(modalWindow);
		animateModal(normalizedName, championClicked.splashArtUri);
		$('[data-toggle="tooltip"]').tooltip();
		$('#modal-link').click();
	}
}

/**
 * Animación alsalir de la ventana modal.
 * 
 * @param champName
 * @param splash
 */
function animateModal(champName, splash) {
	var modal = $('#modals');
	$("#modal-link").animatedModal({
		modalTarget : 'modals',
		animatedIn : 'zoomIn',
		animatedOut : 'bounceOutDown',
		color : '#1c1a34',
		wrapper : "modals",

		beforeOpen : function() {
			modal.css("display", "inherit");
		},
		afterOpen : function() {

		},
		beforeClose : function() {
		},
		afterClose : function() {

		}
	});
	var divModal = $('#' + champName);
	modal.css("background-image", "url('" + splash + "')");
	divModal.addClass("champion-modal-mobile")
}

/**
 * Determina el índice del campeón en el array dinamicamente generado por
 * Thymleaf
 * 
 * @param championName
 * @returns {Number}
 */
function determineIndexChampion(championName) {
	for (var i = 0; i < jsonChampions.length; i++) {
		if (normalizeChampionName(jsonChampions[i].championName) == normalizeChampionName(championName)) {
			return i;
		}
	}
}
