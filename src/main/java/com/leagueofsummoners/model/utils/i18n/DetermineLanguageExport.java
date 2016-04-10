package com.leagueofsummoners.model.utils.i18n;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DetermineLanguageExport {

	/**
	 * Recibe el objeto del que se quiere invocar el método, el locale (el idioma en el que se quiere obtener), 
	 * y el nombre del método (Si en el idioma de las dos últimas letras)
	 * @param obj
	 * @param locale
	 * @param nombreMetodoMultiLang
	 * @return La ejecución del método
	 */
	public static Object getProperLanguage(Object obj, String locale, String nombreMetodoMultiLang) {
		locale = (locale == null) ? "ES" : locale.toUpperCase();
		locale = (isValidLocale(locale)) ? locale : "ES";
		return executeMethod(obj, nombreMetodoMultiLang + locale);
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
			Method metodo = obj.getClass().getMethod(nombreMetodoMultiLang, new Class[] {});
			objectRetuned = metodo.invoke(obj, new Object[] {});
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
