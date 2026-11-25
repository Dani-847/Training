package org.drk;

import java.util.Random;

public class SensorTemperatura extends Thread {
    private ControladorEntradaSalida controladorEntradaSalida;
    private int nMedidas;
    private Random rnd = new Random();

    public SensorTemperatura(ControladorEntradaSalida controladorEntradaSalida, int nMedidas) {
        this.controladorEntradaSalida = controladorEntradaSalida;
        this.nMedidas = nMedidas;
    }

    @Override
    public void run() {
        for (int i = 1; i <= nMedidas && !Thread.currentThread().isInterrupted(); i++) {
            int temp = 20 + rnd.nextInt(16);
            System.out.println("Medición " + i + ": " + temp + "°C");
            controladorEntradaSalida.put(temp);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
