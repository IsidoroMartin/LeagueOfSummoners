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

Este JS gestiona las funciones de lapágina de perfil.
*/


var $table = $('#table-matches');


/**
 * Obtiene mediante una petición Ajax los stats de las partidas
 * 
 * @param update actualiza de la BD de riot o no.
 */
function getMatchList(update) {
	 $('#table-matches tbody').html("");
	 var $loading = $('#ajax-loader').show();
    $.get("./api/summoner/matchlist?update=" + update,
            function(data) {
                var matchs = "";
                var noRankeds = $("#no-rankeds");
                if (data != null && data != "") {
                    data = data.reverse();
                    data.forEach(function(match) {
                            var colorTr = (match.winner) ? 'success' :
                                'danger';
                            var colorText = (match.winner) ? 'green' :
                                'red';
                            var result = (match.winner) ? "Victoria" :
                                'Derrota';
                            matchs += "<tr class='" + colorTr +
                                "'>";
                            matchs += "<td class='" + colorText +
                                "'>" + result + "</td>";
                            matchs += "<td>" + match.durationMins +
                                "</td>";
                            matchs += "<td> <i class='icon champions-lol-28 " +
                                determineClassChampionName(match.championName) +
                                "'></i>&nbsp;" +
                                match.championName + "</td>";
                            matchs += "<td class='hidden-xs'>";
                            matchs += ((match.itemDto0 != null && match.itemDto0.itemIcon != "NO ITEM") ? "<img src='" +
                                match.itemDto0.itemIcon +
                                "' : class='itemIcon' title='" +
                                match.itemDto0.itemNameEs +
                                "' alt='" +
                                match.itemDto0.itemNameEs +
                                "' />" :
                                "");
                            matchs += ((match.itemDto1 != null && match.itemDto1.itemIcon != "NO ITEM") ? "<img src='" +
                                match.itemDto1.itemIcon +
                                "' : class='itemIcon' title='" +
                                match.itemDto1.itemNameEs +
                                "' alt='" +
                                match.itemDto1.itemNameEs +
                                "' />" :
                                "")
                            matchs += ((match.itemDto2 != null && match.itemDto2.itemIcon != "NO ITEM") ? "<img src='" +
                                match.itemDto2.itemIcon +
                                "' : class='itemIcon' title='" +
                                match.itemDto2.itemNameEs +
                                "' alt='" +
                                match.itemDto2itemNameEs +
                                "' />" :
                                "")
                            matchs += ((match.itemDto3 != null && match.itemDto3.itemIcon != "NO ITEM") ? "<img src='" +
                                match.itemDto3.itemIcon +
                                "' : class='itemIcon' title='" +
                                match.itemDto3.itemNameEs +
                                "' alt='" +
                                match.itemDto3.itemNameEs +
                                "' />" :
                                "")
                            matchs += ((match.itemDto4 != null && match.itemDto4.itemIcon != "NO ITEM") ? "<img src='" +
                                match.itemDto4.itemIcon +
                                "' : class='itemIcon' title='" +
                                match.itemDto4.itemNameEs +
                                "' alt='" +
                                match.itemDto4.itemNameEs +
                                "' />" :
                                "")
                            matchs += ((match.itemDto5 != null && match.itemDto5.itemIcon != "NO ITEM") ? "<img src='" +
                                match.itemDto5.itemIcon +
                                "' : class='itemIcon' title='" +
                                match.itemDto5.itemNameEs +
                                "' alt='" +
                                match.itemDto5.itemNameEs +
                                "' />" :
                                "")
                            matchs += ((match.itemDto6 != null && match.itemDto6.itemIcon != "NO ITEM") ? "<img src='" +
                                match.itemDto6.itemIcon +
                                "' : class='itemIcon' title='" +
                                match.itemDto6.itemNameEs +
                                "' alt='" +
                                match.itemDto6.itemNameEs +
                                "' />" :
                                "")
                            matchs += "</td>";
                            matchs += "<td><img src='img/gold.png' alt='oro' title='oro'>" +
                                (match.goldEarned / 1000)
                                .toFixed(2) + "k</td>";
                            matchs += "<td class='hidden-xs'>" +
                                match.stats + "</td>";
                            matchs += "</tr>";
                        });
                }

                if (data == null || data == "" || data.length == 0) {
                    $table.css('visibility', 'hidden');
                    noRankeds.show();
                } else {
                    noRankeds.hide();
                    $table.css('visibility', 'visible');
                }
                $loading.hide();
                $('#table-matches tbody').html(matchs);
            });
};

/*
 * Cuando dasclick sobre delete guide seinvoca estafunción
 */
$('.deleteGuide').click(function(event) {
    var href = this;
    var tr = $(this).parent().parent();
    var tituloGuia = $(tr.find("a")[2]).html();

    swal({
        title: 'Estás segur@ de eliminar la guía?',
        text: "No podrás recuperar la guía",
        showCancelButton: true,
        type: 'warning',
        confirmButtonText: 'Eliminar',
        confirmButtonClass: 'btn btn-danger',
        cancelButtonClass: 'btn btn-success',
        confirmButtonColor:"#b92c28",
        preConfirm: function() {
            return new Promise(function(resolve) {
                swal.enableLoading();
                $.ajax({
                    url: href,
                    type: 'DELETE',
                    success: function(result) {
                        if (result.valid) {
                            tr.hide('slow', function() {
                                tr.remove();
                                $('#mensajeInfo').show();
                            });
                        } else {
                            $('#mensajeError').show();
                        }
                        resolve();
                    }
                });

            });
        },
        allowOutsideClick: false
    });

    return false;
});