package org.example.data;

import java.util.List;
import java.util.Optional;

/**
 *
 * Interfaz encargada de gestionar la persistencia de los datos
 *
 */
public interface DataService{

    public List<Juego> findAll();
    Optional<Juego> save(Juego juego);

}
