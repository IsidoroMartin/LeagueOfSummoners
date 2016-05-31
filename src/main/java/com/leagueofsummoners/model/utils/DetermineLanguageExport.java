package com.leagueofsummoners.model.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

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
 * Esta clase determina el idioma a utilziar
 */
public class DetermineLanguageExport {

	/**
	 * Recibe el objeto del que se quiere invocar el método, el locale (el idioma en el que se quiere obtener), 
	 * y el nombre del método (Si en el idioma de las dos últimas letras)
	 * @param obj
	 * @param locale
	 * @param nombreMetodoMultiLang
	 * @return La ejecución del método
	 */
	public static Object getProperLanguage(Object obj, Locale locale, String nombreMetodoMultiLang) {
		String lang = locale.getLanguage();
		lang = (lang == null) ? "ES" : lang.toUpperCase();
		lang = (isValidLocale(lang)) ? lang : "ES";
		return executeMethod(obj, nombreMetodoMultiLang + lang);
	}

	/**
	 * Ejecuta el método del objeto parametrizado, el nombre del método va en el string
	 * @param obj
	 * @param nombreMetodoMultiLang
	 * @return
	 */
	private static Object executeMethod(Object obj, String nombreMetodoMultiLang) {
		Object objectRetuned = null;
		// Invoco el String una vez validado el idioma
		try {
			Method metodo = obj.getClass().getMethod(nombreMetodoMultiLang);
			objectRetuned = metodo.invoke(obj);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return objectRetuned;
	}

	/**
	 * Comprueba si el locale es un idioma válido
	 * @param locale
	 * @return true si es valido
	 */
	private static boolean isValidLocale(String locale) {
		boolean isValid = false;
	
		switch (locale) {
		case "ES":
		case "EN":
			isValid = true;
		}

		return isValid;
	}
}
