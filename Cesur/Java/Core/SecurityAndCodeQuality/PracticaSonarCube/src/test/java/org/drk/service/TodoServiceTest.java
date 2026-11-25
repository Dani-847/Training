package org.drk.service;

import org.drk.model.Task;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase TodoService.
 * <p>
 * NOTA: Algunos métodos se dejan SIN testear a propósito
 * para que SonarQube reporte cobertura incompleta.
 * </p>
 */
class TodoServiceTest {

    private TodoService service;

    @BeforeEach
    void setUp() {
        Task.resetCounter();
        service = new TodoService();
    }

    // ===================== TESTS DE addTask =====================

    @Test
    @DisplayName("Añadir una tarea incrementa el tamaño de la lista")
    void testAddTask() {
        Task task = new Task("Estudiar Java", "Repasar colecciones");
        service.addTask(task);
        assertEquals(1, service.getTaskCount());
    }

    @Test
    @DisplayName("Añadir múltiples tareas")
    void testAddMultipleTasks() {
        service.addTask(new Task("Tarea 1", "Desc 1"));
        service.addTask(new Task("Tarea 2", "Desc 2"));
        service.addTask(new Task("Tarea 3", "Desc 3"));
        assertEquals(3, service.getTaskCount());
    }

    @Test
    @DisplayName("Añadir null no lanza excepción (BUG intencional)")
    void testAddNullTask() {
        // Este test documenta el BUG: se puede añadir null
        service.addTask(null);
        assertEquals(1, service.getTaskCount());
    }

    // ===================== TESTS DE removeTask =====================

    @Test
    @DisplayName("Eliminar tarea existente retorna true")
    void testRemoveExistingTask() {
        Task task = new Task("Borrar esto", "Será eliminada");
        service.addTask(task);
        assertTrue(service.removeTask(task.getId()));
        assertEquals(0, service.getTaskCount());
    }

    @Test
    @DisplayName("Eliminar tarea inexistente retorna false")
    void testRemoveNonExistingTask() {
        assertFalse(service.removeTask(999));
    }

    // ===================== TESTS DE completeTask =====================

    @Test
    @DisplayName("Completar tarea existente retorna true")
    void testCompleteExistingTask() {
        Task task = new Task("Completar esto", "Se completará");
        service.addTask(task);
        assertTrue(service.completeTask(task.getId()));
    }

    @Test
    @DisplayName("La tarea completada tiene completed = true")
    void testCompletedTaskStatus() {
        Task task = new Task("Tarea", "Desc");
        service.addTask(task);
        service.completeTask(task.getId());

        Task found = service.findById(task.getId());
        assertNotNull(found);
        assertTrue(found.isCompleted());
    }

    @Test
    @DisplayName("Completar tarea inexistente retorna false")
    void testCompleteNonExistingTask() {
        assertFalse(service.completeTask(999));
    }

    // ===================== TESTS DE findById =====================

    @Test
    @DisplayName("Buscar tarea por ID existente")
    void testFindByIdExisting() {
        Task task = new Task("Buscar", "Búsqueda");
        service.addTask(task);
        Task found = service.findById(task.getId());
        assertNotNull(found);
        assertEquals(task.getTitle(), found.getTitle());
    }

    @Test
    @DisplayName("Buscar tarea por ID inexistente retorna null")
    void testFindByIdNonExisting() {
        assertNull(service.findById(999));
    }

    // ===================== TESTS DE listTasks =====================

    @Test
    @DisplayName("Lista vacía al inicio")
    void testEmptyList() {
        assertTrue(service.listTasks().isEmpty());
    }

    @Test
    @DisplayName("Lista contiene las tareas añadidas")
    void testListContainsTasks() {
        service.addTask(new Task("Tarea A", "Desc A"));
        service.addTask(new Task("Tarea B", "Desc B"));
        List<Task> tasks = service.listTasks();
        assertEquals(2, tasks.size());
    }

    // ===================== TESTS DE findByTitle =====================

    @Test
    @DisplayName("Buscar por título encuentra coincidencias")
    void testFindByTitle() {
        service.addTask(new Task("Comprar pan", "En la panadería"));
        service.addTask(new Task("Comprar leche", "En el super"));
        service.addTask(new Task("Estudiar", "Para el examen"));

        List<Task> results = service.findByTitle("Comprar");
        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("Buscar por título sin coincidencias devuelve lista vacía")
    void testFindByTitleNoResults() {
        service.addTask(new Task("Tarea", "Desc"));
        List<Task> results = service.findByTitle("inexistente");
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Búsqueda por título es case-insensitive")
    void testFindByTitleCaseInsensitive() {
        service.addTask(new Task("COMPRAR PAN", "Desc"));
        List<Task> results = service.findByTitle("comprar");
        assertEquals(1, results.size());
    }

    // ===================== TESTS DE findByDescription =====================

    @Test
    @DisplayName("Buscar por descripción encuentra coincidencias")
    void testFindByDescription() {
        service.addTask(new Task("Tarea 1", "Ir al supermercado"));
        service.addTask(new Task("Tarea 2", "Ir al gimnasio"));
        service.addTask(new Task("Tarea 3", "Estudiar en casa"));

        List<Task> results = service.findByDescription("Ir al");
        assertEquals(2, results.size());
    }

    // ===================== TESTS DE contadores =====================

    @Test
    @DisplayName("Contador de completadas")
    void testCompletedCount() {
        service.addTask(new Task("T1", "D1"));
        service.addTask(new Task("T2", "D2"));
        service.addTask(new Task("T3", "D3"));

        service.completeTask(1);
        service.completeTask(2);

        assertEquals(2, service.getCompletedCount());
        assertEquals(1, service.getPendingCount());
    }

    // ===================== TESTS DE getSummary =====================

    @Test
    @DisplayName("Resumen tipo 1: Total de tareas")
    void testSummaryTotal() {
        service.addTask(new Task("T1", "D1"));
        service.addTask(new Task("T2", "D2"));
        String summary = service.getSummary(1);
        assertTrue(summary.contains("2"));
    }

    @Test
    @DisplayName("Resumen tipo 5: Porcentaje de progreso")
    void testSummaryProgress() {
        service.addTask(new Task("T1", "D1"));
        service.addTask(new Task("T2", "D2"));
        service.completeTask(1);
        String summary = service.getSummary(5);
        assertTrue(summary.contains("50"));
    }

    @Test
    @DisplayName("Resumen tipo 5: 0% con lista vacía")
    void testSummaryProgressEmpty() {
        String summary = service.getSummary(5);
        assertTrue(summary.contains("0"));
    }

    // NOTA: No se testea getAllTitles() ni getSummary() con tipos 2,3,4
    // a propósito para que SonarQube reporte cobertura incompleta
}

