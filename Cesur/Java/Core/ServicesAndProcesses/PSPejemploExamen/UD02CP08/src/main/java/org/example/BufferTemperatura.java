package org.example;

public class BufferTemperatura {
    private int temperatura;
    private boolean disponible = false;

    public synchronized void escribirTemperatura(int temp) {
        while(this.disponible) {
            try {
                this.wait();
            } catch (InterruptedException var3) {
                Thread.currentThread().interrupt();
            }
        }

        this.temperatura = temp;
        this.disponible = true;
        System.out.println("Sensor: Temperatura medida: " + temp + "°C");
        this.notifyAll();
    }

    public synchronized int leerTemperatura() {
        while(!this.disponible) {
            try {
                this.wait();
            } catch (InterruptedException var2) {
                Thread.currentThread().interrupt();
            }
        }

        this.disponible = false;
        this.notifyAll();
        return this.temperatura;
    }
}