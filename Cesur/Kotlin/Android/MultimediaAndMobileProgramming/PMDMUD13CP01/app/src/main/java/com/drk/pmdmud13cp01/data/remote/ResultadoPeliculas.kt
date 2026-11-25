package com.drk.pmdmud13cp01.data.remote

import com.drk.pmdmud13cp01.model.Pelicula
import com.google.gson.annotations.SerializedName

data class ResultadoPeliculas(
    @SerializedName("results") val results: List<Pelicula>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int
)
