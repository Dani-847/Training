package com.drk.pmdmud13cp01.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "peliculas_favoritas_usuario",
    primaryKeys = ["usuarioId", "peliculaId"],
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id"],
            childColumns = ["usuarioId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("usuarioId"), Index("peliculaId")]
)
data class PeliculaFavorita(
    val usuarioId: Int,
    val peliculaId: Int,
    // Guardamos el objeto película completo embebido
    val id: Int,
    val title: String,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?
) {
    // Método auxiliar para convertir a Pelicula
    fun toPelicula(): Pelicula {
        return Pelicula(
            id = id,
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            backdropPath = backdropPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage
        )
    }

    companion object {
        // Método auxiliar para crear desde Pelicula y usuarioId
        fun fromPelicula(pelicula: Pelicula, usuarioId: Int): PeliculaFavorita {
            return PeliculaFavorita(
                usuarioId = usuarioId,
                peliculaId = pelicula.id,
                id = pelicula.id,
                title = pelicula.title,
                originalTitle = pelicula.originalTitle,
                overview = pelicula.overview,
                posterPath = pelicula.posterPath,
                backdropPath = pelicula.backdropPath,
                releaseDate = pelicula.releaseDate,
                voteAverage = pelicula.voteAverage
            )
        }
    }
}
