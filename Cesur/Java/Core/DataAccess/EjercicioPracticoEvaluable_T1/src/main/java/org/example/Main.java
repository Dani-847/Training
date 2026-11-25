package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataService dataService = new CsvDataService("peliculas.csv");
        List<Pelicula> peliculas = dataService.findAll();
        ServicioFiltradoPeliculas.filtrarPorGenero(dataService, peliculas, "Acción", "accion.csv");
        ServicioFiltradoPeliculas.filtrarPorDirector(dataService, peliculas, "Jonathan Demme", "demme.csv");
    }
}