package org.example;

public class VideoJuego {
    private int id;
    private String titulo;
    private double precio;

    public VideoJuego(int id, String titulo, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
    }

    public VideoJuego() {

    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return "VideoJuego{id=" + id + ", titulo='" + titulo + "', precio=" + precio + "}";
    }

    public void setId(int anInt) {
        this.id = anInt;
    }
}
