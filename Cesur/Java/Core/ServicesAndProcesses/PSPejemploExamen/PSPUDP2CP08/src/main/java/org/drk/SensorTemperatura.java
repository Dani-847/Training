package org.drk;

import java.util.Random;

public class SensorTemperatura extends Thread {
    private final ControladorEntradaSalida controladorEntradaSalida;
    private final int nMedidas;
    private final Random rnd = new Random();

    public SensorTemperatura(ControladorEntradaSalida controladorEntradaSalida, int nMedidas) {
        this.controladorEntradaSalida = controladorEntradaSalida;
        this.nMedidas = nMedidas;
    }

    @Override
    public void run() {
        for (int i = 1; i <= nMedidas && !isInterrupted(); i++) {
            int temp = 20 + rnd.nextInt(16);
            System.out.println("Medición " + i + ": " + temp + "°C");
            try {
                controladorEntradaSalida.registrarTemperatura(temp);
            } catch (InterruptedException e) {
                interrupt();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                interrupt();
                break;
            }
        }
    }
}
