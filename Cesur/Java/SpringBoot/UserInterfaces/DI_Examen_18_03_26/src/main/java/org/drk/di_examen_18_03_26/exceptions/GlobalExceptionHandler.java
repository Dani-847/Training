package org.drk.di_examen_18_03_26.exceptions;

import org.drk.di_examen_18_03_26.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para la API REST.
 * Captura las excepciones lanzadas por los controladores y devuelve respuestas HTTP adecuadas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción ItemNotFoundException devolviendo un 404.
     *
     * @param ex excepción capturada
     * @return ResponseEntity con código 404 y detalles del error
     */
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleItemNotFound(ItemNotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Item no encontrado",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Maneja la excepción InvalidRequestException devolviendo un 400.
     *
     * @param ex excepción capturada
     * @return ResponseEntity con código 400 y detalles del error
     */
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidRequest(InvalidRequestException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Petición inválida",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Maneja cualquier excepción no controlada devolviendo un 500.
     *
     * @param ex excepción capturada
     * @return ResponseEntity con código 500 y detalles del error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneral(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Error interno del servidor",
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

