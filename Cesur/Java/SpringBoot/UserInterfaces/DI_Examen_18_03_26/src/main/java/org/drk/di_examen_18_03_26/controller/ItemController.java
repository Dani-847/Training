// src/main/java/org/drk/di_examen_18_03_26/controller/ItemController.java
package org.drk.di_examen_18_03_26.controller;

import org.drk.di_examen_18_03_26.entities.Item;
import org.drk.di_examen_18_03_26.exceptions.ItemNotFoundException;
import org.drk.di_examen_18_03_26.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/items";
    }

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

        model.addAttribute("stats", itemService.getStoreStats());

        return "index";
    }

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

    @PostMapping("/items/{id}/editar")
    public String actualizarItem(
            @PathVariable String id,
            @ModelAttribute Item item,
            RedirectAttributes redirectAttributes) {
        itemService.actualizar(id, item);
        redirectAttributes.addFlashAttribute("mensajeExito", "Item actualizado correctamente.");
        return "redirect:/items/" + id;
    }


    @GetMapping("/items/nuevo")
    public String nuevoItemForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("modoEdicion", false);
        return "item-form";
    }

    @PostMapping("/items/nuevo")
    public String crearItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item nuevo = itemService.crear(item);
        redirectAttributes.addFlashAttribute("mensajeExito", "Item creado correctamente.");
        return "redirect:/items/" + nuevo.get_id(); // Redirige al detalle usando el ID de Mongo
    }

    @PostMapping("/items/{id}/eliminar")
    public String eliminarItem(@PathVariable String id, RedirectAttributes redirectAttributes) {
        // Nota: Asegúrate de tener el método eliminar en tu servicio si vas a usar esto
        // itemService.eliminar(id);
        // Como no está definido en tu Service actual, lo dejo comentado o deberías implementarlo.
        redirectAttributes.addFlashAttribute("mensajeExito", "Funcionalidad de eliminar pendiente de implementar en servicio.");
        return "redirect:/items";
    }
}
