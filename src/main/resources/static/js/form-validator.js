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
                        message: obtainProperLanguage('username', 'data-characterslength')
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
                        message: obtainProperLanguage('inputEmail', 'data-notempty')
                    },
                    regexp: {
                        regexp: /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/,
                        message: obtainProperLanguage('inputEmail', 'data-emailerror')
                    },
                    remote: {
                        url: '/api/user/email',
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

function obtainProperLanguage(field, attribute) {
    return $('#' + field).attr(attribute);
}