package org.drk;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        try {
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 7777);
            RMI interfaz = (RMI) registro.lookup("ConversionMoneda");

            try (Scanner sc = new Scanner(System.in)) {
                ejecutarMenu(sc, interfaz);
            }
        } catch (Exception e) {
            System.err.println("Error conectando/ejecutando cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void ejecutarMenu(Scanner sc, RMI interfaz) {
        while (true) {
            imprimirMenu();
            int opcion = leerEntero(sc, "Selecciona una opción: ");

            try {
                switch (opcion) {
                    case 0 -> {
                        System.out.println("Saliendo...");
                        return;
                    }
                    case 1 -> {
                        float cantidad = leerFloat(sc, "Cantidad en Libras: ");
                        System.out.printf("Resultado: %.2f EUR%n", interfaz.librasEuros(cantidad));
                    }
                    case 2 -> {
                        float cantidad = leerFloat(sc, "Cantidad en Euros: ");
                        System.out.printf("Resultado: %.2f GBP%n", interfaz.eurosLibras(cantidad));
                    }
                    case 3 -> {
                        float cantidad = leerFloat(sc, "Cantidad en Dólares: ");
                        System.out.printf("Resultado: %.2f EUR%n", interfaz.dolaresEuros(cantidad));
                    }
                    case 4 -> {
                        float cantidad = leerFloat(sc, "Cantidad en Euros: ");
                        System.out.printf("Resultado: %.2f USD%n", interfaz.eurosDolares(cantidad));
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception ex) {
                System.err.println("Error en la operación: " + ex.getMessage());
            }

            System.out.println();
        }
    }

    private static void imprimirMenu() {
        System.out.println("=== Conversor de Monedas RMI ===");
        System.out.println("1) Libras a Euros");
        System.out.println("2) Euros a Libras");
        System.out.println("3) Dólares a Euros");
        System.out.println("4) Euros a Dólares");
        System.out.println("0) Salir");
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un entero.");
            }
        }
    }

    private static float leerFloat(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String line = sc.nextLine().trim().replace(',', '.');
            try {
                return Float.parseFloat(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número.");
            }
        }
    }
}
