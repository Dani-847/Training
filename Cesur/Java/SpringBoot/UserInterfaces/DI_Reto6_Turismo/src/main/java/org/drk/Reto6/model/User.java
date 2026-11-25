package org.drk.Reto6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de usuario para la autenticacion en la aplicacion web.
 *
 * <p>Representa un usuario del sistema con sus credenciales y rol.
 * Los usuarios se gestionan en memoria a traves de {@code SecurityConfig};
 * este modelo sirve como representacion del concepto de usuario autenticado.</p>
 *
 * <p>Roles disponibles:</p>
 * <ul>
 *   <li>{@code ADMIN} - puede crear, editar y eliminar hoteles</li>
 *   <li>{@code USER} - solo puede consultar hoteles</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Nombre de usuario utilizado para iniciar sesion.
     */
    private String username;

    /**
     * Contrasena cifrada del usuario.
     */
    private String password;

    /**
     * Rol del usuario dentro de la aplicacion (ADMIN o USER).
     */
    private String role;
}
