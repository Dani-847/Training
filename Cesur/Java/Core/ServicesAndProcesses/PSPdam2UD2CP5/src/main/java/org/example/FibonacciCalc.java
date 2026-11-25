package org.example;

public class FibonacciCalc extends Thread {

    private int indice;
    private int fibN;
    private int fibN_1;

    public FibonacciCalc(int indice) {
        this.indice = indice;
    }

    @Override
    public void run() {
        try {
            sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (indice == 1 || indice == 0) {
            fibN = 1;
            fibN_1 = 0;
            System.out.println("Fibonacci index: " + indice + ", is: " + fibN + " Threads: " + Thread.activeCount());
        } else {
            FibonacciCalc f = new FibonacciCalc(indice - 1);
            f.start();
            while (f.isAlive());
            fibN = f.fibN + f.fibN_1;
            fibN_1 = f.fibN;
            System.out.println("Fibonacci index: " + indice + ", is: " + fibN + " Threads: " + Thread.activeCount());
            FibonacciCalc f2 = new FibonacciCalc((int) ( fibN_1*0.5));
            f2.start();
        }

    }
}
