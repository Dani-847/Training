package org.drk.reto4_ad_turismo.controllers;

import org.drk.reto4_ad_turismo.entities.Hotel;
import org.drk.reto4_ad_turismo.services.HotelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // ==================== LECTURA (PÚBLICOS) ====================

    /**
     * GET /api/v1/hotels
     * Lista todos los hoteles (solo id y nombre)
     */
    @GetMapping
    public List<Map<String, Object>> findAll() {
        return hotelService.findAllSimple();
    }

    /**
     * GET /api/v1/hotels/{id}
     * Obtiene un hotel completo por ID
     */
    @GetMapping("/{id}")
    public Hotel findById(@PathVariable String id) {
        return hotelService.findById(id);
    }

    /**
     * GET /api/v1/hotels/code/{hotelCode}
     * Obtiene un hotel por código
     */
    @GetMapping("/code/{hotelCode}")
    public Hotel findByCode(@PathVariable Integer hotelCode) {
        return hotelService.findByHotelCode(hotelCode);
    }

    /**
     * GET /api/v1/hotels/search/{name}
     * Busca hoteles por nombre
     */
    @GetMapping("/search/{name}")
    public List<Map<String, Object>> searchByName(@PathVariable String name) {
        return hotelService.searchByNameSimple(name);
    }

    /**
     * GET /api/v1/hotels/rating
     * Lista hoteles ordenados por rating (nombre y rating)
     */
    @GetMapping("/rating")
    public List<Map<String, Object>> findAllByRating() {
        return hotelService.findAllOrderByRating();
    }

    /**
     * GET /api/v1/hotels/rating/{rating}
     * Lista hoteles con un rating específico (FiveStar, FourStar, etc.)
     */
    @GetMapping("/rating/{rating}")
    public List<Map<String, Object>> findByRating(@PathVariable String rating) {
        return hotelService.findByRatingSimple(rating);
    }

    /**
     * GET /api/v1/hotels/country/{countryCode}
     * Lista hoteles por país (nombre y ciudad)
     */
    @GetMapping("/country/{countryCode}")
    public List<Map<String, Object>> findByCountry(@PathVariable String countryCode) {
        return hotelService.findByCountrySimple(countryCode);
    }

    /**
     * GET /api/v1/hotels/city/{cityName}
     * Lista hoteles por ciudad
     */
    @GetMapping("/city/{cityName}")
    public List<Map<String, Object>> findByCity(@PathVariable String cityName) {
        return hotelService.findByCitySimple(cityName);
    }

    /**
     * GET /api/v1/hotels/attractions
     * Lista hoteles con atracciones (nombre y atracciones)
     */
    @GetMapping("/attractions")
    public List<Map<String, Object>> findWithAttractions() {
        return hotelService.findHotelsWithAttractionsSimple();
    }

    /**
     * GET /api/v1/hotels/facilities/{facility}
     * Lista hoteles con una facilidad específica (wifi, pool, spa, etc.)
     */
    @GetMapping("/facilities/{facility}")
    public List<Map<String, Object>> findByFacility(@PathVariable String facility) {
        return hotelService.findByFacilitySimple(facility);
    }

    /**
     * GET /api/v1/hotels/stats
     * Estadísticas generales
     */
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return hotelService.getStats();
    }

    // ==================== ESCRITURA (REQUIEREN AUTH) ====================

    /**
     * POST /api/v1/hotels
     * Crea un nuevo hotel
     */
    @PostMapping
    public ResponseEntity<Hotel> save(@RequestBody Hotel hotel) {
        Hotel saved = hotelService.create(hotel);
        if (saved != null) {
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/v1/hotels/{id}
     * Actualiza un hotel
     */
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable String id, @RequestBody Hotel hotel) {
        Hotel updated = hotelService.update(id, hotel);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DELETE /api/v1/hotels/{id}
     * Elimina un hotel
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

