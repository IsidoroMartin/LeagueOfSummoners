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

Este JS Muestra los nombres de los campeones bajo los inputs search.
*/


var champsToken = [];
for (var i = 0;i<champion_list.length;i++){
    champsToken[i] = {name_champion:champion_list[i]};
}
var champions = new Bloodhound({
    datumTokenizer: function (d) {
        return Bloodhound.tokenizers.whitespace(d.name_champion);
    },
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    local:champsToken
});
champions.initialize();
$('.input_champions').typeahead(null, {
    displayKey: 'name_champion',
    source: champions.ttAdapter()
});

