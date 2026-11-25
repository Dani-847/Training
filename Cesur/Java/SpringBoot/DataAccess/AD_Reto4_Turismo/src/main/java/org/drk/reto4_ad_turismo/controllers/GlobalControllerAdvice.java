package org.drk.reto4_ad_turismo.controllers;

import org.drk.reto4_ad_turismo.dto.ErrorResponseDTO;
import org.drk.reto4_ad_turismo.exceptions.DuplicateHotelException;
import org.drk.reto4_ad_turismo.exceptions.HotelNotFoundException;
import org.drk.reto4_ad_turismo.exceptions.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    // ==================== HOTEL EXCEPTIONS ====================

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleHotelNotFound(HotelNotFoundException ex) {
        ErrorResponseDTO err = new ErrorResponseDTO(
                "Hotel not found",
                ex.getMessage(),
                404
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(err);
    }

    @ExceptionHandler(DuplicateHotelException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateHotel(DuplicateHotelException ex) {
        ErrorResponseDTO err = new ErrorResponseDTO(
                "Duplicate hotel",
                ex.getMessage(),
                409
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(err);
    }

    // ==================== GENERAL EXCEPTIONS ====================

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidRequest(InvalidRequestException ex) {
        ErrorResponseDTO err = new ErrorResponseDTO(
                "Invalid request",
                ex.getMessage(),
                400
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format(
                "El parámetro '%s' debe ser de tipo '%s'",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido"
        );
        ErrorResponseDTO err = new ErrorResponseDTO(
                "Invalid parameter type",
                message,
                400
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        ErrorResponseDTO err = new ErrorResponseDTO(
                "Invalid request body",
                "El cuerpo de la petición no es válido o está mal formateado",
                400
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(err);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<String> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        // Devolver JSON directamente como String para evitar problemas de serialización
        String json = """
                {"error":"Not Acceptable","message":"El recurso solicitado no está disponible en el formato pedido","errorCode":406}
                """;
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO err = new ErrorResponseDTO(
                "Internal server error",
                "Ha ocurrido un error interno en el servidor",
                500
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(err);
    }
}