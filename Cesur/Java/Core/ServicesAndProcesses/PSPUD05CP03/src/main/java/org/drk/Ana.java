package org.drk;

/**
 * Clase que representa a Ana, la emisora del mensaje.
 * Ana tiene el mensaje original y usa cifrado simétrico para enviarlo.
 */
public class Ana {
    private final CifradoSimetrico cifrador;

    public Ana(CifradoSimetrico cifrador) {
        this.cifrador = cifrador;
    }

    /**
     * Ana envía un mensaje cifrándolo primero.
     * @param mensaje Mensaje en texto claro
     * @return Mensaje cifrado
     */
    public String enviarMensaje(String mensaje) {
        System.out.println("Ana quiere enviar (en claro): " + mensaje);
        String mensajeCifrado = cifrador.cifrar(mensaje);
        System.out.println("Ana cifra el mensaje y obtiene: " + mensajeCifrado);
        return mensajeCifrado;
    }
}

