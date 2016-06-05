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



$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(decodeURIComponent(window.location.href));
    return (results != null) ? results[1].replace("enie", "ñ") : null;
}
//Activa el botón
$(function () {
    if (localStorage.getItem("username") != null) {
        $("#username").val(localStorage.getItem("username")).css("background-color", "#FFFCE6");
        $("#password").val(localStorage.getItem("password")).css("background-color", "#FFFCE6");
        $("#remember-credentials-checkbox").attr("checked", "checked");
        $("#submit-login").removeAttr("disabled");

    }
    var error_message = $.urlParam("error_message");
    if (error_message != null) {
        showLoginError(error_message);
    }
    $("#login-form input").on("input", function () {
        if ($("#username").val() != "" && $("#password").val() != "") {
            $("#submit-login").removeAttr("disabled");
        } else {
            $("#submit-login").attr("disabled", "disabled");
        }
    });
    //Validación en el sumbit
    $("#login-form").submit(function (event) {

        if ($("#username").val() == "" || $("#password").val() == "") {
            showLoginError("El usuario y la contraseña no pueden estar en blanco");
            return false;
        }
        if ($("#username").val().match(/["']/) || $("#password").val().match(/["']/)) {
            showLoginError("No pueden introducirse comillas");
            return false;
        }
        if ($("#remember-credentials-checkbox").prop("checked")) {
            localStorage.setItem("username", $("#username").val());
            localStorage.setItem("password", $("#password").val());
        } else {
            if (localStorage.getItem("username") != null) {
                localStorage.removeItem("username");
                localStorage.removeItem("password");
            }
        }
        return true;
    });
});
//Muestra el mensaje de error.
function showLoginError(error_message) {
    $("#error-message").html(error_message);
    $("#error-message").slideDown(300);
}