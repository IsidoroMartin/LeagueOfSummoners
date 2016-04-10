package com.leagueofsummoners.model.services;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * Esta es una clase que sobrescribe el listener de cambiar de idioma para añadir a una cookie el idioma de la 
 * app
 * @author Juanjors
 *
 */
public class LocaleChangeInterceptorAddSession extends LocaleChangeInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {
		String locale = request.getParameter("locale");
		request.getSession(true).setAttribute("locale", locale);
		response.addCookie(new Cookie("locale", locale));
		return super.preHandle(request, response, handler);
	}
}
