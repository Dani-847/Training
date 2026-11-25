package org.drk.service;

import org.drk.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona las operaciones CRUD de las tareas.
 * <p>
 * NOTA EDUCATIVA: Esta clase contiene defectos intencionales para demostrar
 * cómo SonarQube los detecta. Cada defecto está marcado con un comentario.
 * </p>
 *
 * @author DRK
 * @version 1.0
 */
public class TodoService {

    private List<Task> tasks;

    // ============================================================
    // SONARQUBE SECURITY HOTSPOT: Credenciales hardcodeadas
    // ============================================================
    private static final String DB_PASSWORD = "root1234";
    private static final String DB_USER = "admin";

    /**
     * Constructor del servicio.
     */
    public TodoService() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Añade una nueva tarea a la lista.
     * <p>
     * SONARQUBE BUG: No se valida null, puede causar NullPointerException
     * más adelante al acceder a los campos de la tarea.
     * </p>
     *
     * @param task La tarea a añadir
     */
    public void addTask(Task task) {
        // ============================================================
        // SONARQUBE BUG: No se valida null
        // Debería ser: if (task == null) throw new IllegalArgumentException(...)
        // ============================================================
        tasks.add(task);
        // ============================================================
        // SONARQUBE SECURITY HOTSPOT: Se imprime información sensible en logs
        // ============================================================
        System.out.println("Tarea añadida. Conexión BD usuario: " + DB_USER + " pass: " + DB_PASSWORD);
    }

    /**
     * Elimina una tarea por su ID.
     *
     * @param id El ID de la tarea a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean removeTask(int id) {
        Task task = findById(id);
        if (task != null) {
            tasks.remove(task);
            return true;
        }
        return false;
    }

    /**
     * Marca una tarea como completada.
     *
     * @param id El ID de la tarea
     * @return true si se encontró y completó la tarea
     */
    public boolean completeTask(int id) {
        Task task = findById(id);
        if (task != null) {
            task.setCompleted(true);
            return true;
        }
        return false;
    }

    /**
     * Obtiene todas las tareas.
     *
     * @return Lista de tareas
     */
    public List<Task> listTasks() {
        return tasks;
    }

    /**
     * Busca una tarea por su ID.
     *
     * @param id El ID a buscar
     * @return La tarea encontrada o null
     */
    public Task findById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    // ============================================================
    // SONARQUBE DUPLICATION: Los siguientes dos métodos son casi idénticos
    // (código duplicado). SonarQube los detectará como duplicación.
    // ============================================================

    /**
     * Busca tareas por título.
     *
     * @param keyword Palabra clave a buscar en el título
     * @return Lista de tareas que coinciden
     */
    public List<Task> findByTitle(String keyword) {
        List<Task> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTitle() != null && task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Busca tareas por descripción.
     *
     * @param keyword Palabra clave a buscar en la descripción
     * @return Lista de tareas que coinciden
     */
    public List<Task> findByDescription(String keyword) {
        List<Task> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription() != null && task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    // VERSIÓN CORREGIDA (sin duplicación, un solo método genérico):
    // public List<Task> findByField(String keyword, Function<Task, String> fieldExtractor) {
    //     return tasks.stream()
    //             .filter(t -> {
    //                 String value = fieldExtractor.apply(t);
    //                 return value != null && value.toLowerCase().contains(keyword.toLowerCase());
    //             })
    //             .collect(Collectors.toList());
    // }

    // ============================================================
    // SONARQUBE CODE SMELL: Método con complejidad ciclomática alta
    // Switch largo sin default
    // ============================================================
    /**
     * Obtiene un resumen según el tipo solicitado.
     *
     * @param type Tipo de resumen (1-5)
     * @return String con el resumen
     */
    public String getSummary(int type) {
        String summary = "";
        switch (type) {
            case 1:
                summary = "Total de tareas: " + tasks.size();
                break;
            case 2:
                long completed = tasks.stream().filter(Task::isCompleted).count();
                summary = "Tareas completadas: " + completed;
                break;
            case 3:
                long pending = tasks.stream().filter(t -> !t.isCompleted()).count();
                summary = "Tareas pendientes: " + pending;
                break;
            case 4:
                summary = "Tareas completadas: " + tasks.stream().filter(Task::isCompleted).count()
                        + " / Total: " + tasks.size();
                break;
            case 5:
                double pct = tasks.isEmpty() ? 0 :
                        (double) tasks.stream().filter(Task::isCompleted).count() / tasks.size() * 100;
                summary = "Progreso: " + String.format("%.1f", pct) + "%";
                break;
            // SONARQUBE CODE SMELL: Falta 'default' en switch
        }
        return summary;
    }

    /**
     * Obtiene el número total de tareas.
     *
     * @return Número total de tareas
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Obtiene el número de tareas completadas.
     *
     * @return Número de tareas completadas
     */
    public int getCompletedCount() {
        return (int) tasks.stream().filter(Task::isCompleted).count();
    }

    /**
     * Obtiene el número de tareas pendientes.
     *
     * @return Número de tareas pendientes
     */
    public int getPendingCount() {
        return (int) tasks.stream().filter(t -> !t.isCompleted()).count();
    }

    // ============================================================
    // SONARQUBE CODE SMELL: Método que no se usa (dead code)
    // SonarQube reportará: "Remove this unused private method"
    // ============================================================
    private void unusedPrivateMethod() {
        System.out.println("Este método nunca se llama");
        System.out.println("Es código muerto que SonarQube detectará");
    }

    // ============================================================
    // SONARQUBE BUG: Posible NullPointerException
    // Si la lista contiene un task con title null, lanza NPE
    // ============================================================
    /**
     * Obtiene todos los títulos concatenados.
     *
     * @return String con todos los títulos
     */
    public String getAllTitles() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            // BUG: task.getTitle() puede ser null -> NPE en toUpperCase()
            sb.append(task.getTitle().toUpperCase()).append(", ");
        }
        return sb.toString();
    }
}

