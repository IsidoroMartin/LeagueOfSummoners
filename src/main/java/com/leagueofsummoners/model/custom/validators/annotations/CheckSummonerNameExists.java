package com.leagueofsummoners.model.custom.validators.annotations;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


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
 * Comprueba desde el lado del servidor si elnombre de invocador existe.
 *
 */
public class CheckSummonerNameExists implements ConstraintValidator<ValidateSummonerNameExists, String> {

    @Override
    public void initialize(ValidateSummonerNameExists constraintAnnotation) {

    }
    
    /**
     * Devuelve si el nombre de invocador existe (Es válido).
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        if (value == null) {
            return false;
        }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        return session.getAttribute("summonerExists") != null && (boolean) session.getAttribute("summonerExists");

    }
}