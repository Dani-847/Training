package org.example;

import java.security.Provider;
import java.util.List;
import java.util.Optional;

public interface DataService{
    public List<Pelicula> findAll();
    Optional<Pelicula> save(Pelicula pelicula, String destino);

}
