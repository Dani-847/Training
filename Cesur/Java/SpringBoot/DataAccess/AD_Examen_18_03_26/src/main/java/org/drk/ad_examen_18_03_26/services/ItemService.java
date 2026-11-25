package org.drk.ad_examen_18_03_26.services;

import org.drk.ad_examen_18_03_26.dto.StoreStatsDTO;
import org.drk.ad_examen_18_03_26.entities.Item;
import org.drk.ad_examen_18_03_26.exceptions.ItemNotFoundException;
import org.drk.ad_examen_18_03_26.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que contiene la lógica de negocio para la gestión de ítems de la tienda.
 */
@Service
public class ItemService {

    /** Constante que define el umbral de stock bajo (100 por defecto). */
    private static final int LOW_STOCK_THRESHOLD = 100;

    private final ItemRepository itemRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param itemRepository repositorio de ítems
     */
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Obtiene todos los ítems de la tienda.
     *
     * @return lista con todos los ítems
     */
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    /**
     * Historia de usuario A: Agregar un nuevo ítem.
     * Almacena un nuevo ítem en la tienda.
     *
     * @param item el ítem a guardar
     * @return el ítem guardado
     */
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * Historia de usuario B: Eliminar un ítem.
     * Elimina un ítem concreto de la tienda por su identificador interno.
     *
     * @param id el identificador interno del ítem a eliminar
     * @throws ItemNotFoundException si no existe un ítem con ese id
     */
    public void deleteItem(Integer id) {
        if (!itemRepository.existsByItemId(id)) {
            throw new ItemNotFoundException("Item no encontrado con id: " + id);
        }
        itemRepository.deleteByItemId(id);
    }

    /**
     * Historia de usuario C: Obtener los detalles de un ítem.
     * Devuelve la información detallada de un ítem específico proporcionando su identificador interno.
     *
     * @param id el identificador interno del ítem
     * @return el ítem encontrado
     * @throws ItemNotFoundException si no existe un ítem con ese id
     */
    public Item findByItemId(Integer id) {
        return itemRepository.findByItemId(id)
                .orElseThrow(() -> new ItemNotFoundException("Item no encontrado con id: " + id));
    }

    /**
     * Historia de usuario D: Listar ítems por categoría.
     * Devuelve todos los ítems de una categoría específica.
     *
     * @param category la categoría a filtrar
     * @return lista de ítems de esa categoría
     */
    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    /**
     * Historia de usuario E: Cambiar categoría.
     * Cambia el texto de categoría a uno nuevo para todos los ítems que pertenezcan a dicha categoría.
     *
     * @param oldCategory la categoría actual
     * @param newCategory la nueva categoría
     * @return número de ítems actualizados
     */
    public long changeCategory(String oldCategory, String newCategory) {
        List<Item> items = itemRepository.findByCategory(oldCategory);
        items.forEach(item -> item.setCategory(newCategory));
        itemRepository.saveAll(items);
        return items.size();
    }

    /**
     * Historia de usuario F: Mostrar estadísticas de la tienda.
     * Devuelve un resumen estadístico que incluye:
     * a) Número total de ítems almacenados.
     * b) Ítems con stock inferior a LOW_STOCK_THRESHOLD (100 por defecto).
     * c) Listado de fabricantes (solo el nombre, sin duplicados).
     *
     * @return DTO con las estadísticas de la tienda
     */
    public StoreStatsDTO getStoreStats() {
        long totalItems = itemRepository.count();

        List<String> lowStockItems = itemRepository.findByCountLessThan(LOW_STOCK_THRESHOLD)
                .stream()
                .map(Item::getTitle)
                .collect(Collectors.toList());

        List<String> manufacturers = itemRepository.findAll()
                .stream()
                .map(Item::getManufacturer)
                .filter(m -> m != null && !m.isBlank())
                .distinct()
                .collect(Collectors.toList());

        return new StoreStatsDTO(totalItems, lowStockItems, manufacturers);
    }
}

