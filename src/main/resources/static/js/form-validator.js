var errormsg = [];
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
                        message: obtainProperLanguage('username', 'data-notempty')
                    },
                    regexp: {
                        regexp: /^[a-z0-9]{4,24}$/i,
                        message: obtainProperLanguage('username', 'data-regexpusername')
                    },
                    remote: {
                        url: '/api/user/username',
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
                        url: '/api/user/summonername',
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
                        message: 'El campo Email es obligatorio'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'El campo Email debe tener entre 3 y 30 carácteres'
                    },
                    regexp: {
                        regexp: /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/,
                        message: 'El campo Email debe cumplir con el siguiente formato: micorreo@dominio.com'
                    }
                }
            }, password: {
                validators: {
                    notEmpty: {
                        message: obtainProperLanguage('password','data-notempty')
                    },
                    identical: {
                        field: 'inputPasswordConfirm',
                        message: 'Los campos de pw no coinciden'
                    },
                    regexp: {
                        regexp: /^(6|9)[0-9]{8}$/,
                        message: 'El campo telefono debe cumplir con el siguiente formato: 918548789 o 618548789 y sólo puede contener caracteres numéricos'
                    }
                }
            }
        }
    });
});

function obtainProperLanguage(field, attribute) {
    return $('#' + field).attr(attribute);
}