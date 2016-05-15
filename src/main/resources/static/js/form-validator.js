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
                    }, callback: {
                        callback: function (value, validator, $field) {
                            var password = $('#password').val();
                            if (password == '') {
                                return true;
                            }

                            var validlength = password.length >= 8;
                            var hasUpperCase = /[A-Z]/.test(password);
                            var hasLowerCase = /[a-z]/.test(password);
                            var hasNumbers = /\d/.test(password);
                            var hasNonalphas = /\W/.test(password);
                            if (hasUpperCase && hasLowerCase && hasNumbers && hasNonalphas && validlength)
                                return true;

                            return false;
                        },
                        message: obtainProperLanguage('password', 'data-pwnocomplex')
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
    var regex = new RegExp("^" + inputValue, "i");
    var modal = $('#modal-wrapper');
    var html = "";
    campeonesImg.each(function (index, value) {
        var champName = value.title;
        if (champName.search(regex) != -1) {
            html += '<div style="float:left;">' + value.outerHTML + '</div>';
        }
    });
    if (html == "")
        html = "<p>" + obtainProperLanguage("show-champions","data-no-champions") + "</p>";
        $('#show-champions').hide().html(html).fadeIn(500);
}