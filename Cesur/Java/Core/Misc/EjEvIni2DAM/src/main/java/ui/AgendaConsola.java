package ui;

import modelo.Contacto;
import servicio.AgendaMetodos;

import java.util.List;
import java.util.Scanner;

public class AgendaConsola {

    private final AgendaMetodos service;
    private final InputHandler input;
    private final Scanner scanner;

    public AgendaConsola() {
        this.scanner = new Scanner(System.in);
        this.service = new AgendaMetodos(20);
        this.input = new InputHandler();
    }

    public void iniciar() {
        int opcion = -1;
        MenuPrint.imprimir();
        while (opcion != 4) {
            opcion = input.leerEntero(scanner, "Seleccione una opción: ");
            System.out.println("");
            if (opcion == 1) {
                ejecutarAgregar();
            } else {
                if (opcion == 2) {
                    ejecutarListar();
                } else {
                    if (opcion == 3) {
                        ejecutarBuscar();
                    } else {
                        if (opcion == 4) {
                            System.out.println("Saliendo...");
                        } else {
                            System.out.println("Opción inválida.");
                        }
                    }
                }
            }
            System.out.println("");
        }
    }

    private void ejecutarAgregar() {
        String nombre = input.leerCadena(scanner, "Nombre: ");
        String telefono = input.leerCadena(scanner, "Teléfono: ");
        boolean agregado = service.agregar(nombre, telefono);
        if (agregado) {
            System.out.println("Contacto agregado. Restante: " + service.capacidadRestante());
        } else {
            System.out.println("No se pudo agregar (duplicado o agenda llena).");
        }
    }

    private void ejecutarListar() {
        List<Contacto> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("Sin contactos.");
        } else {
            int i = 0;
            while (i < lista.size()) {
                System.out.println((i + 1) + ". " + lista.get(i));
                i++;
            }
        }
    }

    private void ejecutarBuscar() {
        String nombre = input.leerCadena(scanner, "Nombre a buscar: ");
        Contacto c = service.buscar(nombre);
        if (c == null) {
            System.out.println("No encontrado.");
        } else {
            System.out.println("Encontrado: " + c);
        }
    }
}
