package com.ralphdoe.torni.exception;

/**
 * Excepción que se lanza cuando no se encuentra un recurso.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
