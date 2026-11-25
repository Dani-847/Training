package org.example;

public class Main {
    public static void main(String[] args) {
        FibonacciCalc fibThread = new FibonacciCalc(20);

        fibThread.start();
    }
}