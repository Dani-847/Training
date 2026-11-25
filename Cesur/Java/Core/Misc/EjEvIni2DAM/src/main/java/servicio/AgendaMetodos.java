package servicio;

import modelo.Contacto;
import archivo.AgendaArchivo;

import java.util.List;

public class AgendaMetodos {

    private final AgendaArchivo archivo;

    public AgendaMetodos(int capacidad) {
        this.archivo = new AgendaArchivo(capacidad);
    }

    public boolean agregar(String nombre, String telefono) {
        Contacto nuevo = new Contacto(nombre, telefono);
        boolean ok = archivo.agregarContacto(nuevo);
        return ok;
    }

    public Contacto buscar(String nombre) {
        Contacto c = archivo.buscarPorNombre(nombre);
        return c;
    }

    public List<Contacto> listar() {
        List<Contacto> lista = archivo.listar();
        return lista;
    }

    public int capacidadRestante() {
        int restante = archivo.getCapacidadMaxima() - archivo.getCantidad();
        return restante;
    }
}
