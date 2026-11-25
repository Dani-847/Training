package org.example;

import java.util.List;

public interface ServicioFiltradoPeliculas extends  DataService{
    static void filtrarPorGenero(DataService dataService, List<Pelicula> peliculas, String genero, String destino) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getGenero().equalsIgnoreCase(genero)) {
                dataService.save(pelicula, destino);
                System.out.println("Guardando pelicula: " + pelicula.getTitulo() + " en " + destino);
            }
        }
    }

    static void filtrarPorDirector(DataService dataService,List<Pelicula> peliculas, String director, String destino) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getDirector().equalsIgnoreCase(director)) {
                dataService.save(pelicula, destino);
                System.out.println("Guardando pelicula: " + pelicula.getTitulo() + " en " + destino);
            }
        }
    }
}
