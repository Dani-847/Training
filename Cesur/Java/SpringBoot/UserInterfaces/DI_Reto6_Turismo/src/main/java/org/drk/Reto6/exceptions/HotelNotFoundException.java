package org.drk.Reto6.exceptions;

/**
 * Excepcion lanzada cuando no se encuentra un hotel en la base de datos.
 *
 * <p>Se lanza desde {@code HotelService} cuando se busca un hotel
 * por ID o codigo y no existe ninguno con ese identificador.</p>
 */
public class HotelNotFoundException extends RuntimeException {

    /**
     * Crea una nueva excepcion con el mensaje indicado.
     *
     * @param message descripcion del error (p.ej. "Hotel no encontrado con ID: 123")
     */
    public HotelNotFoundException(String message) {
        super(message);
    }
}
