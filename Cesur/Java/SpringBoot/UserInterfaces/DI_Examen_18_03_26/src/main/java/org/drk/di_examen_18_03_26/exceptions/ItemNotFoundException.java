package org.drk.di_examen_18_03_26.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un Item con el identificador proporcionado.
 */
public class ItemNotFoundException extends RuntimeException {

    /**
     * Constructor que recibe el mensaje de error.
     *
     * @param message mensaje descriptivo del error
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}

