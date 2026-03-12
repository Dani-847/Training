package org.drk.Reto6.controllers;

import org.drk.Reto6.entities.Hotel;
import org.drk.Reto6.exceptions.HotelNotFoundException;
import org.drk.Reto6.services.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador MVC principal de la aplicacion web de turismo.
 *
 * <p>Gestiona las rutas web y renderiza las vistas Thymeleaf para la consulta
 * y gestion de hoteles. No consume la API REST; accede directamente a la base
 * de datos a traves de {@link HotelService}.</p>
 *
 * <p>Rutas publicas (cualquier visitante):</p>
 * <ul>
 *   <li>{@code GET /} - redirige al listado de hoteles</li>
 *   <li>{@code GET /hoteles} - listado con filtros opcionales</li>
 *   <li>{@code GET /hoteles/{id}} - detalle de un hotel</li>
 * </ul>
 *
 * <p>Rutas protegidas (requieren rol ADMIN):</p>
 * <ul>
 *   <li>{@code GET /hoteles/nuevo} - formulario de creacion</li>
 *   <li>{@code POST /hoteles/nuevo} - guardar nuevo hotel</li>
 *   <li>{@code GET /hoteles/{id}/editar} - formulario de edicion</li>
 *   <li>{@code POST /hoteles/{id}/editar} - guardar cambios</li>
 *   <li>{@code POST /hoteles/{id}/eliminar} - eliminar hotel</li>
 * </ul>
 */
@Controller
public class HotelController {

    private final HotelService hotelService;

    /**
     * Constructor con inyeccion del servicio de hoteles.
     *
     * @param hotelService servicio de negocio para hoteles
     */
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // ==================== RUTAS PUBLICAS ====================

    /**
     * Redirige la raiz de la aplicacion al listado de hoteles.
     *
     * @return redireccion a {@code /hoteles}
     */
    @GetMapping("/")
    public String inicio() {
        return "redirect:/hoteles";
    }

    /**
     * Muestra el listado de hoteles con filtros opcionales.
     *
     * <p>Si se proporciona alguno de los parametros de filtro, se aplica la busqueda
     * correspondiente. Si no se proporciona ningun filtro, se devuelven los primeros
     * 50 hoteles ordenados por nombre.</p>
     *
     * @param model   modelo de la vista
     * @param buscar  texto para buscar en el nombre del hotel (opcional)
     * @param pais    codigo de pais para filtrar (opcional)
     * @param ciudad  nombre de ciudad para filtrar (opcional)
     * @param rating  categoria para filtrar (opcional, p.ej. "FiveStar")
     * @return nombre de la plantilla {@code index}
     */
    @GetMapping("/hoteles")
    public String listaHoteles(
            Model model,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String rating) {

        List<Hotel> hoteles;

        if (buscar != null && !buscar.isBlank()) {
            hoteles = hotelService.buscarPorNombre(buscar);
        } else if (pais != null && !pais.isBlank()) {
            hoteles = hotelService.buscarPorPais(pais);
        } else if (ciudad != null && !ciudad.isBlank()) {
            hoteles = hotelService.buscarPorCiudad(ciudad);
        } else if (rating != null && !rating.isBlank()) {
            hoteles = hotelService.buscarPorRating(rating);
        } else {
            hoteles = hotelService.listarTodos();
        }

        model.addAttribute("hoteles", hoteles);
        model.addAttribute("buscar", buscar);
        model.addAttribute("pais", pais);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("rating", rating);
        model.addAttribute("stats", hotelService.obtenerEstadisticas());
        return "index";
    }

    /**
     * Muestra el detalle completo de un hotel.
     *
     * @param id    identificador MongoDB del hotel
     * @param model modelo de la vista
     * @return nombre de la plantilla {@code hotel} o redireccion a error si no se encuentra
     */
    @GetMapping("/hoteles/{id}")
    public String detalleHotel(@PathVariable String id, Model model) {
        try {
            Hotel hotel = hotelService.buscarPorId(id);
            model.addAttribute("hotel", hotel);
            return "hotel";
        } catch (HotelNotFoundException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "error";
        }
    }

    // ==================== RUTAS ADMIN - CREACION ====================

    /**
     * Muestra el formulario para crear un nuevo hotel.
     *
     * <p>Accesible solo para usuarios con rol ADMIN.</p>
     *
     * @param model modelo de la vista
     * @return nombre de la plantilla {@code hotel-form}
     */
    @GetMapping("/hoteles/nuevo")
    public String nuevoHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("modoEdicion", false);
        return "hotel-form";
    }

    /**
     * Procesa el formulario de creacion y guarda el nuevo hotel.
     *
     * <p>Accesible solo para usuarios con rol ADMIN.</p>
     *
     * @param hotel              objeto Hotel enlazado desde el formulario
     * @param redirectAttributes atributos para el mensaje flash tras redireccion
     * @return redireccion al listado de hoteles
     */
    @PostMapping("/hoteles/nuevo")
    public String crearHotel(@ModelAttribute Hotel hotel, RedirectAttributes redirectAttributes) {
        hotelService.crear(hotel);
        redirectAttributes.addFlashAttribute("mensajeExito", "Hotel creado correctamente.");
        return "redirect:/hoteles";
    }

    // ==================== RUTAS ADMIN - EDICION ====================

    /**
     * Muestra el formulario para editar un hotel existente.
     *
     * <p>Accesible solo para usuarios con rol ADMIN.</p>
     *
     * @param id    identificador MongoDB del hotel
     * @param model modelo de la vista
     * @return nombre de la plantilla {@code hotel-form} o {@code error} si no se encuentra
     */
    @GetMapping("/hoteles/{id}/editar")
    public String editarHotelForm(@PathVariable String id, Model model) {
        try {
            Hotel hotel = hotelService.buscarPorId(id);
            model.addAttribute("hotel", hotel);
            model.addAttribute("modoEdicion", true);
            return "hotel-form";
        } catch (HotelNotFoundException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "error";
        }
    }

    /**
     * Procesa el formulario de edicion y guarda los cambios del hotel.
     *
     * <p>Accesible solo para usuarios con rol ADMIN.</p>
     *
     * @param id                 identificador MongoDB del hotel
     * @param hotel              objeto Hotel con los nuevos datos del formulario
     * @param redirectAttributes atributos para el mensaje flash tras redireccion
     * @return redireccion al detalle del hotel actualizado
     */
    @PostMapping("/hoteles/{id}/editar")
    public String actualizarHotel(
            @PathVariable String id,
            @ModelAttribute Hotel hotel,
            RedirectAttributes redirectAttributes) {
        hotelService.actualizar(id, hotel);
        redirectAttributes.addFlashAttribute("mensajeExito", "Hotel actualizado correctamente.");
        return "redirect:/hoteles/" + id;
    }

    // ==================== RUTAS ADMIN - ELIMINACION ====================

    /**
     * Elimina un hotel de la base de datos.
     *
     * <p>Accesible solo para usuarios con rol ADMIN. La solicitud se envia
     * mediante un formulario POST desde la vista de detalle.</p>
     *
     * @param id                 identificador MongoDB del hotel a eliminar
     * @param redirectAttributes atributos para el mensaje flash tras redireccion
     * @return redireccion al listado de hoteles
     */
    @PostMapping("/hoteles/{id}/eliminar")
    public String eliminarHotel(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            hotelService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "Hotel eliminado correctamente.");
        } catch (HotelNotFoundException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/hoteles";
    }

    // ==================== LOGIN ====================

    /**
     * Muestra la pagina de inicio de sesion.
     *
     * <p>Si el usuario ya esta autenticado, Spring Security lo redirige
     * automaticamente a {@code /hoteles}.</p>
     *
     * @param error  parametro presente cuando las credenciales son incorrectas (opcional)
     * @param model  modelo de la vista
     * @return nombre de la plantilla {@code login}
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorLogin", "Usuario o contrasena incorrectos.");
        }
        return "login";
    }
}
