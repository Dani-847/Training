package org.drk;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        User u = new User();
        u.setEmail("Francisco");


        ArrayList<Game> games = new ArrayList<>();

        Game g1 = new Game();
        g1.setName("God of War");
        g1.setYear(2018);
        g1.setImagen("gow.jpg");
        g1.setPlatform("PS4");
        g1.setDescription("Aventura de acción ambientada en la mitología nórdica.");
        games.add(g1);

        Game g2 = new Game();
        g2.setName("The Last of Us");
        g2.setYear(2013);
        g2.setImagen("tlou.jpg");
        g2.setPlatform("PS3");
        g2.setDescription("Historia postapocalíptica centrada en la supervivencia.");
        games.add(g2);

        Game g3 = new Game();
        g3.setName("Halo Combat Evolved");
        g3.setYear(2001);
        g3.setImagen("halo.jpg");
        g3.setPlatform("Xbox");
        g3.setDescription("Shooter en primera persona de ciencia ficción.");
        games.add(g3);

        Game g4 = new Game();
        g4.setName("The Legend of Zelda: Breath of the Wild");
        g4.setYear(2017);
        g4.setImagen("zelda_botw.jpg");
        g4.setPlatform("Nintendo Switch");
        g4.setDescription("Juego de mundo abierto y exploración.");
        games.add(g4);

        Game g5 = new Game();
        g5.setName("Final Fantasy VII");
        g5.setYear(1997);
        g5.setImagen("ff7.jpg");
        g5.setPlatform("PS1");
        g5.setDescription("RPG clásico con una historia épica.");
        games.add(g5);


        games.forEach( (g)->u.addGame(g) );


        EntityManager entityManager = DataProvider.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Game g = entityManager.find(Game.class, 3);
            //entityManager.remove(g);

            entityManager.getTransaction().commit();
        }
        finally {
            entityManager.close();
        }

    }
}