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
                        message: 'El campo nombre es obligatorio'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'El campo nombre debe tener entre 3 y 30 carácteres'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'El campo Nombre sólo puede contener carácteres alfabéticos'
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
                        message: obtainProperLanguage('summonerName','data-notsummavailable'),
                        type: 'POST',
                    }
                }
            }, segundo_apellido: {
                validators: {
                    notEmpty: {
                        message: 'El campo Segundo Apellido es obligatorio'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'El campo Segundo Apellido debe tener entre 3 y 30 carácteres'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'El campo Segundo Apellido sólo puede contener carácteres alfabéticos'
                    }
                }
            }, dni: {
                validators: {
                    notEmpty: {
                        message: 'El campo DNI es obligatorio'
                    },
                    stringLength: {
                        min: 9,
                        max: 9,
                        message: 'El campo DNI debe tener 9 carácteres'
                    },
                    regexp: {
                        regexp: /^[0-9]{8}[a-zA-Z]{1}$/,
                        message: 'El campo DNI debe cumplir con el siguiente formato 000000000J'
                    }
                }
            }, email: {
                onError: function (e, data) {
                    alert("Hi");
                },
                onSuccess: function (e, data) {
                    // Do something ...
                },
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
            }, telefono: {
                validators: {
                    notEmpty: {
                        message: 'El campo Telefono es obligatorio'
                    },
                    stringLength: {
                        min: 9,
                        max: 9,
                        message: 'El campo telefono debe contener 9 carácteres numéricos'
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