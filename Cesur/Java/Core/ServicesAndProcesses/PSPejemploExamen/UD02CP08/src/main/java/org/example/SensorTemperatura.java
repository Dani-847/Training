package org.example;

public class SensorTemperatura extends Thread {
    private BufferTemperatura buffer;
    private static final int MIN_TEMP = 18;
    private static final int MAX_TEMP = 35;

    public SensorTemperatura(BufferTemperatura buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for(int i = 0; i < 10; ++i) {
            int temperatura = 18 + (int)(Math.random() * (double)18.0F);
            this.buffer.escribirTemperatura(temperatura);

            try {
                Thread.sleep((long)(1000 + (int)(Math.random() * (double)1000.0F)));
            } catch (InterruptedException var4) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Sensor Finalizado el monitoreo de temperatura.");
    }
}
