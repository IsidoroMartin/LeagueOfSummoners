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

Este JS valida los campos del login. 
*/


//Activa el botón
$(function () {
	 $('#contact-form').bootstrapValidator({
	        framework: 'bootstrap',
	        icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        message: 'Este valor no es válido',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        }, fields: {
	        	nombre: {
	                validators: {
	                    notEmpty: {
	                        message: "El nombre no puede estar vacio"
	                    },
	                    stringLength: {
	                        min: 4,
	                        max: 24,
	                        message: "El nombre tiene que contener al menos 4 caracteres"
	                    },
	                    regexp: {
	                        regexp: /^[a-z]{4,24}$/i,
	                        message: "El nombre sólo puede contener letras"
	                    }
	                }
	            }, email: {
	                validators: {
	                    notEmpty: {
	                        message: "El campo e-mail no puede estar vacio"
	                    },
	                    regexp: {
	                        regexp: /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/i,
	                        message: "El campo e-mail tiene que ser un email válido, (usuario@dominio.com)"
	                    }
	                }
	            }, content: {
	                validators: {
	                    notEmpty: {
	                        message: "El campo de contacto no puede estar vacio"
	                    },
	                   stringLength: {
	                        min: 15,
	                        max: 300,
	                        message: "El campo de contacto debe contener entre 15 y 300 ccaracteres"
	                    }
	                }
	            }
	        }
	    });
	});

// Muestra el mensaje de success.
$(function showSuccess() {
	var successMessage = $.urlParam("success");
	if(successMessage != null){
	var successElement = $("#success-message");
	successElement.html(successMessage);
	successElement.slideDown(300);
	}
});

$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(decodeURIComponent(window.location.href));
    return (results != null) ? results[1] : null;
}