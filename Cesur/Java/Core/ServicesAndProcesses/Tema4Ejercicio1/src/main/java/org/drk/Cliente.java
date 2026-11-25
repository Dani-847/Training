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
            RMI interfaz = (RMI) registro.lookup("RemotoRMI");

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
                        int a = leerEntero(sc, "x: ");
                        int b = leerEntero(sc, "y: ");
                        System.out.println("Resultado: " + interfaz.sumar(a, b));
                    }
                    case 2 -> {
                        int a = leerEntero(sc, "x: ");
                        int b = leerEntero(sc, "y: ");
                        System.out.println("Resultado: " + interfaz.resta(a, b));
                    }
                    case 3 -> {
                        int a = leerEntero(sc, "x: ");
                        int b = leerEntero(sc, "y: ");
                        System.out.println("Resultado: " + interfaz.multiplica(a, b));
                    }
                    case 4 -> {
                        double a = leerDouble(sc, "x: ");
                        double b = leerDouble(sc, "y: ");
                        System.out.println("Resultado: " + interfaz.divide(a, b));
                    }
                    case 5 -> {
                        double x = leerDouble(sc, "x (porcentaje): ");
                        double y = leerDouble(sc, "y (número): ");
                        System.out.println("Resultado: " + interfaz.porcentaje(x, y));
                    }
                    case 6 -> {
                        int a = leerEntero(sc, "x: ");
                        int b = leerEntero(sc, "y: ");
                        System.out.println("Resultado: " + interfaz.resto(a, b));
                    }
                    case 7 -> {
                        double[] arr = leerArrayDouble(sc);
                        System.out.println("Resultado: " + interfaz.media(arr));
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
        System.out.println("=== Menú RMI ===");
        System.out.println("1) Suma (x + y)");
        System.out.println("2) Resta (x - y)");
        System.out.println("3) Multiplica (x * y)");
        System.out.println("4) Divide (x / y)");
        System.out.println("5) Porcentaje (x% de y)");
        System.out.println("6) Resto (x % y)");
        System.out.println("7) Media (array)");
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

    private static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String line = sc.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número (double).");
            }
        }
    }

    private static double[] leerArrayDouble(Scanner sc) {
        System.out.println("Introduce números para el array. Escribe una línea vacía para terminar.");
        List<Double> valores = new ArrayList<>();

        while (true) {
            System.out.print("Valor " + (valores.size() + 1) + ": ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;

            line = line.replace(',', '.');
            try {
                valores.add(Double.parseDouble(line));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Inténtalo de nuevo.");
            }
        }

        double[] arr = new double[valores.size()];
        for (int i = 0; i < valores.size(); i++) arr[i] = valores.get(i);
        return arr;
    }
}
