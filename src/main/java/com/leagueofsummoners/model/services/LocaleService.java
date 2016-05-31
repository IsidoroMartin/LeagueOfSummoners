package com.leagueofsummoners.model.services;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Esta es una clase que sobrescribe el listener de cambiar de idioma para añadir a una cookie el idioma de la
 * app
 *
 * @author Juanjors
 */
@Component
public class LocaleService extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {
        boolean result = super.preHandle(request, response, handler); //Invoco al método encargado de settear el locale al pedido por el navegador
        Cookie cookieLanguage = new Cookie("locale", LocaleContextHolder.getLocale().getLanguage());  //setteo cookie para que desde el cliente podamos ser conscientes del idioma del server
        cookieLanguage.setPath("/");//La ruta raíz
        response.addCookie(cookieLanguage); //lo meto en la respuesta
        return result;
    }
}
