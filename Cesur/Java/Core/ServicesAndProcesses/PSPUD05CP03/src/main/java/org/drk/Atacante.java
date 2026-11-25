package org.drk;

/**
 * Clase que representa al Atacante.
 * El Atacante observa todos los mensajes que viajan por el canal,
 * puede ver los mensajes cifrados pero no entenderlos.
 */
public class Atacante {

    /**
     * El Atacante intercepta un mensaje del canal.
     * Solo puede ver el mensaje cifrado, no puede descifrar su contenido.
     * @param mensajeCifrado Mensaje cifrado interceptado
     */
    public void interceptar(String mensajeCifrado) {
        System.out.println("Atacante intercepta el mensaje cifrado en el canal: " + mensajeCifrado);
    }
}

