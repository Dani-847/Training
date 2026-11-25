package org.example;

public class HebraLectora extends Thread {
    private VariableCompartida vc;
    private int lastReadValue = -1;

    public HebraLectora(VariableCompartida vc) {
        this.vc = vc;
    }

    @Override
    public void run() {

        while (lastReadValue < 99) {
            synchronized(vc) {
                int currentValue = vc.get();
                if (currentValue != lastReadValue) {
                    System.out.println("Lector detectó: " + currentValue);
                    lastReadValue = currentValue;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Lector ha terminado de leer hasta 99.");
    }
}
