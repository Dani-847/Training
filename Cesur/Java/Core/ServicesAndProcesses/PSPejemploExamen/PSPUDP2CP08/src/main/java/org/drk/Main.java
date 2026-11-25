package org.drk;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ControladorEntradaSalida controladorEntradaSalida = new ControladorEntradaSalida();
        int nMedidas = 20;
        int limite = 28;

        SensorTemperatura sensor = new SensorTemperatura(controladorEntradaSalida, nMedidas);
        ControladorAmbiental controlador = new ControladorAmbiental(controladorEntradaSalida, nMedidas, limite);

        sensor.start();
        controlador.start();

        sensor.join();
        controlador.join();

        System.out.println("Simulación finalizada.");
    }
}
