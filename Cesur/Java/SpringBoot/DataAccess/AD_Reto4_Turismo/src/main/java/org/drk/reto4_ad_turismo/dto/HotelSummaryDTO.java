package org.drk.reto4_ad_turismo.dto;

import org.drk.reto4_ad_turismo.entities.Hotel;

/**
 * DTO para representar un resumen de hotel (sin descripción completa)
 */
public record HotelSummaryDTO(
    String id,
    Integer hotelCode,
    String hotelName,
    String hotelRating,
    String cityName,
    String countryName,
    String address,
    String hotelWebsiteUrl
) {
    public static HotelSummaryDTO fromHotel(Hotel hotel) {
        return new HotelSummaryDTO(
            hotel.get_id(),
            hotel.getHotelCode(),
            hotel.getHotelName(),
            hotel.getHotelRating(),
            hotel.getCityName(),
            hotel.getCountryName(),
            hotel.getAddress(),
            hotel.getHotelWebsiteUrl()
        );
    }
}

