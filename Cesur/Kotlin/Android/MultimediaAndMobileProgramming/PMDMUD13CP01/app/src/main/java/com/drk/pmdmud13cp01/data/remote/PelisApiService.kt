package com.drk.pmdmud13cp01.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PelisApiService {

    // En cartelera (now_playing)
    @GET("movie/now_playing")
    suspend fun getPeliculasEnCartelera(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<ResultadoPeliculas>

    // Populares
    @GET("movie/popular")
    suspend fun getPeliculasPopulares(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<ResultadoPeliculas>

    // Mejor valoradas (top_rated)
    @GET("movie/top_rated")
    suspend fun getPeliculasMejorValoradas(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<ResultadoPeliculas>

    // Próximamente (upcoming)
    @GET("movie/upcoming")
    suspend fun getPeliculasProximamente(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<ResultadoPeliculas>

}

