package org.drk.reto4_ad_turismo.dto;

public record ErrorResponseDTO(
    String error,
    String message,
    Integer errorCode
) {}
