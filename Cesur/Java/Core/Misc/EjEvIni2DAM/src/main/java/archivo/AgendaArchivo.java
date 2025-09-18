package archivo;

import modelo.Contacto;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaArchivo {

    private final Path archivo;
    private final int capacidadMaxima;
    private final List<Contacto> contactos;
    private final String nombreArchivo = "contactos.txt";

    public AgendaArchivo(int capacidadMaxima) {
        this.archivo = Paths.get(nombreArchivo);
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
        inicializarArchivo();
        cargarDesdeArchivo();
    }

    private void inicializarArchivo() {
        boolean existe = Files.exists(archivo);
        if (!existe) {
            try {
                Files.createFile(archivo);
            } catch (IOException e) {
                System.err.println("No se pudo crear el archivo: " + e.getMessage());
            }
        }
    }

    private void cargarDesdeArchivo() {
        List<String> lineas;
        try {
            lineas = Files.readAllLines(archivo);
        } catch (IOException e) {
            lineas = new ArrayList<>();
            System.err.println("Error leyendo archivo: " + e.getMessage());
        }
        contactos.clear();
        int i = 0;
        while (i < lineas.size()) {
            String linea = lineas.get(i).trim();
            if (!linea.isEmpty()) {
                String[] partes = linea.split(";", 2);
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    String telefono = partes[1].trim();
                    if (!nombre.isEmpty() && !telefono.isEmpty()) {
                        contactos.add(new Contacto(nombre, telefono));
                    }
                }
            }
            i++;
        }
    }

    private void guardarArchivo() {
        List<String> lineas = new ArrayList<>();
        int i = 0;
        while (i < contactos.size()) {
            Contacto c = contactos.get(i);
            lineas.add(c.getNombre() + ";" + c.getTelefono());
            i++;
        }
        try {
            Files.write(archivo, lineas, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.err.println("Error guardando archivo: " + e.getMessage());
        }
    }

    public boolean agregarContacto(Contacto contacto) {
        boolean agregado = false;
        boolean existe = false;
        int i = 0;
        while (i < contactos.size() && !existe) {
            Contacto c = contactos.get(i);
            if (c.getNombre().equalsIgnoreCase(contacto.getNombre())) {
                existe = true;
            }
            i++;
        }
        if (!existe) {
            if (contactos.size() < capacidadMaxima) {
                contactos.add(contacto);
                agregado = true;
                guardarArchivo();
            }
        }
        return agregado;
    }

    public Contacto buscarPorNombre(String nombre) {
        Contacto encontrado = null;
        boolean hallado = false;
        int i = 0;
        while (i < contactos.size() && !hallado) {
            Contacto c = contactos.get(i);
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = c;
                hallado = true;
            }
            i++;
        }
        return encontrado;
    }

    public List<Contacto> listar() {
        List<Contacto> copia = new ArrayList<>();
        int i = 0;
        while (i < contactos.size()) {
            copia.add(contactos.get(i));
            i++;
        }
        return copia;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public int getCantidad() {
        return contactos.size();
    }
}
