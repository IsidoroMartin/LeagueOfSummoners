package com.leagueofsummoners.interceptors;

import com.leagueofsummoners.model.dto.UserDTO;
import com.leagueofsummoners.security.annotations.LoginAdminRequired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriUtils;

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
 * Autentica a un usuario administrador en la aplicación para mostrar o no mostrar el recurso
 * donde estaanotación este definida.
 */
@Component
public class AuthenticationAdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginAdminRequired adminLoginRequired = handlerMethod.getMethod().getAnnotation(LoginAdminRequired.class);
		UserDTO user = (UserDTO) httpServletRequest.getSession().getAttribute("userlogged");

		if (adminLoginRequired == null) {
			return true;
		}
		if (null != user && user.isAdmin()) {
			return true;
		} else {
			httpServletResponse.sendRedirect("/forbidden?url=" + UriUtils.encode("/login?error_message=No tiene permisos para visualizar esta pagina.", "UTF8"));
			return false;
		}
	}
}