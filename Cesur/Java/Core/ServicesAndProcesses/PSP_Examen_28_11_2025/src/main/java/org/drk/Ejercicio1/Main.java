package org.drk.Ejercicio1;

/**
 * Clase Main para iniciar la simulación del sistema de control ambiental.
 * Crea una instancia de Supervisor y la inicia.
 * @author Drk
 */
public class Main {
    public static void main(String[] args) {
        Supervisor supervisor = new Supervisor();
        supervisor.start();
    }
}
