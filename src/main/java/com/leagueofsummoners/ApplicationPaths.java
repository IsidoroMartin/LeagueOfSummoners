package com.leagueofsummoners;

import java.io.File;
import java.io.IOException;

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
 * En esta clase están todas las rutas posibles de la aplicación
 */
public class ApplicationPaths {

	/**
	 * Generación de rutas dependiendo de donde se este ejecutando la app.
	 */
	static {
		try {
			// GALLERY_DEVELOP_SAVE_PATH = new File(".").getCanonicalPath() +
			// File.separator + "src" + File.separator + "main"
			// + File.separator + "resources" + File.separator + "static" +
			// File.separator + "img" + File.separator + "avatars" +
			// File.separator;

			GALLERY_DEVELOP_SAVE_PATH = new File(".").getCanonicalPath() + File.separator + "webapps" + File.separator
					+ "ROOT" + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "static"
					+ File.separator + "img" + File.separator + "avatars";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String GALLERY_DEVELOP_SAVE_PATH;
	public static final String ROOT_PATH = "/";
	public static final String INDEX_HTML_PATH = ROOT_PATH + "index.html";
	public static final String INDEX_PATH = "/index";
	public static final String HOME_PATH = "/home";

	public static final String REGISTER_PATH = "/register";
	public static final String LOGIN_PATH = "/login";
	public static final String PROFILE_PATH = "/profile";
	public static final String PROFILE_LOGOUT = "/logout";

	public static final String CHAMPIONS_PATH = "/champions";
	public static final String CHAMPIONS_HTML_PATH = "/champions.html";

	public static final String SUMMONER_MATCH_STADISTICS_HTML_PATH = "/summonermatchstadistics.html";
	public static final String SUMMONER_MATCH_STADISTICS_PATH = "/summonermatchstadistics";

	public static final String GUIDES_HTML_PATH = "/guides.html";
	public static final String GUIDES_BORRAR_PATH = "/borrar/{id}";
	public static final String GUIDES_PATH = "/guides";

	public static final String SEARCH_PATH = "/search";

	public static final String COOKIES_PATH = "/cookies";
	
	public static final String AYUDA = "/help";

	public static final String P_PRIVACIDAD_PATH = "/p_privacidad";
	public static final String MAPA_WEB = "/webmap";
	public static final String LICENCIA = "/licencia";

    public static final String VIEW_GUIDE_HTML_PATH = "/view_guide.html";
    public static final String VIEW_GUIDE_PATH = "/view_guide";
    public static final String CONTACT= "/contact";


    // Rest Services Path

	public static final String REST_API_USER = "/api/user";
	public static final String REST_API_USER_USERNAME = "/username";
	public static final String REST_API_USER_EMAIL = "/email";
	public static final String REST_API_USER_SUMMONERNAME = "/summonername";
	public static final String REST_API_USER_UPDATE_PERMISSION_LEVEL = "/update/permission";
	public static final String REST_API_USER_DELETE_USER = "/delete/{username}";

	public static final String REST_API_SUMMONER = "/api/summoner";
	public static final String REST_API_SUMMONER_MATCHLIST = "/matchlist";

	public static final String USER_LIST = "/userlist";

}
