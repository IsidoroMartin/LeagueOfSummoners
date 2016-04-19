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

                            var result = zxcvbn(password),
                                score = result.score,
                                message = result.feedback.warning || 'The password is weak';

                            // Update the progress bar width and add alert class
                            var $bar = $('#strengthBar');
                            switch (score) {
                                case 0:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '1%');
                                    break;
                                case 1:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '25%');
                                    break;
                                case 2:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '50%');
                                    break;
                                case 3:
                                    $bar.attr('class', 'progress-bar progress-bar-warning')
                                        .css('width', '75%');
                                    break;
                                case 4:
                                    $bar.attr('class', 'progress-bar progress-bar-success')
                                        .css('width', '100%');
                                    break;
                            }

                            // We will treat the password as an invalid one if the score is less than 3
                            if (score < 3) {
                                return {
                                    valid: false,
                                    message: message
                                }
                            }
                            return true;
                        }
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