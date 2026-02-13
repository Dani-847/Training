package org.drk.reto4_ad_turismo.services;

import org.drk.reto4_ad_turismo.entities.Hotel;
import org.drk.reto4_ad_turismo.exceptions.DuplicateHotelException;
import org.drk.reto4_ad_turismo.exceptions.HotelNotFoundException;
import org.drk.reto4_ad_turismo.repositories.HotelRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private static final int LIMIT = 100;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // ==================== LECTURA ====================

    /**
     * Lista todos los hoteles (solo id y nombre) - limitado a 100
     */
    public List<Map<String, Object>> findAllSimple() {
        return hotelRepository.findAll(PageRequest.of(0, LIMIT))
                .stream()
                .map(h -> Map.<String, Object>of(
                        "id", h.get_id(),
                        "hotelName", h.getHotelName() != null ? h.getHotelName() : ""
                ))
                .toList();
    }

    /**
     * Busca un hotel por ID
     */
    public Hotel findById(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel no encontrado con ID: " + id));
    }

    /**
     * Busca un hotel por código
     */
    public Hotel findByHotelCode(Integer hotelCode) {
        return hotelRepository.findByHotelCode(hotelCode)
                .orElseThrow(() -> new HotelNotFoundException("Hotel no encontrado con código: " + hotelCode));
    }

    /**
     * Busca hoteles por nombre (solo id y nombre)
     */
    public List<Map<String, Object>> searchByNameSimple(String name) {
        return hotelRepository.findByHotelNameContainingIgnoreCase(name)
                .stream()
                .limit(LIMIT)
                .map(h -> Map.<String, Object>of(
                        "id", h.get_id(),
                        "hotelName", h.getHotelName() != null ? h.getHotelName() : ""
                ))
                .toList();
    }

    /**
     * Lista hoteles ordenados por rating (nombre y rating)
     */
    public List<Map<String, Object>> findAllOrderByRating() {
        return hotelRepository.findAll(PageRequest.of(0, LIMIT, Sort.by("HotelRating").descending()))
                .stream()
                .map(h -> Map.<String, Object>of(
                        "hotelName", h.getHotelName() != null ? h.getHotelName() : "",
                        "hotelRating", h.getHotelRating() != null ? h.getHotelRating() : ""
                ))
                .toList();
    }

    /**
     * Lista hoteles por rating específico (nombre y rating)
     */
    public List<Map<String, Object>> findByRatingSimple(String rating) {
        return hotelRepository.findByHotelRatingIgnoreCase(rating, PageRequest.of(0, LIMIT))
                .stream()
                .map(h -> Map.<String, Object>of(
                        "id", h.get_id(),
                        "hotelName", h.getHotelName() != null ? h.getHotelName() : "",
                        "hotelRating", h.getHotelRating() != null ? h.getHotelRating() : ""
                ))
                .toList();
    }

    /**
     * Lista hoteles por país (nombre y ciudad)
     */
    public List<Map<String, Object>> findByCountrySimple(String countryCode) {
        return hotelRepository.findByCountryCodeIgnoreCase(countryCode, PageRequest.of(0, LIMIT))
                .stream()
                .map(h -> Map.<String, Object>of(
                        "id", h.get_id(),
                        "hotelName", h.getHotelName() != null ? h.getHotelName() : "",
                        "cityName", h.getCityName() != null ? h.getCityName() : ""
                ))
                .toList();
    }

    /**
     * Lista hoteles por ciudad
     */
    public List<Map<String, Object>> findByCitySimple(String cityName) {
        return hotelRepository.findByCityNameIgnoreCase(cityName, PageRequest.of(0, LIMIT))
                .stream()
                .map(h -> Map.<String, Object>of(
                        "id", h.get_id(),
                        "hotelName", h.getHotelName() != null ? h.getHotelName() : "",
                        "address", h.getAddress() != null ? h.getAddress() : ""
                ))
                .toList();
    }

    /**
     * Lista hoteles con atracciones (nombre y atracciones)
     */
    public List<Map<String, Object>> findHotelsWithAttractionsSimple() {
        return hotelRepository.findHotelsWithAttractions(PageRequest.of(0, LIMIT))
                .stream()
                .map(h -> Map.<String, Object>of(
                        "id", h.get_id(),
                        "hotelName", h.getHotelName() != null ? h.getHotelName() : "",
                        "attractions", h.getAttractions() != null ? h.getAttractions() : ""
                ))
                .toList();
    }

    /**
     * Lista hoteles por facilidad (nombre y facilidades)
     */
    public List<Map<String, Object>> findByFacilitySimple(String facility) {
        return hotelRepository.findByHotelFacilitiesContaining(facility, PageRequest.of(0, LIMIT))
                .stream()
                .map(h -> Map.<String, Object>of(
                        "id", h.get_id(),
                        "hotelName", h.getHotelName() != null ? h.getHotelName() : "",
                        "hotelFacilities", h.getHotelFacilities() != null ? h.getHotelFacilities() : ""
                ))
                .toList();
    }

    /**
     * Estadísticas generales
     */
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalHotels", hotelRepository.count());
        stats.put("fiveStarHotels", hotelRepository.countByHotelRatingIgnoreCase("FiveStar"));
        stats.put("fourStarHotels", hotelRepository.countByHotelRatingIgnoreCase("FourStar"));
        stats.put("threeStarHotels", hotelRepository.countByHotelRatingIgnoreCase("ThreeStar"));
        return stats;
    }

    // ==================== ESCRITURA ====================

    /**
     * Crea un nuevo hotel
     */
    public Hotel create(Hotel hotel) {
        if (hotel.getHotelCode() != null && hotelRepository.existsByHotelCode(hotel.getHotelCode())) {
            throw new DuplicateHotelException("Ya existe un hotel con el código: " + hotel.getHotelCode());
        }
        return hotelRepository.save(hotel);
    }

    /**
     * Actualiza un hotel
     */
    public Hotel update(String id, Hotel hotelDetails) {
        Hotel existingHotel = findById(id);

        if (hotelDetails.getHotelName() != null) existingHotel.setHotelName(hotelDetails.getHotelName());
        if (hotelDetails.getHotelCode() != null) existingHotel.setHotelCode(hotelDetails.getHotelCode());
        if (hotelDetails.getCountryCode() != null) existingHotel.setCountryCode(hotelDetails.getCountryCode());
        if (hotelDetails.getCountryName() != null) existingHotel.setCountryName(hotelDetails.getCountryName());
        if (hotelDetails.getCityCode() != null) existingHotel.setCityCode(hotelDetails.getCityCode());
        if (hotelDetails.getCityName() != null) existingHotel.setCityName(hotelDetails.getCityName());
        if (hotelDetails.getHotelRating() != null) existingHotel.setHotelRating(hotelDetails.getHotelRating());
        if (hotelDetails.getAddress() != null) existingHotel.setAddress(hotelDetails.getAddress());
        if (hotelDetails.getAttractions() != null) existingHotel.setAttractions(hotelDetails.getAttractions());
        if (hotelDetails.getDescription() != null) existingHotel.setDescription(hotelDetails.getDescription());
        if (hotelDetails.getHotelFacilities() != null) existingHotel.setHotelFacilities(hotelDetails.getHotelFacilities());
        if (hotelDetails.getHotelWebsiteUrl() != null) existingHotel.setHotelWebsiteUrl(hotelDetails.getHotelWebsiteUrl());

        return hotelRepository.save(existingHotel);
    }

    /**
     * Elimina un hotel por ID
     */
    public void delete(String id) {
        if (!hotelRepository.existsById(id)) {
            throw new HotelNotFoundException("Hotel no encontrado con ID: " + id);
        }
        hotelRepository.deleteById(id);
    }
}

