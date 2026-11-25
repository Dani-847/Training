package org.drk.reto4_ad_turismo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "kaggle_hotels_dataset")
public class Hotel {
    @Id
    private String _id;

    @Field("countyCode")
    private String countryCode;

    @Field("countyName")
    private String countryName;

    private Integer cityCode;
    private String cityName;

    @Field("HotelCode")
    private Integer hotelCode;

    @Field("HotelName")
    private String hotelName;

    @Field("HotelRating")
    private String hotelRating;

    @Field("Address")
    private String address;

    @Field("Attractions")
    private String attractions;

    @Field("Description")
    private String description;

    @Field("FaxNumber")
    private Object faxNumber;

    @Field("HotelFacilities")
    private String hotelFacilities;

    @Field("Map")
    private String map;

    @Field("PhoneNumber")
    private Object phoneNumber;

    @Field("PinCode")
    private Object pinCode;

    @Field("HotelWebsiteUrl")
    private String hotelWebsiteUrl;
}

