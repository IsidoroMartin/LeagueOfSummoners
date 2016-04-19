package com.leagueofsummoners.model.dto;

/**
 * Created by juanj on 18/04/2016.
 */
public class GenericJsonValidator {
    private boolean valid;

    public GenericJsonValidator(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
