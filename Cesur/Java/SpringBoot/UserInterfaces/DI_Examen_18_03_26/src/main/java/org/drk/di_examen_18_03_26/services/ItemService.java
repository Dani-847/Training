package org.drk.di_examen_18_03_26.services;

import org.drk.di_examen_18_03_26.dto.StoreStatsDTO;
import org.drk.di_examen_18_03_26.entities.Item;
import org.drk.di_examen_18_03_26.exceptions.ItemNotFoundException;
import org.drk.di_examen_18_03_26.repositories.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OJETIVO PRINCIPAL:
 * 1: Listar todos los ítems disponibles en la tienda.
 * 2: Obtener los detalles de un ítem específico por su ID o EAN.
 * 3: Editar un ítem existente, actualizando solo los campos proporcionados.
 * 4: Panel de estadisticas de la tienda, incluyendo:
 *    - Número total de ítems.
 *    - Ítems con stock inferior a un umbral definido (100 por defecto).
 *    - Listado de fabricantes (solo el nombre, sin duplicados).
 */



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
    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    /**
     * Historia de usuario A: Agregar un nuevo ítem.
     * Almacena un nuevo ítem en la tienda.
     *
     * @param item el ítem a guardar
     * @return el ítem guardado
     */
    public Item crear(Item item) {
        return itemRepository.save(item);
    }

    /**
     * Historia de usuario C: Editar un item.
     *
     * <p>Solo se actualizan los campos no nulos del objeto {@code detalles}.</p>
     *
     * @param id       identificador del hotel a actualizar
     * @param detalles objeto Hotel con los nuevos valores
     * @return el hotel actualizado
     * @throws ItemNotFoundException si no existe ningun hotel con ese ID
     */
    //title, price, category, description, rate, count, color, manufacturer, EAN, image
    public Item actualizar(String id, Item detalles) {
        Item existente = buscarPorId(id);
        if (detalles.getTitle() != null)        existente.setTitle(detalles.getTitle());
        if (detalles.getPrice() != null)        existente.setPrice(detalles.getPrice());
        if (detalles.getCategory() != null)     existente.setCategory(detalles.getCategory());
        if (detalles.getDescription() != null)  existente.setDescription(detalles.getDescription());
        if (detalles.getRate() != null)         existente.setRate(detalles.getRate());
        if (detalles.getCount() != null)        existente.setCount(detalles.getCount());
        if (detalles.getColor() != null)        existente.setColor(detalles.getColor());
        if (detalles.getManufacturer() != null) existente.setManufacturer(detalles.getManufacturer());
        if (detalles.getEan() != null)          existente.setEan(detalles.getEan());
        if (detalles.getImage() != null)        existente.setImage(detalles.getImage());
        return itemRepository.save(existente);
    }

    /**
     * Historia de usuario B: Obtener los detalles de un ítem.
     * Funciona con el controller para dar los detalles de un item concreto.
     * Devuelve la información detallada de un ítem específico proporcionando su identificador interno.
     *
     * @param id el identificador interno del ítem
     * @return el ítem encontrado
     * @throws ItemNotFoundException si no existe un ítem con ese id
     */
    public Item buscarPorId(String id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item no encontrado con id: " + id));
    }

    /**
     * Historia de usuario D: Mostrar estadísticas de la tienda.
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


    public record ItemStats(long total, long stock, List<String> fabricantes) {}

    public ItemStats obtenerEstadisticas() {
        long total = itemRepository.count();
        long stock = itemRepository.countByCountLessThan(LOW_STOCK_THRESHOLD);

        List<String> fabricantes = itemRepository.findAll()
                .stream()
                .map(Item::getManufacturer)
                .filter(m -> m != null && !m.isBlank())
                .distinct()
                .collect(Collectors.toList());
        return new ItemStats(total, stock, fabricantes);
    }

}

