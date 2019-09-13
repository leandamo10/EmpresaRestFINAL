package com.empresaRESTmongo.exceptions;

public class DniNotFoundException extends Exception{
    public DniNotFoundException(String message){
        super(message);
    }
}
