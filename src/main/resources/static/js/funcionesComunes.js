
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

Este JS contiene las funciones comunes a toda la aplicación..
*/


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
});

$("img.lazy").lazyload({
	effect : "fadeIn"
});

/**
 * Setea la variable active en el menú
 */
$(function setActive() {
	var aObj = $('.menu-item').get();
	for (i = 0; i < aObj.length; i++) {
		if (document.location.href.indexOf($(aObj[i]).first().attr("href")) >= 0) {
			$(aObj[i]).parent().addClass("active");
		}
	}
});

/**
 * Determinael nombre del campeón usado enclases CSS
 * @param name
 * @returns el nombre del campeón normalizado.
 */
function determineClassChampionName(name) {
	if (name.search(" ") >= 0) {
		name = name.replace(" ", "-");
	}
	if (name.search("'") >= 0) {
		name = name.replace("'", "");
	}

	if (name.search(".") >= 0) {
		name = name.replace(".", "");
	}

	return name.toLowerCase();
}

/**
 * Esta funciónnormaliza el nombre del campeón paraloslinks
 * @param name
 * @returns El nombre del campeón
 */
function normalizeChampionName(name) {
	if (name.search(" ") >= 0) {
		name = name.replace(" ", "");
	}
	if (name.search("'") >= 0) {
		name = name.replace("'", "");
	}

	if (name.search(".") >= 0) {
		name = name.replace(".", "");
	}

	return name.toLowerCase();
}

/**
 * Esta función normalizala descripción de los campeones para sermostrada en HTML
 * @param description
 * @returns La descripción normalizada.
 */
function normalizeDescription(description) {
	if (description.search('"') >= 0) {
		description = description.replace(new RegExp('"', 'g'), "'");
	}
	return description;
}

/**
 * Obtiene un parametro de la queryString
 * @param sParam
 * @returns el valor
 */
function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

