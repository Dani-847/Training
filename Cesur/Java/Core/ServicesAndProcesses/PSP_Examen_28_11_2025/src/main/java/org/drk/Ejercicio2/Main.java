package org.drk.Ejercicio2;

/**
 * Ejercicio 2: Impresora compartida entre varias personas.
 *
 * Tres personas intentan imprimir cinco documentos cada una utilizando una impresora compartida.
 * La impresora solo puede ser utilizada por una persona a la vez.
 * Se utiliza sincronización para evitar condiciones de carrera y asegurar que solo una persona pueda imprimir a la vez.
 * Se utiliza wait() y notifyAll() para manejar la disponibilidad de la impresora.
 * Se simula el tiempo de impresión con Thread.sleep().
 * Cada persona imprime sus documentos en su propio hilo.
 * Se evita la espera activa.
 * Se manejan las interrupciones adecuadamente.
 * @author Drk
 */
public class Main {
    public static void main(String[] args) {
        Impresora impresora = new Impresora();

        Persona persona1 = new Persona("Persona 1", 5, impresora);
        Persona persona2 = new Persona("Persona 2", 5, impresora);
        Persona persona3 = new Persona("Persona 3", 5, impresora);

        persona1.start();
        persona2.start();
        persona3.start();
    }
}