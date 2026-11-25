package modelo;

import java.util.Objects;

public class Contacto {
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        boolean esIgual = false;
        if (this == o) {
            esIgual = true;
        } else {
            if (o instanceof Contacto otro) {
                esIgual = nombre != null && nombre.equalsIgnoreCase(otro.nombre);
            }
        }
        return esIgual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre == null ? null : nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return nombre + " - " + telefono;
    }
}
