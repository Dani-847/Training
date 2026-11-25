package org.drk.Reto6.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entidad que representa un hotel en la coleccion MongoDB {@code kaggle_hotels_dataset}.
 *
 * <p>Mapea el documento MongoDB con los campos del dataset de hoteles de Kaggle.
 * Es el mismo origen de datos que usa la API REST {@code AD_Reto4_Turismo}.</p>
 */
@Data
@Document(collection = "kaggle_hotels_dataset")
public class Hotel {

    /**
     * Identificador unico del documento MongoDB.
     */
    @Id
    private String _id;

    /**
     * Codigo del pais (nota: el campo en MongoDB se llama "countyCode" por error tipografico del dataset).
     */
    @Field("countyCode")
    private String countryCode;

    /**
     * Nombre del pais (nota: el campo en MongoDB se llama "countyName" por error tipografico del dataset).
     */
    @Field("countyName")
    private String countryName;

    /**
     * Codigo numerico de la ciudad.
     */
    private Integer cityCode;

    /**
     * Nombre de la ciudad donde se ubica el hotel.
     */
    private String cityName;

    /**
     * Codigo numerico unico del hotel.
     */
    @Field("HotelCode")
    private Integer hotelCode;

    /**
     * Nombre del hotel.
     */
    @Field("HotelName")
    private String hotelName;

    /**
     * Categoria del hotel (p.ej. FiveStar, FourStar, ThreeStar).
     */
    @Field("HotelRating")
    private String hotelRating;

    /**
     * Direccion postal del hotel.
     */
    @Field("Address")
    private String address;

    /**
     * Descripcion de las atracciones cercanas al hotel.
     */
    @Field("Attractions")
    private String attractions;

    /**
     * Descripcion general del hotel.
     */
    @Field("Description")
    private String description;

    /**
     * Numero de fax del hotel. Puede ser String o numero segun el documento.
     */
    @Field("FaxNumber")
    private Object faxNumber;

    /**
     * Lista de instalaciones y servicios disponibles en el hotel.
     */
    @Field("HotelFacilities")
    private String hotelFacilities;

    /**
     * Enlace al mapa de ubicacion del hotel.
     */
    @Field("Map")
    private String map;

    /**
     * Numero de telefono del hotel. Puede ser String o numero segun el documento.
     */
    @Field("PhoneNumber")
    private Object phoneNumber;

    /**
     * Codigo postal del hotel. Puede ser String o numero segun el documento.
     */
    @Field("PinCode")
    private Object pinCode;

    /**
     * URL del sitio web oficial del hotel.
     */
    @Field("HotelWebsiteUrl")
    private String hotelWebsiteUrl;
}
