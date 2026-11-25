package org.drk;

/**
 * Clase que representa el Canal Inseguro.
 * Es la vía por donde viajan los mensajes, no ofrece protección
 * y permite que el Atacante vea todo lo que circula.
 */
public class CanalInseguro {
    private final Atacante atacante;

    public CanalInseguro(Atacante atacante) {
        this.atacante = atacante;
    }

    /**
     * Envía un mensaje por el canal inseguro.
     * El Atacante puede interceptar y ver el mensaje.
     * @param mensaje Mensaje a enviar (cifrado)
     * @return El mismo mensaje (el canal no lo modifica)
     */
    public String enviar(String mensaje) {
        // El atacante intercepta el mensaje en el canal
        atacante.interceptar(mensaje);
        return mensaje;
    }
}

