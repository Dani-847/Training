package org.drk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente extends Thread {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String nombreCliente;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
        System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            nombreCliente = in.readLine();
            if (nombreCliente == null) return;

            synchronized (ServidorChat.clientesConectados) {
                ServidorChat.clientesConectados.add(out);
                ServidorChat.nombresClientes.add(nombreCliente);
            }

            System.out.println(nombreCliente + " se ha unido al chat.");

            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                System.out.println("Mensaje de " + nombreCliente + ": " + mensaje);
                broadcast(nombreCliente + ": " + mensaje, nombreCliente);
            }
        } catch (IOException e) {
            System.out.println("Cliente " + nombreCliente + " desconectado o error: " + e.getMessage());
        } finally {
            if (nombreCliente != null) {
                synchronized (ServidorChat.clientesConectados) {
                    ServidorChat.clientesConectados.remove(out);
                    ServidorChat.nombresClientes.remove(nombreCliente);
                }
                System.out.println(nombreCliente + " ha salido del chat.");
            }
            try { socket.close(); } catch (IOException ignored) {}
        }
    }

    private void broadcast(String message, String senderName) {
        synchronized (ServidorChat.clientesConectados) {
            for (int i = 0; i < ServidorChat.clientesConectados.size(); i++) {
                if (!ServidorChat.nombresClientes.get(i).equals(senderName)) {
                    ServidorChat.clientesConectados.get(i).println(message);
                }
            }
        }
    }
}
