package org.drk;

/**
 * Clase que implementa el Cifrado César (cifrado simétrico).
 * Cifra y descifra mensajes usando una clave entera.
 */
public class CifradoSimetrico {
    private final int clave;

    public CifradoSimetrico(int clave) {
        // Normalizar la clave al rango 0-25
        this.clave = ((clave % 26) + 26) % 26;
    }

    /**
     * Cifra un mensaje usando el Cifrado César.
     * Desplaza cada letra según la clave.
     * @param mensaje Mensaje en texto claro
     * @return Mensaje cifrado
     */

    public String cifrar(String mensaje) {
        StringBuilder resultado = new StringBuilder();
        for (char c : mensaje.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char cifrado = (char) ((c - 'A' + clave) % 26 + 'A');
                resultado.append(cifrado);
            } else if (c >= 'a' && c <= 'z') {
                char cifrado = (char) ((c - 'a' + clave) % 26 + 'a');
                resultado.append(cifrado);
            } else {
                // Cualquier otro carácter (espacios, puntuación, acentos) se mantiene igual
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    /**
     * Descifra un mensaje usando el Cifrado César.
     * Desplaza cada letra en sentido contrario según la clave.
     * @param mensaje Mensaje cifrado
     * @return Mensaje descifrado (texto claro)
     */
    public String descifrar(String mensaje) {
        StringBuilder resultado = new StringBuilder();
        for (char c : mensaje.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char descifrado = (char) ((c - 'A' - clave + 26) % 26 + 'A');
                resultado.append(descifrado);
            } else if (c >= 'a' && c <= 'z') {
                char descifrado = (char) ((c - 'a' - clave + 26) % 26 + 'a');
                resultado.append(descifrado);
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

}

