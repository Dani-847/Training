package org.example.data;

import lombok.Data;

@Data
public class Juego {
    private Integer id;
    private String nombre;
    private Integer año;
    private String plataforma;
    private String desarrollador;
    private Integer valoracion;
    private String tipo;
    private String descripcion;
    private String imageUrl;
    private String videoUrl;
    private Integer ownerId;
    private String status;
}
