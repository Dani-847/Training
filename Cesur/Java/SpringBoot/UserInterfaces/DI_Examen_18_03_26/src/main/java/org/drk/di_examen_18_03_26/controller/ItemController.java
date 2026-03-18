package org.drk.di_examen_18_03_26.controller;

import org.drk.di_examen_18_03_26.entities.Item;
import org.drk.di_examen_18_03_26.exceptions.ItemNotFoundException;
import org.drk.di_examen_18_03_26.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
 * Controlador MVC principal de la aplicacion web de store.
 *
 * <p>Gestiona las rutas web y renderiza las vistas Thymeleaf para la consulta
 * y gestion de items. No consume la API REST; accede directamente a la base
 * de datos a traves de {@link ItemService}.</p>
 *
 * <p>Rutas publicas (cualquier visitante):</p>
 * <ul>
 *   <li>{@code GET /} - redirige al listado de items</li>
 *   <li>{@code GET /items} - listado con filtros opcionales</li>
 *   <li>{@code GET /items/{id}} - detalle de un item</li>
 * </ul>
 *
 * <p>Rutas protegidas (requieren rol ADMIN):</p>
 * <ul>
 *   <li>{@code GET /items/nuevo} - formulario de creacion</li>
 *   <li>{@code POST /items/nuevo} - guardar nuevo item</li>
 *   <li>{@code GET /items/{id}/editar} - formulario de edicion</li>
 *   <li>{@code POST /items/{id}/editar} - guardar cambios</li>
 *   <li>{@code POST /items/{id}/eliminar} - eliminar item</li>
 * </ul>
 */
@Controller
class ItemController {

    private final ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Obtiene todos los ítems de la tienda.
     * GET /items
     *
     * @return lista de todos los ítems
     */
    @GetMapping("/")
    public String inicio() {
        return "redirect:/items";
    }

    /**
     * Muestra el listado de items con filtros opcionales.
     *
     * <p>Si se proporciona alguno de los parametros de filtro, se aplica la busqueda
     * correspondiente. Si no se proporciona ningun filtro, se devuelven los primeros
     * 50 items ordenados por nombre.</p>
     *
     * @param model   modelo de la vista
     * @param buscar  texto para buscar en el nombre del item (opcional)
     * @param precio  precio para filtrar (opcional)
     * @param categoria  nombre de la categoria para filtrar (opcional)
     * @param rating  categoria para filtrar (opcional, p.ej. "FiveStar")
     * @return nombre de la plantilla {@code index}
     */
    @GetMapping("/items")
    public String listaitems(
            Model model,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String precio,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String rating) {

        List<Item> items;

        items = itemService.listarTodos();


        model.addAttribute("items", items);
        model.addAttribute("buscar", buscar);
        model.addAttribute("precio", precio);
        model.addAttribute("categoria", categoria);
        model.addAttribute("rating", rating);
        model.addAttribute("stats", itemService.obtenerEstadisticas());
        return "index";
    }

    /**
     * Muestra el detalle completo de un item.
     *
     * @param id    identificador MongoDB del item
     * @param model modelo de la vista
     * @return nombre de la plantilla {@code item} o redireccion a error si no se encuentra
     */
    @GetMapping("/items/{id}")
    public String detalleItem(@PathVariable String id, Model model) {
        try {
            Item item = itemService.buscarPorId(id);
            model.addAttribute("item", item);
            return "item";
        } catch (ItemNotFoundException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "error";
        }
    }

    /**
     * Muestra el formulario para editar un item existente.
     *
     *
     * @param id    identificador MongoDB del item
     * @param model modelo de la vista
     * @return nombre de la plantilla {@code item-form} o {@code error} si no se encuentra
     */
    @GetMapping("/items/{id}/editar")
    public String editarItemForm(@PathVariable String id, Model model) {
        try {
            Item item = itemService.buscarPorId(id);
            model.addAttribute("item", item);
            model.addAttribute("modoEdicion", true);
            return "item-form";
        } catch (ItemNotFoundException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "error";
        }
    }

    /**
     * Procesa el formulario de edicion y guarda los cambios del item.
     *
     * <p>Accesible solo para usuarios con rol ADMIN.</p>
     *
     * @param id                 identificador MongoDB del item
     * @param item              objeto item con los nuevos datos del formulario
     * @param redirectAttributes atributos para el mensaje flash tras redireccion
     * @return redireccion al detalle del item actualizado
     */
    @PostMapping("/items/{id}/editar")
    public String actualizarItem(
            @PathVariable String id,
            @ModelAttribute Item item,
            RedirectAttributes redirectAttributes) {
        itemService.actualizar(id, item);
        redirectAttributes.addFlashAttribute("mensajeExito", "item actualizado correctamente.");
        return "redirect:/items/" + id;
    }

}
