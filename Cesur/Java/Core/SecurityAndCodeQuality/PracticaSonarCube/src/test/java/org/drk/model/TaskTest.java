package org.drk.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase Task.
 * <p>
 * NOTA: Algunos métodos de Task se dejan SIN testear a propósito
 * para que SonarQube reporte cobertura incompleta.
 * </p>
 */
class TaskTest {

    @BeforeEach
    void setUp() {
        Task.resetCounter();
    }

    @Test
    @DisplayName("Crear tarea con título y descripción")
    void testCreateTask() {
        Task task = new Task("Comprar leche", "Ir al supermercado");
        assertEquals("Comprar leche", task.getTitle());
        assertEquals("Ir al supermercado", task.getDescription());
        assertFalse(task.isCompleted());
        assertNotNull(task.getCreatedAt());
    }

    @Test
    @DisplayName("El ID se autoincrementa")
    void testAutoIncrementId() {
        Task t1 = new Task("Tarea 1", "Desc 1");
        Task t2 = new Task("Tarea 2", "Desc 2");
        assertEquals(1, t1.getId());
        assertEquals(2, t2.getId());
    }

    @Test
    @DisplayName("Marcar tarea como completada")
    void testCompleteTask() {
        Task task = new Task("Test", "Test desc");
        assertFalse(task.isCompleted());
        task.setCompleted(true);
        assertTrue(task.isCompleted());
    }

    @Test
    @DisplayName("Equals compara por ID")
    void testEquals() {
        Task t1 = new Task("Tarea A", "Desc A");
        Task t2 = new Task("Tarea B", "Desc B");
        assertNotEquals(t1, t2);

        // Mismo ID -> iguales
        t2.setId(t1.getId());
        assertEquals(t1, t2);
    }

    @Test
    @DisplayName("Equals con null y otro tipo")
    void testEqualsEdgeCases() {
        Task task = new Task("Test", "Desc");
        assertNotEquals(null, task);
        assertNotEquals("string", task);
        assertEquals(task, task);
    }

    @Test
    @DisplayName("toString devuelve información formateada")
    void testToString() {
        Task task = new Task("Mi tarea", "Descripción de ejemplo");
        String result = task.toString();
        assertTrue(result.contains("Mi tarea"));
        assertTrue(result.contains("Descripción de ejemplo"));
        assertTrue(result.contains("✗")); // No completada
    }

    @Test
    @DisplayName("toString de tarea completada muestra checkmark")
    void testToStringCompleted() {
        Task task = new Task("Tarea hecha", "Ya terminada");
        task.setCompleted(true);
        String result = task.toString();
        assertTrue(result.contains("✓"));
    }

    @Test
    @DisplayName("Setters funcionan correctamente")
    void testSetters() {
        Task task = new Task();
        task.setTitle("Nuevo título");
        task.setDescription("Nueva descripción");
        task.setId(99);
        assertEquals("Nuevo título", task.getTitle());
        assertEquals("Nueva descripción", task.getDescription());
        assertEquals(99, task.getId());
    }

    @Test
    @DisplayName("isValid retorna true para tarea válida")
    void testIsValid() {
        Task task = new Task("Título válido", "Descripción válida");
        assertTrue(task.isValid());
    }

    @Test
    @DisplayName("isValid retorna false para título null")
    void testIsValidNullTitle() {
        Task task = new Task(null, "Descripción");
        assertFalse(task.isValid());
    }

    @Test
    @DisplayName("isValid retorna false para título vacío")
    void testIsValidEmptyTitle() {
        Task task = new Task("", "Descripción");
        assertFalse(task.isValid());
    }

    @Test
    @DisplayName("isValid retorna false para descripción null")
    void testIsValidNullDescription() {
        Task task = new Task("Título", null);
        assertFalse(task.isValid());
    }

    // NOTA: No se testea getInfo() ni getPassword() a propósito
    // para que SonarQube reporte cobertura incompleta
}

