package com.leagueofsummoners.model.dto;

import lombok.Data;

import javax.persistence.*;

import com.leagueofsummoners.model.dao.tables.TableNames;


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

/**
 * Este bean representa la entidad items de la base de datos
 */

@Data
@Entity(name = TableNames.TABLE_ITEMS_ENTITY)
@Table(name = TableNames.TABLE_ITEMS)
public class ItemDTO {

    @Id
    @Column(name = TableNames.COLUMN_ITEMS_ITEM_ID)
    private Long idItem;

    @Column(name = TableNames.COLUMN_ITEMS_ITEM_NAME_ES)
    private String itemNameEs;

    @Column(name = TableNames.COLUMN_ITEMS_ITEM_DESC_ES)
    private boolean itemDescEs;

    @Column(name = TableNames.COLUMN_ITEMS_ITEM_ICON)
    private String itemIcon;


}