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

 Este JS contiene las funciones encargadas de gestionar la página de guias.
 */

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

		// Muestro el HTML generado
		$('#guias').hide().html(html).fadeIn(500);

		// Le añado la paginación una vezque el HTML es cargado.
		$("div.holder").jPages({
			containerID : "guias"
		});
	}

// Cuando das click sobre un campeón que se muestra en el campo de búsqueda se
// ejecuta búsqueda
// directamente.
$(".tt-menu").click(function() {
	findGuidesByChampionOrGuideTitle($('#campo-busqueda').val());
});

/**
 * En el eventoonchange del móvil se ejecuta labúsqueda
 */
$("#filtros-mobile select").change(function() {
	findGuidesByChampionOrGuideTitle(this.value != "Todos" ? this.value : "");
});

/**
 * Si nos viene un search input por parametro buscamos ese campeón y lo seteamos
 * en el campo de búsqueda
 */
$(function() {
	var search_input = getUrlParameter("search_input");
	if (search_input != undefined) {
		$('#campo-busqueda').val(search_input);
		findGuidesByChampionOrGuideTitle(search_input);
	} else {
		findGuidesByChampionOrGuideTitle("")
	}
});

/**
 * Si el radio es pulsado ejecutamos la búsqueda.
 */
$('.unique')
		.click(
				function() {
					var valorText = this.firstElementChild.value != "Todos" ? this.firstElementChild.value
							: "";
					var campoBusqueda = $('#campo-busqueda');
					if (valorText != campoBusqueda.val()) {
						campoBusqueda.val(valorText);
						findGuidesByChampionOrGuideTitle(valorText);
					}

				});