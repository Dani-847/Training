package org.drk.reto4_ad_turismo.dto;

public record HotelStatsDTO(
    long totalHotels,
    Long hotelsByCountry,
    Long hotelsByCity,
    String countryCode,
    String cityName
) {}

