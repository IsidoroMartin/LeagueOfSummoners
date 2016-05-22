$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(decodeURIComponent(window.location.href));
    return (results != null) ? results[1].replace("enie", "ñ") : null;
}
$(function () {
    if (localStorage.getItem("username") != null) {
        $("#username").val(localStorage.getItem("username")).css("background-color", "#FFFCE6");
        $("#password").val(localStorage.getItem("password")).css("background-color", "#FFFCE6");
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
function showLoginError(error_message) {
    $("#error-message").html(error_message);
    $("#error-message").slideDown(300);
}