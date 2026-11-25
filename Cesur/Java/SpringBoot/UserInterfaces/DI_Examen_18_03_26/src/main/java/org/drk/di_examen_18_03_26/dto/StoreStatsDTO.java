package org.drk.di_examen_18_03_26.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * DTO que representa las estadísticas de la tienda.
 * Historia de usuario F: Mostrar estadísticas de la tienda.
 */
@AllArgsConstructor
@Getter
public class StoreStatsDTO {

    /** Número total de ítems almacenados. */
    private long totalItems;

    /** Ítems con stock inferior a la constante definida (100 por defecto). */
    private List<String> lowStockItems;

    /** Listado de fabricantes (solo el nombre, sin duplicados). */
    private List<String> manufacturers;
}

