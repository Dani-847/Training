package org.drk.ad_examen_18_03_26.controller;

import org.drk.ad_examen_18_03_26.dto.StoreStatsDTO;
import org.drk.ad_examen_18_03_26.entities.Item;
import org.drk.ad_examen_18_03_26.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de ítems de la tienda.
 * Expone los endpoints que resuelven las historias de usuario.
 */
@RestController
@RequestMapping("/api")
class ItemController {

    private final ItemService itemService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param itemService servicio de ítems
     */
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Obtiene todos los ítems de la tienda.
     * GET /api/items
     *
     * @return lista de todos los ítems
     */
    @GetMapping("/getItems")
    public List<Item> findAll() {
        return itemService.findAll();
    }

    /**
     * Historia de usuario A: Agregar un nuevo ítem.
     * POST /api/items
     * Operación de escritura protegida con seguridad básica (rol ADMIN).
     *
     * @param item el ítem a agregar (cuerpo de la petición en JSON)
     * @return el ítem creado con código 201 (Created)
     */
    @PostMapping("/addItem")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item savedItem = itemService.addItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    /**
     * Historia de usuario B: Eliminar un ítem.
     * DELETE /api/items/{id}
     * Operación de escritura protegida con seguridad básica (rol ADMIN).
     *
     * @param id el identificador interno del ítem a eliminar
     * @return código 204 (No Content) si se eliminó correctamente
     */
    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Historia de usuario C: Obtener los detalles de un ítem.
     * GET /api/items/{id}
     *
     * @param id el identificador interno del ítem
     * @return el ítem encontrado con código 200 (OK)
     */
    @GetMapping("/detailsItem/{id}")
    public ResponseEntity<Item> findById(@PathVariable Integer id) {
        Item item = itemService.findByItemId(id);
        return ResponseEntity.ok(item);
    }

    /**
     * Historia de usuario D: Listar ítems por categoría.
     * GET /api/items/category/{category}
     *
     * @param category la categoría a buscar
     * @return lista de ítems de esa categoría con código 200 (OK)
     */
    @GetMapping("/items/listByCategory/{category}")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable String category) {
        List<Item> items = itemService.getItemsByCategory(category);
        return ResponseEntity.ok(items);
    }

    /**
     * Historia de usuario E: Cambiar categoría.
     * PUT /api/items/category
     * Operación de escritura protegida con seguridad básica (rol ADMIN).
     *
     * @param body mapa con las claves "oldCategory" y "newCategory"
     * @return mensaje indicando cuántos ítems se actualizaron, con código 200 (OK)
     */
    @PutMapping("/modifyCategory")
    public ResponseEntity<Map<String, Object>> changeCategory(@RequestBody Map<String, String> body) {
        String oldCategory = body.get("oldCategory");
        String newCategory = body.get("newCategory");
        long updated = itemService.changeCategory(oldCategory, newCategory);
        return ResponseEntity.ok(Map.of(
                "message", "Categoría actualizada correctamente",
                "itemsUpdated", updated
        ));
    }

    /**
     * Historia de usuario F: Mostrar estadísticas de la tienda.
     * GET /api/items/stats
     * Devuelve un resumen estadístico con:
     * a) Número total de ítems almacenados.
     * b) Ítems con stock inferior a 100.
     * c) Listado de fabricantes.
     *
     * @return DTO con las estadísticas de la tienda, con código 200 (OK)
     */
    @GetMapping("/stats")
    public ResponseEntity<StoreStatsDTO> getStoreStats() {
        StoreStatsDTO stats = itemService.getStoreStats();
        return ResponseEntity.ok(stats);
    }
}

