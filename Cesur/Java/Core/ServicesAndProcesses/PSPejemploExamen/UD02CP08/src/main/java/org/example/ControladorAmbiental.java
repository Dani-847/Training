package org.example;

public class ControladorAmbiental extends Thread {
    private BufferTemperatura buffer;
    private static final int UMBRAL_TEMPERATURA = 28;

    public ControladorAmbiental(BufferTemperatura buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for(int i = 0; i < 10; ++i) {
            int temperatura = this.buffer.leerTemperatura();
            System.out.println("Controlador: Temperatura leída: " + temperatura + "°C");
            if (temperatura > 28) {
                System.out.println("Alerta: Temperatura alta detectada!");
                System.out.println("Acción: Activando sistema de ventilación...");
            } else {
                System.out.println("Estado: Temperatura en rango normal. Todo correcto.");
            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException var4) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Controlador: Finalizado el control ambiental.");
    }
}