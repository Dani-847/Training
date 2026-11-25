package org.drk.bibliotecadejuegosspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
class WebController {

    private GameRepository gameRepository;
    private GameService gameService;

    public WebController(GameRepository gameRepository, GameService gameService) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String platform) {
        model.addAttribute("platforms", gameRepository.getPlatforms());
        model.addAttribute("selectedPlatform", platform);

        if (platform != null && !platform.isEmpty()) {
            model.addAttribute("games", gameRepository.findGamesByPlatform(platform));
        } else {
            model.addAttribute("games", gameRepository.findAll());
        }
        return "index";
    }

    @PostMapping("/")
    public String crear(@ModelAttribute Game newGame) {
        gameService.saveGame(newGame);
        return "redirect:/";
    }

    @GetMapping("/nuevo")
    public String nuevoJuego(Model model) {
        model.addAttribute("platforms", gameRepository.getPlatforms());
        model.addAttribute("game", new Game());
        return "form";
    }

    @GetMapping("/juego/{id}/editar")
    public String editarJuego(@PathVariable Integer id, Model model) {
        model.addAttribute("platforms", gameRepository.getPlatforms());
        if (gameRepository.findById(id).isPresent()) {
            model.addAttribute("game", gameRepository.findById(id).get());
            return "form";
        } else {
            model.addAttribute("error", "No existe el juego " + id);
            return "error";
        }
    }

    @PostMapping("/juego/{id}/editar")
    public String guardarEdicion(@PathVariable Integer id, @ModelAttribute Game game) {
        game.setId(id);
        gameRepository.save(game);
        return "redirect:/juego/" + id;
    }

    @PostMapping("/juego/{id}/eliminar")
    public String eliminarJuego(@PathVariable Integer id) {
        if (gameRepository.findById(id).isPresent()) {
            gameRepository.deleteById(id);
        }
        return "redirect:/";
    }

    @GetMapping("/{nombre}")
    public String saludar(@PathVariable String nombre, Model model) {
        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido", "anonimo");
        return "saludo";
    }

    @GetMapping("/juego/{id}")
    public String juego(@PathVariable Integer id, Model model) {
        model.addAttribute("platforms", gameRepository.getPlatforms());
        if (gameRepository.findById(id).isPresent()) {
            model.addAttribute("game", gameRepository.findById(id).get());
            return "game";
        } else {
            model.addAttribute("error", "No existe el juego " + id);
            return "error";
        }
    }

    @GetMapping("/847")
    public String clicker() {
        return "847";
    }
}
