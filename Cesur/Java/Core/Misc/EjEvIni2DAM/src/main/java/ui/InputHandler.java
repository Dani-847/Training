package ui;

import java.util.Scanner;

public class InputHandler {

    public static String leerCadena(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String valor = sc.nextLine().trim();
        return valor;
    }

    public static int leerEntero(Scanner sc, String mensaje) {
        Integer numero = null;
        while (numero == null) {
            String entrada = leerCadena(sc, mensaje);
            try {
                numero = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido.");
            }
        }
        return numero;
    }
}
