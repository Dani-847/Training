package org.drk.di_examen_18_03_26.entities;

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
    @Field("title")
    private String title;

    /** Precio del artículo. */
    @Field("price")
    private Double price;

    /** Categoría del artículo. */
    @Field("category")
    private String category;

    /** Descripción del artículo (puede incluir código HTML). */
    @Field("description")
    private String description;

    /** Valoración media en una escala del 1 al 10. */
    @Field("rate")
    private Double rate;

    /** Cantidad de veces que se ha vendido el producto. */
    @Field("count")
    private Integer count;

    /** Color del producto. */
    @Field("color")
    private String color;

    /** Fabricante del producto. */
    @Field("manufacturer")
    private String manufacturer;

    /** Código de barras asociado. */
    @Field("EAN")
    @JsonProperty("EAN")
    private String ean;

    /** URL con la imagen del producto. */
    @Field("image")
    private String image;
}

