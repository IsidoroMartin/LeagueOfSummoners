package com.leagueofsummoners;

import com.leagueofsummoners.interceptors.AuthenticationAdminInterceptor;
import com.leagueofsummoners.interceptors.AuthenticationInterceptor;
import com.leagueofsummoners.model.services.LocaleService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

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
 * En esta clase se configura el idioma de la aplicación.
 * (Preparado para el multidioma)
 */
@Configuration
@EnableAsync
public class SpringConfig extends WebMvcConfigurerAdapter {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}


	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("ES"));
		return slr;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleService interceptor = new LocaleService();
		interceptor.setParamName("locale");
		registry.addInterceptor(interceptor);
		registry.addInterceptor(new AuthenticationInterceptor());
		registry.addInterceptor(new AuthenticationAdminInterceptor());
	}
}
