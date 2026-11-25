package org.drk.Ejercicio2;

public class Impresora {
    private boolean disponible = true;

    /**
     * Imprime un documento para una persona específica.
     * Usa sincronización para asegurar que solo una persona pueda imprimir a la vez.
     * Usa wait() y notifyAll() para manejar la disponibilidad de la impresora.
     * Usa Thread.sleep() para simular el tiempo de impresión.
     * Usa el nombre de la persona y el número del documento para los mensajes de impresión.
     * Evita la espera activa.
     * Evita condiciones de carrera.
     */
    public synchronized void imprimirDocumento(String nombrePersona, int documentoNumero) throws InterruptedException {
        while (!disponible) {
            wait();
        }
        disponible = false;
        System.out.println(nombrePersona + " empieza a imprimir el documento #" + documentoNumero);
        Thread.sleep(1000);
        System.out.println(nombrePersona + " terminó de imprimir el documento #" + documentoNumero);
        disponible = true;
        notifyAll();
    }
}
