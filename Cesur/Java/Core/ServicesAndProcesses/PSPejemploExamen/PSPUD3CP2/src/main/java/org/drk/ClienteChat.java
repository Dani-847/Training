package org.drk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteChat extends Thread {
    private final String nombreCliente;
    private static final String SERVER_NAME = "127.0.0.1";
    private static final int PORT = 1234;

    public ClienteChat(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void run() {
        try (Socket socket = new Socket(SERVER_NAME, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("[" + nombreCliente + "] Bienvenido al chat! Por favor, escribe tu nombre:");
            out.println(nombreCliente);

            HiloReceptorCliente receptor = new HiloReceptorCliente(in, nombreCliente);
            receptor.start();

            for (int i = 0; i < 3; i++) {
                String mensaje = "Mensaje " + (i + 1) + " de " + nombreCliente;
                out.println(mensaje);
                Thread.sleep(100);
            }

            socket.shutdownOutput();
            receptor.join();
        } catch (UnknownHostException e) {
            System.err.println("Unknown: " + SERVER_NAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Conexión perdida con el servidor o error de E/S.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Conexión cerrada por servidor para " + nombreCliente);
    }
}
