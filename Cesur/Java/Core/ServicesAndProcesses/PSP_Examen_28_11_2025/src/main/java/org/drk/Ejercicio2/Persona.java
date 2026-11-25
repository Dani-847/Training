package org.drk.Ejercicio2;

public class Persona extends Thread{
    private final String nombre;
    private final int documentosAImprimir;
    private final Impresora impresora;

    public Persona(String nombre, int documentosAImprimir, Impresora impresora) {
        this.impresora = impresora;
        this.nombre = nombre;
        this.documentosAImprimir = documentosAImprimir;
    }

    /**
     * Método que se ejecuta al iniciar el hilo. Imprime los documentos asignados utilizando la impresora compartida.
     * Cada documento se imprime llamando al método imprimirDocumento de la clase Impresora.
     * Se maneja la excepción InterruptedException que puede ser lanzada durante la impresión.
     * Después de imprimir cada documento, el hilo cede su tiempo de CPU con Thread.yield().
     */
    @Override
    public void run() {
        for (int i = 1; i <= documentosAImprimir; i++) {
            try {
                impresora.imprimirDocumento(nombre, i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }

    }
}
