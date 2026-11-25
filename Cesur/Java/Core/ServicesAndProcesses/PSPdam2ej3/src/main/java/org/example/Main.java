package org.example;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        VariableCompartida vc = new VariableCompartida(0);

        HebraEscritora escritora = new HebraEscritora(vc);
        HebraLectora lectora = new HebraLectora(vc);

        escritora.start();
        lectora.start();

        escritora.join();
        lectora.join();

        System.out.println("Final: " + vc.toString());

    }
}