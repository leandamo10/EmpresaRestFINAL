package com.empresaRESTmongo.exceptions;

public class EmpleadoAlreadyExistException extends Exception {
    public EmpleadoAlreadyExistException(String message){
        super(message);
    }
}
