package org.drk.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Clase que representa una tarea en la lista de tareas.
 * <p>
 * NOTA EDUCATIVA: Esta clase contiene defectos intencionales para demostrar
 * cómo SonarQube los detecta. Cada defecto está marcado con un comentario
 * que indica su tipo.
 * </p>
 *
 * @author DRK
 * @version 1.0
 */
public class Task implements Serializable {

    private static int counter = 0;

    // ============================================================
    // SONARQUBE BUG: Campo público que debería ser privado con getter
    // SonarQube reportará: "Make this member 'protected' or 'private'"
    // ============================================================
    public int id;

    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;

    // ============================================================
    // SONARQUBE VULNERABILITY / SECURITY HOTSPOT: Contraseña hardcodeada
    // SonarQube reportará: "Hard-coded credentials are security-sensitive"
    // ============================================================
    private String password = "admin123";

    /**
     * Constructor de la tarea.
     *
     * @param title       Título de la tarea
     * @param description Descripción de la tarea
     */
    public Task(String title, String description) {
        this.id = ++counter;
        this.title = title;
        this.description = description;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Constructor vacío.
     */
    public Task() {
        this.id = ++counter;
        this.createdAt = LocalDateTime.now();
    }

    // ============================================================
    // SONARQUBE BUG: Se sobreescribe equals() pero NO hashCode()
    // SonarQube reportará: "Override 'hashCode()' when overriding 'equals()'"
    // ============================================================
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        // SONARQUBE CODE SMELL: Usar getClass() vs instanceof
        if (getClass() != obj.getClass()) return false;
        Task other = (Task) obj;
        return this.id == other.id;
    }

    // VERSIÓN CORREGIDA (descomentarla para corregir el bug):
    // @Override
    // public int hashCode() {
    //     return Objects.hash(id);
    // }

    /**
     * Representación en texto de la tarea.
     *
     * @return String con la información de la tarea
     */
    @Override
    public String toString() {
        // ============================================================
        // SONARQUBE CODE SMELL: Concatenación de Strings en vez de StringBuilder
        // SonarQube reportará: "Use StringBuilder instead of concatenation"
        // ============================================================
        String result = "";
        result = result + "[" + (completed ? "✓" : "✗") + "] ";
        result = result + "ID: " + id + " | ";
        result = result + "Título: " + title + " | ";
        result = result + "Descripción: " + description + " | ";
        result = result + "Creada: " + createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return result;
    }

    // ============================================================
    // SONARQUBE DUPLICATION: Método duplicado que hace lo mismo que toString()
    // SonarQube reportará duplicación de código
    // ============================================================
    /**
     * Obtiene información de la tarea (DUPLICADO de toString).
     *
     * @return String con la información de la tarea
     */
    public String getInfo() {
        String result = "";
        result = result + "[" + (completed ? "✓" : "✗") + "] ";
        result = result + "ID: " + id + " | ";
        result = result + "Título: " + title + " | ";
        result = result + "Descripción: " + description + " | ";
        result = result + "Creada: " + createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return result;
    }

    // ============================================================
    // SONARQUBE CODE SMELL: Método con complejidad cognitiva alta
    // ============================================================
    /**
     * Valida la tarea según múltiples criterios.
     *
     * @return true si la tarea es válida
     */
    public boolean isValid() {
        if (title == null) {
            return false;
        } else if (title.isEmpty()) {
            return false;
        } else if (title.length() > 100) {
            return false;
        } else if (title.contains("  ")) {
            return false;
        } else if (description == null) {
            return false;
        } else if (description.isEmpty()) {
            return false;
        } else if (description.length() > 500) {
            return false;
        } else if (id <= 0) {
            return false;
        } else if (createdAt == null) {
            return false;
        } else if (createdAt.isAfter(LocalDateTime.now())) {
            return false;
        }
        return true;
    }

    // VERSIÓN CORREGIDA (más limpia, sin complejidad excesiva):
    // public boolean isValid() {
    //     return title != null && !title.isEmpty() && title.length() <= 100
    //             && !title.contains("  ")
    //             && description != null && !description.isEmpty()
    //             && description.length() <= 500
    //             && id > 0
    //             && createdAt != null && !createdAt.isAfter(LocalDateTime.now());
    // }

    // --- Getters y Setters ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public String getPassword() { return password; }

    /**
     * Resetea el contador de IDs (útil para tests).
     */
    public static void resetCounter() { counter = 0; }
}

