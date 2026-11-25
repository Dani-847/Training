package org.example;
public class HebraEscritora extends Thread {
    private VariableCompartida vc;

    public HebraEscritora(VariableCompartida vc) {
        this.vc = vc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            vc.set(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}