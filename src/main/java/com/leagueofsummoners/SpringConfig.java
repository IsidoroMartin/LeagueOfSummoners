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
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.Locale;

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
