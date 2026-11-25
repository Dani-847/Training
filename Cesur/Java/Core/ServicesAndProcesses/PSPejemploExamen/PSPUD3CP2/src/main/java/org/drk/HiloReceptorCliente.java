package org.drk;

import java.io.BufferedReader;
import java.io.IOException;

public class HiloReceptorCliente extends Thread {
    private final BufferedReader in;
    private final String nombreCliente;

    public HiloReceptorCliente(BufferedReader in, String nombreCliente) {
        this.in = in;
        this.nombreCliente = nombreCliente;
    }

    public void run() {
        String linea;
        try {
            while ((linea = in.readLine()) != null) {
                System.out.println("[" + nombreCliente + "] " + linea);
            }
        } catch (IOException ignored) {}
    }
}
