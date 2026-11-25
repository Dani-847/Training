package org.example;

public class VariableCompartida {
    private int v;

    public VariableCompartida(int v) {
        this.v = v;
    }


    public synchronized int get() {
        return v;
    }

    public synchronized void set(int v) {
        this.v = v;

        notifyAll();
    }

    public synchronized void inc() {
        this.v++;
    }

    @Override
    public String toString() {
        return "VariableCompartida[" + "v = " + v + ']';
    }
}