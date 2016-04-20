package com.leagueofsummoners.model.utils;

/**
 * Created by juanj on 15/04/2016.
 */
public class ValidationRegEXP {
    public static final String VALIDATE_NAME = "^[a-zA-ZñáéíóúÑÁÉÍÓÚ][a-zA-Z ñáéíóúÑÁÉÍÓÚ]{0,30}$";

    public static final String COMPLEX_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";
}
