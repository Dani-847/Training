package org.drk;

public class ControladorEntradaSalida {
    private int temperatura;
    private boolean disponible = false;

    public synchronized void registrarTemperatura(int temp) throws InterruptedException {
        while (disponible) {
            wait();
        }
        temperatura = temp;
        disponible = true;
        notifyAll();
    }

    public synchronized int leerTemperatura() throws InterruptedException {
        while (!disponible) {
            wait();
        }
        int temp = temperatura;
        disponible = false;
        notifyAll();
        return temp;
    }
}
