package org.example;

public class Main {
    public static void main(String[] args) {
        BufferTemperatura buffer = new BufferTemperatura();
        SensorTemperatura sensor = new SensorTemperatura(buffer);
        ControladorAmbiental controlador = new ControladorAmbiental(buffer);
        sensor.start();
        controlador.start();

        try {
            sensor.join();
            controlador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
