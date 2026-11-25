package org.drk;

public class MainCliente {
    public static void main(String[] args) {
        ClienteChat c1 = new ClienteChat("Cliente1");
        ClienteChat c2 = new ClienteChat("Cliente2");
        ClienteChat c3 = new ClienteChat("Cliente3");
        c1.start();
        c2.start();
        c3.start();

        try {
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Todos los clientes finalizaron");
    }
}
