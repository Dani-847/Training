package org.drk.Reto6.repositories;

import org.drk.Reto6.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio MongoDB para la entidad {@link Hotel}.
 *
 * <p>Extiende {@link MongoRepository} y define consultas derivadas y personalizadas
 * para acceder a la coleccion {@code kaggle_hotels_dataset} de la base de datos
 * {@code turismo}, el mismo origen de datos que la API REST {@code AD_Reto4_Turismo}.</p>
 */
@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {

    /**
     * Busca un hotel por su codigo unico.
     *
     * @param hotelCode codigo numerico del hotel
     * @return Optional con el hotel si existe
     */
    Optional<Hotel> findByHotelCode(Integer hotelCode);

    /**
     * Busca hoteles cuyo nombre contenga el texto indicado (sin distinguir mayusculas).
     *
     * @param hotelName texto a buscar en el nombre
     * @return lista de hoteles que coinciden
     */
    List<Hotel> findByHotelNameContainingIgnoreCase(String hotelName);

    /**
     * Busca hoteles por codigo de pais con paginacion.
     *
     * @param countryCode codigo del pais (p.ej. "ES", "US")
     * @param pageable    parametros de paginacion
     * @return pagina de hoteles del pais indicado
     */
    Page<Hotel> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);

    /**
     * Busca hoteles por nombre de pais (sin distinguir mayusculas).
     *
     * @param countryName nombre completo del pais
     * @return lista de hoteles del pais
     */
    List<Hotel> findByCountryNameIgnoreCase(String countryName);

    /**
     * Busca hoteles por nombre de ciudad con paginacion.
     *
     * @param cityName nombre de la ciudad
     * @param pageable parametros de paginacion
     * @return pagina de hoteles de la ciudad indicada
     */
    Page<Hotel> findByCityNameIgnoreCase(String cityName, Pageable pageable);

    /**
     * Busca hoteles por codigo numerico de ciudad.
     *
     * @param cityCode codigo de la ciudad
     * @return lista de hoteles de la ciudad
     */
    List<Hotel> findByCityCode(Integer cityCode);

    /**
     * Busca hoteles por categoria con paginacion.
     *
     * @param rating   categoria del hotel (p.ej. "FiveStar", "FourStar")
     * @param pageable parametros de paginacion
     * @return pagina de hoteles con la categoria indicada
     */
    Page<Hotel> findByHotelRatingIgnoreCase(String rating, Pageable pageable);

    /**
     * Busca hoteles cuyas instalaciones contengan el texto indicado (expresion regular).
     *
     * @param facility texto de la instalacion a buscar (p.ej. "WiFi", "Pool")
     * @param pageable parametros de paginacion
     * @return pagina de hoteles con esa instalacion
     */
    @Query("{ 'HotelFacilities': { $regex: ?0, $options: 'i' } }")
    Page<Hotel> findByHotelFacilitiesContaining(String facility, Pageable pageable);

    /**
     * Busca hoteles por pais y categoria simultaneamente.
     *
     * @param countryCode codigo del pais
     * @param rating      categoria del hotel
     * @param pageable    parametros de paginacion
     * @return pagina de hoteles filtrados por pais y categoria
     */
    Page<Hotel> findByCountryCodeIgnoreCaseAndHotelRatingIgnoreCase(
            String countryCode, String rating, Pageable pageable);

    /**
     * Busca hoteles por ciudad y categoria simultaneamente.
     *
     * @param cityName nombre de la ciudad
     * @param rating   categoria del hotel
     * @param pageable parametros de paginacion
     * @return pagina de hoteles filtrados por ciudad y categoria
     */
    Page<Hotel> findByCityNameIgnoreCaseAndHotelRatingIgnoreCase(
            String cityName, String rating, Pageable pageable);

    /**
     * Cuenta el numero de hoteles en un pais.
     *
     * @param countryCode codigo del pais
     * @return numero de hoteles
     */
    long countByCountryCodeIgnoreCase(String countryCode);

    /**
     * Cuenta el numero de hoteles en una ciudad.
     *
     * @param cityName nombre de la ciudad
     * @return numero de hoteles
     */
    long countByCityNameIgnoreCase(String cityName);

    /**
     * Cuenta el numero de hoteles con una categoria determinada.
     *
     * @param rating categoria del hotel
     * @return numero de hoteles
     */
    long countByHotelRatingIgnoreCase(String rating);

    /**
     * Busca hoteles que tengan atracciones descritas (campo no vacio ni nulo).
     *
     * @param pageable parametros de paginacion
     * @return pagina de hoteles con atracciones
     */
    @Query("{ 'Attractions': { $ne: '', $exists: true } }")
    Page<Hotel> findHotelsWithAttractions(Pageable pageable);

    /**
     * Comprueba si ya existe un hotel con el codigo indicado.
     *
     * @param hotelCode codigo numerico del hotel
     * @return {@code true} si existe, {@code false} en caso contrario
     */
    boolean existsByHotelCode(Integer hotelCode);
}
