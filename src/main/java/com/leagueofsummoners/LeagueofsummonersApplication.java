package com.leagueofsummoners;

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
 * Esta es la clase que inicia la aplicación.
 * Ya sea usando el tomcat embebido en Spring boot o desde 
 * un servidor tomcat externo
 *
 */
@SpringBootApplication
public class LeagueofsummonersApplication extends SpringBootServletInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(LeagueofsummonersApplication.class);


    /**
     * Este main se instancia cuando el servidor se inicia modo Spring boot.
     * @param args
     */
    public static void main(String[] args) {
		SpringApplication.run(LeagueofsummonersApplication.class, args);
		LeagueAccessAPI.initRIOTAPI();
        LOGGER.info("League Of Summoners app started...");
    }

    /**
     * Confiración página deerror
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
            }
        };
    }

    /**
     * Este es el método encargado de iniciar la aplicación cuando se inicia desde un servidor
     * tomcat externo.
     */
     @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        LOGGER.info("Tomcat empieza por aqui");
        LeagueAccessAPI.initRIOTAPI();
        return application.sources(this.getClass());
    }




}