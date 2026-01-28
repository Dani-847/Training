package org.drk.bibliotecadejuegosspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
class WebController {

    GameRepository gameRepository;
    public WebController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String platform)
    {
        model.addAttribute("platforms", gameRepository.getPlatforms());
        model.addAttribute("selectedPlatform", platform);

        if(platform != null && !platform.isEmpty()) {
            model.addAttribute("games", gameRepository.findGamesByPlatform(platform));
        } else {
            model.addAttribute("games", gameRepository.findAll());
        }
        return "index";
    }

    @GetMapping("/{nombre}")
    public String saludar(@PathVariable String nombre, Model model)
    {
        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido", "anonimo");
        return "saludo";
    }

    @GetMapping("/juego/{id}")
    public String juego(@PathVariable Integer id, Model model)
    {
        model.addAttribute("platforms", gameRepository.getPlatforms());
        if(gameRepository.findById(id).isPresent()) {
            model.addAttribute("game", gameRepository.findById(id).get());
            return "game";
        } else {
            model.addAttribute("error","No existe el juego "+id);
            return "error";
        }
    }

    @GetMapping("/847")
    public String clicker() {
        return "847";
    }

}
