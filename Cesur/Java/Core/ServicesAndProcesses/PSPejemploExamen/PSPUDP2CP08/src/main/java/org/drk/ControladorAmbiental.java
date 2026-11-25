package org.drk;

public class ControladorAmbiental extends Thread {
    private final ControladorEntradaSalida controladorEntradaSalida;
    private final int nMedidas;
    private final int limite;

    public ControladorAmbiental(ControladorEntradaSalida controladorEntradaSalida, int nMedidas, int limite) {
        this.controladorEntradaSalida = controladorEntradaSalida;
        this.nMedidas = nMedidas;
        this.limite = limite;
    }

    @Override
    public void run() {
        for (int i = 1; i <= nMedidas && !isInterrupted(); i++) {
            int temp;
            try {
                temp = controladorEntradaSalida.leerTemperatura();
            } catch (InterruptedException e) {
                interrupt();
                break;
            }
            System.out.println("Lectura " + i + ": " + temp + "°C");
            if (temp > limite) {
                System.out.println("Temperatura > " + limite + "°C: activando ventilación.");
            } else {
                System.out.println("Temperatura normal.");
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
