package org.drk.ad_examen_18_03_26.exceptions;

/**
 * Excepción lanzada cuando la petición del cliente es inválida o incompleta.
 */
public class InvalidRequestException extends RuntimeException {

    /**
     * Constructor que recibe el mensaje de error.
     *
     * @param message mensaje descriptivo del error
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}

