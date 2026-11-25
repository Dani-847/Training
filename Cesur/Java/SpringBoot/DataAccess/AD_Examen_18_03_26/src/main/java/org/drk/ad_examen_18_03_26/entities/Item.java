package org.drk.ad_examen_18_03_26.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entidad Item que representa un artículo de la tienda online.
 * Se almacena en la colección "items" de MongoDB.
 */
@Data
@Document(collection = "store")
public class Item {

    /** Identificador único generado automáticamente por MongoDB. */
    @Id
    private String _id;

    /** Identificador interno del ítem (1-30). */
    @Field("id")
    @JsonProperty("id")
    private Integer itemId;

    /** Nombre del artículo. */
    private String title;

    /** Precio del artículo. */
    private Double price;

    /** Categoría del artículo. */
    private String category;

    /** Descripción del artículo (puede incluir código HTML). */
    private String description;

    /** Valoración media en una escala del 1 al 10. */
    private Double rate;

    /** Cantidad de veces que se ha vendido el producto. */
    private Integer count;

    /** Color del producto. */
    private String color;

    /** Fabricante del producto. */
    private String manufacturer;

    /** Código de barras asociado. */
    @Field("EAN")
    @JsonProperty("EAN")
    private String ean;

    /** URL con la imagen del producto. */
    private String image;
}

