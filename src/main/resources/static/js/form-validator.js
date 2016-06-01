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

Este JS valida los campos del registro. y ademas hace que aparezca lagalería en el registro.
*/


//Obtengo una lista de todos los campeones para no tener que obtenerlos cada vez que se hace un búsqueda
var campeonesImg = $('.champion-gallery');

/**
 * Esta fucnión utiliza bootstrap validator par controlar los errores del formulario, además de eso
 * hace uso de la función obtainProperLanguage, para obtener el mensaje de error en el idioma en uso por el usuario
 */
$(function (e) {
    $('#form-register').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        }, fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: obtainProperLanguage('username', 'data-notempty')
                    },
                    stringLength: {
                        min: 4,
                        max: 24,
                        message: obtainProperLanguage('username', 'data-characterslength')
                    },
                    regexp: {
                        regexp: /^[a-z0-9]{4,24}$/i,
                        message: obtainProperLanguage('username', 'data-regexpusername')
                    },
                    remote: {
                        url: './api/user/username',
                        data: function (validator) {
                            return {
                                username: validator.getFieldElements('username').val()
                            }
                        },
                        message: obtainProperLanguage('username', 'data-usernamexists'),
                        type: 'POST'
                    }
                }
            }, summonerName: {
                validators: {
                    notEmpty: {
                        message: obtainProperLanguage('summonerName', 'data-notempty')
                    },
                    stringLength: {
                        min: 4,
                        max: 24,
                        message: obtainProperLanguage('summonerName', 'data-characterslength')
                    },
                    remote: {
                        url: './api/user/summonername',
                        data: function (validator) {
                            return {
                                summonerName: validator.getFieldElements('summonerName').val()
                            }
                        },
                        message: obtainProperLanguage('summonerName', 'data-notsummavailable'),
                        type: 'POST',
                    }
                }
            }, email: {
                validators: {
                    notEmpty: {
                        message: obtainProperLanguage('inputEmail', 'data-notempty')
                    },
                    regexp: {
                        regexp: /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/i,
                        message: obtainProperLanguage('inputEmail', 'data-emailerror')
                    },
                    remote: {
                        url: './api/user/email',
                        data: function (validator) {
                            return {
                                email: validator.getFieldElements('email').val()
                            }
                        },
                        message: obtainProperLanguage('inputEmail', 'data-usedemail'),
                        type: 'POST',
                    }
                }
            }, password: {
                validators: {
                    notEmpty: {
                        message: obtainProperLanguage('password', 'data-notempty')
                    },
                    identical: {
                        field: 'inputPasswordConfirm',
                        message: obtainProperLanguage('password', 'data-nomatchpassword')
                    },stringLength: {
                        min: 8,
                        max: 15,
                        message: obtainProperLanguage('password', 'data-pwnolength')
                    }
                }
            },
            inputPasswordConfirm: {
                validators: {
                    identical: {
                        field: 'password',
                        message: obtainProperLanguage('password', 'data-nomatchpassword')
                    }
                }
            }
        }
    });
});

/**
 * Esta función obtiene el mensaje del campo 'data-algo' en el idioma correspondiente
 * (Traducido previamente por el server)
 * @param field
 * @param attribute
 * @returns {*|jQuery} El mensaje traducido
 */
function obtainProperLanguage(field, attribute) {
    return $('#' + field).attr(attribute);
}


/**
 * Esta función cierra la ventana cuando seleccionas un campeón, ademas de poner el nombre en
 * la caja de texto para saber cual has seleccionado, también pone la ruta de el campeón elegido en el
 * input hidden
 */
function closeModal(champPicked) {
    var championPicked = $(champPicked);
    $('.fileinput-filename').html(championPicked.attr("title"));
    $('.glyphicon-picture').css({opacity: 1});
    $('#img-galeria').val(championPicked.attr("src"));
    $('#champions_modal').modal('toggle');
}

/**
 * cuando seleccionas una imagen pone la imagen de la galería a vacio
 */
$('#fileInput').click(function () {
    $('#img-galeria').val("");
});

/**
 * Todos los botones que tengan esta clase eliminarán la ruta del input type hidden
 * para que no se envíe al servidor
 */
$(".dissmissvalue").click(function () {
    $('.img-galeria').val("");
});

$(document).keyup(function (e) {
    if (e.keyCode == 27) {
        $('#champions_modal').modal('hide');
    }
});

function filtrarCampeones(inputValue) {
    // Me creo una expresión regular con lo que ha introducido el usuario
    var regex = new RegExp(inputValue, "i");
    var html = "";
    campeonesImg.each(function (index, value) {
        var champName = value.title;
        if (champName.search(regex) != -1) {
            html += '<div style="float:left;">' + value.outerHTML + '</div>';
        }
    });
    if (html == "")
    	html = "<div class='alert alert-warning fade in'>No existen campeones con estas caracteristicas</div>";
        $('#show-champions').hide().html(html).fadeIn(500);
}