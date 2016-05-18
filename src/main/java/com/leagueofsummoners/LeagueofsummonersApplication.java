package com.leagueofsummoners;

import com.leagueofsummoners.interceptors.InterceptorSession;
import com.leagueofsummoners.model.utils.LeagueAccessAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpSessionListener;

@SpringBootApplication
public class LeagueofsummonersApplication extends SpringBootServletInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(LeagueofsummonersApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(LeagueofsummonersApplication.class, args);
        LeagueAccessAPI.initRIOTAPI();
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
    public HttpSessionListener httpSessionListener() {
        return new InterceptorSession();
    }

     @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        LOGGER.info("Tomcat empieza por aqui");
        LeagueAccessAPI.initRIOTAPI();
        return application.sources(this.getClass());
    }




}