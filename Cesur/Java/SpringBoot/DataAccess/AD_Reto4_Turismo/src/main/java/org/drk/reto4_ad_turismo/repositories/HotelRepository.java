package org.drk.reto4_ad_turismo.repositories;

import org.drk.reto4_ad_turismo.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {

    // Buscar por código de hotel (único)
    Optional<Hotel> findByHotelCode(Integer hotelCode);

    // Buscar por nombre de hotel (parcial, ignorando mayúsculas)
    List<Hotel> findByHotelNameContainingIgnoreCase(String hotelName);

    // Buscar por país
    Page<Hotel> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);

    List<Hotel> findByCountryNameIgnoreCase(String countryName);

    // Buscar por ciudad
    Page<Hotel> findByCityNameIgnoreCase(String cityName, Pageable pageable);

    List<Hotel> findByCityCode(Integer cityCode);

    // Buscar por rating
    Page<Hotel> findByHotelRatingIgnoreCase(String rating, Pageable pageable);

    // Buscar hoteles con facilidades específicas (contiene texto)
    @Query("{ 'HotelFacilities': { $regex: ?0, $options: 'i' } }")
    Page<Hotel> findByHotelFacilitiesContaining(String facility, Pageable pageable);

    // Buscar por país y rating
    Page<Hotel> findByCountryCodeIgnoreCaseAndHotelRatingIgnoreCase(
            String countryCode, String rating, Pageable pageable);

    // Buscar por ciudad y rating
    Page<Hotel> findByCityNameIgnoreCaseAndHotelRatingIgnoreCase(
            String cityName, String rating, Pageable pageable);

    // Contar hoteles por país
    long countByCountryCodeIgnoreCase(String countryCode);

    // Contar hoteles por ciudad
    long countByCityNameIgnoreCase(String cityName);

    // Contar hoteles por rating
    long countByHotelRatingIgnoreCase(String rating);

    // Buscar hoteles con atracciones
    @Query("{ 'Attractions': { $ne: '', $exists: true } }")
    Page<Hotel> findHotelsWithAttractions(Pageable pageable);

    // Verificar si existe por código de hotel
    boolean existsByHotelCode(Integer hotelCode);
}

