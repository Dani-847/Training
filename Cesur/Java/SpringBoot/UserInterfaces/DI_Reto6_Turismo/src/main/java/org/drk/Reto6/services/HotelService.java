package org.drk.Reto6.services;

import org.drk.Reto6.entities.Hotel;
import org.drk.Reto6.exceptions.HotelNotFoundException;
import org.drk.Reto6.repositories.HotelRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de negocio para la gestion de hoteles.
 *
 * <p>Encapsula la logica de acceso a datos del repositorio {@link HotelRepository}
 * y devuelve objetos {@link Hotel} completos para su uso en las vistas Thymeleaf.
 * Todas las consultas de listado se limitan a {@value #LIMITE} resultados para
 * evitar sobrecargar la memoria.</p>
 */
@Service
public class HotelService {

    /** Numero maximo de resultados por consulta de listado. */
    private static final int LIMITE = 50;

    private final HotelRepository hotelRepository;

    /**
     * Constructor con inyeccion del repositorio.
     *
     * @param hotelRepository repositorio MongoDB de hoteles
     */
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // ==================== LECTURA ====================

    /**
     * Devuelve una lista de hasta {@value #LIMITE} hoteles ordenados por nombre.
     *
     * @return lista de hoteles
     */
    public List<Hotel> listarTodos() {
        return hotelRepository.findAll(
                PageRequest.of(0, LIMITE, Sort.by("HotelName").ascending())
        ).getContent();
    }

    /**
     * Busca un hotel por su identificador MongoDB.
     *
     * @param id identificador del documento
     * @return el hotel encontrado
     * @throws HotelNotFoundException si no existe ningun hotel con ese ID
     */
    public Hotel buscarPorId(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel no encontrado con ID: " + id));
    }

    /**
     * Busca hoteles cuyo nombre contenga el texto indicado (hasta {@value #LIMITE} resultados).
     *
     * @param nombre texto a buscar en el nombre del hotel
     * @return lista de hoteles que coinciden
     */
    public List<Hotel> buscarPorNombre(String nombre) {
        return hotelRepository.findByHotelNameContainingIgnoreCase(nombre)
                .stream()
                .limit(LIMITE)
                .toList();
    }

    /**
     * Busca hoteles por codigo de pais (hasta {@value #LIMITE} resultados).
     *
     * @param codigoPais codigo del pais (p.ej. "ES", "US")
     * @return lista de hoteles del pais indicado
     */
    public List<Hotel> buscarPorPais(String codigoPais) {
        return hotelRepository.findByCountryCodeIgnoreCase(
                codigoPais, PageRequest.of(0, LIMITE)
        ).getContent();
    }

    /**
     * Busca hoteles por nombre de ciudad (hasta {@value #LIMITE} resultados).
     *
     * @param ciudad nombre de la ciudad
     * @return lista de hoteles de la ciudad indicada
     */
    public List<Hotel> buscarPorCiudad(String ciudad) {
        return hotelRepository.findByCityNameIgnoreCase(
                ciudad, PageRequest.of(0, LIMITE)
        ).getContent();
    }

    /**
     * Busca hoteles por categoria (hasta {@value #LIMITE} resultados).
     *
     * @param rating categoria del hotel (p.ej. "FiveStar", "FourStar", "ThreeStar")
     * @return lista de hoteles con esa categoria
     */
    public List<Hotel> buscarPorRating(String rating) {
        return hotelRepository.findByHotelRatingIgnoreCase(
                rating, PageRequest.of(0, LIMITE)
        ).getContent();
    }

    /**
     * Devuelve hasta {@value #LIMITE} hoteles que tienen atracciones descritas.
     *
     * @return lista de hoteles con atracciones
     */
    public List<Hotel> buscarConAtracciones() {
        return hotelRepository.findHotelsWithAttractions(
                PageRequest.of(0, LIMITE)
        ).getContent();
    }

    /**
     * Devuelve estadisticas generales de los hoteles en la base de datos.
     *
     * <p>Incluye el total de hoteles y el recuento por categoria de estrellas.</p>
     *
     * @return objeto con las estadisticas
     */
    public HotelStats obtenerEstadisticas() {
        long total = hotelRepository.count();
        long cincoEstrellas = hotelRepository.countByHotelRatingIgnoreCase("FiveStar");
        long cuatroEstrellas = hotelRepository.countByHotelRatingIgnoreCase("FourStar");
        long tresEstrellas = hotelRepository.countByHotelRatingIgnoreCase("ThreeStar");
        return new HotelStats(total, cincoEstrellas, cuatroEstrellas, tresEstrellas);
    }

    // ==================== ESCRITURA ====================

    /**
     * Crea un nuevo hotel en la base de datos.
     *
     * @param hotel objeto Hotel con los datos a guardar
     * @return el hotel guardado con su ID asignado
     */
    public Hotel crear(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    /**
     * Actualiza los campos de un hotel existente.
     *
     * <p>Solo se actualizan los campos no nulos del objeto {@code detalles}.</p>
     *
     * @param id       identificador del hotel a actualizar
     * @param detalles objeto Hotel con los nuevos valores
     * @return el hotel actualizado
     * @throws HotelNotFoundException si no existe ningun hotel con ese ID
     */
    public Hotel actualizar(String id, Hotel detalles) {
        Hotel existente = buscarPorId(id);
        if (detalles.getHotelName() != null)        existente.setHotelName(detalles.getHotelName());
        if (detalles.getHotelCode() != null)        existente.setHotelCode(detalles.getHotelCode());
        if (detalles.getCountryCode() != null)      existente.setCountryCode(detalles.getCountryCode());
        if (detalles.getCountryName() != null)      existente.setCountryName(detalles.getCountryName());
        if (detalles.getCityCode() != null)         existente.setCityCode(detalles.getCityCode());
        if (detalles.getCityName() != null)         existente.setCityName(detalles.getCityName());
        if (detalles.getHotelRating() != null)      existente.setHotelRating(detalles.getHotelRating());
        if (detalles.getAddress() != null)          existente.setAddress(detalles.getAddress());
        if (detalles.getAttractions() != null)      existente.setAttractions(detalles.getAttractions());
        if (detalles.getDescription() != null)      existente.setDescription(detalles.getDescription());
        if (detalles.getHotelFacilities() != null)  existente.setHotelFacilities(detalles.getHotelFacilities());
        if (detalles.getHotelWebsiteUrl() != null)  existente.setHotelWebsiteUrl(detalles.getHotelWebsiteUrl());
        return hotelRepository.save(existente);
    }

    /**
     * Elimina un hotel por su identificador.
     *
     * @param id identificador del hotel a eliminar
     * @throws HotelNotFoundException si no existe ningun hotel con ese ID
     */
    public void eliminar(String id) {
        if (!hotelRepository.existsById(id)) {
            throw new HotelNotFoundException("Hotel no encontrado con ID: " + id);
        }
        hotelRepository.deleteById(id);
    }

    // ==================== CLASE INTERNA ====================

    /**
     * Record con estadisticas generales de hoteles para mostrar en el dashboard.
     *
     * @param total           numero total de hoteles en la base de datos
     * @param cincoEstrellas  numero de hoteles de cinco estrellas
     * @param cuatroEstrellas numero de hoteles de cuatro estrellas
     * @param tresEstrellas   numero de hoteles de tres estrellas
     */
    public record HotelStats(long total, long cincoEstrellas, long cuatroEstrellas, long tresEstrellas) {}
}
