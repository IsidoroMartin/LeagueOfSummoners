package com.leagueofsummoners.model.services;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Esta es una clase que sobrescribe el listener de cambiar de idioma para añadir a una cookie el idioma de la 
 * app
 * @author Juanjors
 *
 */
@Component
public class LocaleChangeInterceptorAddSession extends LocaleChangeInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {
		Cookie cookieLanguage = new Cookie("locale", LocaleContextHolder.getLocale().getLanguage());
		cookieLanguage.setPath("/");
		response.addCookie(cookieLanguage);
		return super.preHandle(request, response, handler);
	}
}
