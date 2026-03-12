package org.drk.Reto6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicacion web de turismo.
 *
 * <p>Aplicacion Spring Boot MVC que permite consultar y gestionar hoteles
 * almacenados en la base de datos MongoDB {@code turismo}, accediendo
 * directamente al origen de datos sin consumir la API REST.</p>
 *
 * <p>Requiere la variable de entorno {@code MONGODB_URI} configurada antes
 * de arrancar.</p>
 */
@SpringBootApplication
public class BibliotecaDeJuegosSpringBootApplication {

    /**
     * Metodo principal que arranca el contexto de Spring Boot.
     *
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaDeJuegosSpringBootApplication.class, args);
    }
}
