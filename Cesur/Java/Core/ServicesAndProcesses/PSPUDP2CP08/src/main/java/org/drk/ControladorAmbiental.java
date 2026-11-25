package org.drk;

public class ControladorAmbiental extends Thread {
    private ControladorEntradaSalida controladorEntradaSalida;
    private int nMedidas;
    private int limite;

    public ControladorAmbiental(ControladorEntradaSalida controladorEntradaSalida, int nMedidas, int limite) {
        this.controladorEntradaSalida = controladorEntradaSalida;
        this.nMedidas = nMedidas;
        this.limite = limite;
    }

    @Override
    public void run() {
        for (int i = 1; i <= nMedidas && !Thread.currentThread().isInterrupted(); i++) {
            int temp = controladorEntradaSalida.get();
            System.out.println("Lectura " + i + ": " + temp + "°C");
            if (temp > limite) {
                System.out.println("Temperatura > " + limite + "°C: activando ventilación.");
            } else {
                System.out.println("Temperatura normal.");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
