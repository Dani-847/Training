package org.drk;

/**
 * Clase que representa a Bernardo, el receptor del mensaje.
 * Bernardo comparte la misma clave secreta con Ana y puede descifrar los mensajes.
 */
public class Bernardo {
    private final CifradoSimetrico cifrador;

    public Bernardo(CifradoSimetrico cifrador) {
        this.cifrador = cifrador;
    }

    /**
     * Bernardo recibe un mensaje cifrado y lo descifra.
     * @param mensajeCifrado Mensaje cifrado recibido
     */
    public void recibirMensaje(String mensajeCifrado) {
        System.out.println("Bernardo recibe el mensaje cifrado: " + mensajeCifrado);
        String mensajeDescifrado = cifrador.descifrar(mensajeCifrado);
        System.out.println("Bernardo descifra y obtiene: " + mensajeDescifrado);
    }
}

