package com.leagueofsummoners.model.custom.validators.annotations;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckSummonerNameExists implements ConstraintValidator<ValidateSummonerNameExists, String> {

    @Override
    public void initialize(ValidateSummonerNameExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        if (value == null) {
            return false;
        }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        if(session.getAttribute("summonerExists") != null)
            return true;

        return false;
    }
}