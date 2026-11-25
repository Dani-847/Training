package org.drk;

import org.drk.model.Task;
import org.drk.service.TodoService;

import java.util.List;
import java.util.Scanner;

/**
 * Aplicación principal de la lista de tareas (TodoList) por terminal.
 * <p>
 * NOTA EDUCATIVA: Esta clase contiene defectos intencionales para demostrar
 * cómo SonarQube los detecta. Cada defecto está marcado con un comentario.
 * </p>
 * <p>
 * Funcionalidades:
 * <ul>
 *     <li>Añadir tareas</li>
 *     <li>Listar tareas</li>
 *     <li>Completar tareas</li>
 *     <li>Eliminar tareas</li>
 *     <li>Buscar tareas</li>
 *     <li>Ver resumen</li>
 * </ul>
 * </p>
 *
 *
 * @author DRK
 * @version 1.0
 */
public class Main {

    // ============================================================
    // SONARQUBE CODE SMELL: Números mágicos usados directamente
    // Deberían ser constantes con nombre descriptivo
    // ============================================================

    private static TodoService service = new TodoService();

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no usados)
     */
    public static void main(String[] args) {
        // ============================================================
        // SONARQUBE BUG / CODE SMELL: Scanner nunca se cierra (resource leak)
        // Debería usar try-with-resources
        // ============================================================
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     TODOLIST - Práctica SonarQube    ║");
        System.out.println("║     Gestión de Tareas por Terminal   ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;

        while (running) {
            printMenu();

            // ============================================================
            // SONARQUBE CODE SMELL: Catch vacío que traga excepciones
            // ============================================================
            int option = 0;
            try {
                option = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                // SONARQUBE CODE SMELL: Bloque catch vacío
                // Debería al menos logear el error:
                // System.err.println("Error: " + e.getMessage());
            }

            // ============================================================
            // SONARQUBE CODE SMELL: Números mágicos (1, 2, 3, 4, 5, 6, 7)
            // Deberían ser constantes: OPTION_ADD = 1, OPTION_LIST = 2, etc.
            // ============================================================
            switch (option) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    listTasks();
                    break;
                case 3:
                    completeTask(scanner);
                    break;
                case 4:
                    removeTask(scanner);
                    break;
                case 5:
                    searchTasks(scanner);
                    break;
                case 6:
                    showSummary(scanner);
                    break;
                case 7:
                    running = false;
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intenta de nuevo.");
            }
        }

        // VERSIÓN CORREGIDA: scanner.close();
    }

    /**
     * Muestra el menú principal.
     */
    private static void printMenu() {
        System.out.println("\n┌──────────────────────────────┐");
        System.out.println("│        MENÚ PRINCIPAL        │");
        System.out.println("├──────────────────────────────┤");
        System.out.println("│  1. Añadir tarea             │");
        System.out.println("│  2. Listar tareas            │");
        System.out.println("│  3. Completar tarea          │");
        System.out.println("│  4. Eliminar tarea           │");
        System.out.println("│  5. Buscar tareas            │");
        System.out.println("│  6. Ver resumen              │");
        System.out.println("│  7. Salir                    │");
        System.out.println("└──────────────────────────────┘");
        System.out.print("Selecciona una opción: ");
    }

    /**
     * Permite al usuario añadir una nueva tarea.
     *
     * @param scanner Scanner para leer input del usuario
     */
    private static void addTask(Scanner scanner) {
        System.out.print("\nTítulo de la tarea: ");
        String title = scanner.nextLine();

        System.out.print("Descripción: ");
        String description = scanner.nextLine();

        // ============================================================
        // SONARQUBE BUG: Comparación de Strings con == en vez de .equals()
        // ============================================================
        if (title == "") {
            System.out.println("El título no puede estar vacío.");
            return;
        }
        // VERSIÓN CORREGIDA:
        // if (title.equals("") || title.isBlank()) { ... }

        Task task = new Task(title, description);
        service.addTask(task);
        System.out.println("Tarea añadida correctamente: " + task.getTitle());
    }

    /**
     * Lista todas las tareas existentes.
     */
    private static void listTasks() {
        List<Task> tasks = service.listTasks();

        if (tasks.isEmpty()) {
            System.out.println("\nNo hay tareas registradas.");
            return;
        }

        System.out.println("\nLISTA DE TAREAS:");
        System.out.println("────────────────────────────────────────────────────────────");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("────────────────────────────────────────────────────────────");
        System.out.println("Total: " + tasks.size() + " tarea(s)");
    }

    /**
     * Permite completar una tarea por su ID.
     *
     * @param scanner Scanner para leer input
     */
    private static void completeTask(Scanner scanner) {
        listTasks();
        System.out.print("\nID de la tarea a completar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (service.completeTask(id)) {
                System.out.println("Tarea completada correctamente.");
            } else {
                System.out.println("No se encontró la tarea con ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no válido.");
        }
    }

    /**
     * Permite eliminar una tarea por su ID.
     *
     * @param scanner Scanner para leer input
     */
    private static void removeTask(Scanner scanner) {
        listTasks();
        System.out.print("\nID de la tarea a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (service.removeTask(id)) {
                System.out.println("Tarea eliminada correctamente.");
            } else {
                System.out.println("No se encontró la tarea con ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no válido.");
        }
    }

    /**
     * Busca tareas por palabra clave.
     *
     * @param scanner Scanner para leer input
     */
    private static void searchTasks(Scanner scanner) {
        System.out.print("\nPalabra clave a buscar: ");
        String keyword = scanner.nextLine();

        System.out.println("\n--- Resultados por título ---");
        List<Task> byTitle = service.findByTitle(keyword);
        if (byTitle.isEmpty()) {
            System.out.println("No se encontraron coincidencias en títulos.");
        } else {
            byTitle.forEach(t -> System.out.println(t.toString()));
        }

        System.out.println("\n--- Resultados por descripción ---");
        List<Task> byDesc = service.findByDescription(keyword);
        if (byDesc.isEmpty()) {
            System.out.println("No se encontraron coincidencias en descripciones.");
        } else {
            byDesc.forEach(t -> System.out.println(t.toString()));
        }
    }

    /**
     * Muestra un resumen de las tareas.
     *
     * @param scanner Scanner para leer input
     */
    private static void showSummary(Scanner scanner) {
        System.out.println("\nRESUMEN:");
        System.out.println("Selecciona tipo de resumen:");
        System.out.println("  1. Total de tareas");
        System.out.println("  2. Tareas completadas");
        System.out.println("  3. Tareas pendientes");
        System.out.println("  4. Completadas / Total");
        System.out.println("  5. Porcentaje de progreso");
        System.out.print("Opción: ");

        try {
            int type = Integer.parseInt(scanner.nextLine().trim());
            String summary = service.getSummary(type);
            System.out.println("\n" + summary);
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida.");
        }
    }
}