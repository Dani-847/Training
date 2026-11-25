package org.drk;

public class ControladorEntradaSalida {
    private int temperatura;
    private boolean available = false;

    public void put(int temp) {
        while (available) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        temperatura = temp;
        available = true;
    }

    public int get() {
        while (!available) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int temp = temperatura;
        available = false;
        return temp;
    }
}
