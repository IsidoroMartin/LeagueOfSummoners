package com.leagueofsummoners;

import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.leagueofsummoners.interceptors.InterceptorSession;

@SpringBootApplication
public class LeagueofsummonersApplication {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(LeagueofsummonersApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(LeagueofsummonersApplication.class, args);
		LOGGER.info("League Of Summoners app started...");
		
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
			}
		};
	}
	
	@Bean
	public HttpSessionListener httpSessionListener(){
	    return new InterceptorSession();
	}

}