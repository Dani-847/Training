package org.drk;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServidorChat {
    private static final int PORT = 1234;
    protected static final List<PrintWriter> clientesConectados = new ArrayList<>();
    protected static final List<String> nombresClientes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado en el puerto " + PORT);
        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                new ManejadorCliente(listener.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
