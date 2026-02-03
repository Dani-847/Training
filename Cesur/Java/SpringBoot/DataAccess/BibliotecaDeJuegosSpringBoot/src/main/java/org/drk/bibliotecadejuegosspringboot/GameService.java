package org.drk.bibliotecadejuegosspringboot;

import org.springframework.stereotype.Service;

@Service
class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void saveGame(Game newGame) {
        //Game g = new Game();
        //g.setTitle(newGame.getTitle().trim());
        //g.setPlatform(newGame.getPlatform().trim());
        newGame.setDescription("No hay descripcion");
        newGame.setYear(2026);
        gameRepository.save(newGame);
    }

}