package org.drk.di_examen_18_03_26.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO que representa una respuesta de error de la API.
 * Se utiliza para devolver información consistente sobre los errores al cliente.
 */
@AllArgsConstructor
@Getter
public class ErrorResponseDTO {

    /** Tipo de error. */
    private String error;

    /** Mensaje descriptivo del error. */
    private String message;

    /** Código de estado HTTP del error. */
    private Integer errorCode;
}