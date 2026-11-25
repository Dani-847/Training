package notas;

import org.drk.DataProvider;
import org.drk.Game;
import org.drk.GameRepository;

public class NOTAS {
    public static void main(String[] args) {
/*        var hib = new Configuration().configure();
        SessionFactory factory = hib.buildSessionFactory();

        try(Session session = factory.openSession()){
            List<Game> games = session.createQuery("from Game").list();
            games.forEach(System.out::println);
        }
/*
        Game game = new Game();
        game.setTitle("New Game");
        game.setPlatform("Platform");
        game.setDescription("Description");
        game.setYear(2018);

        factory.inTransaction(session -> {
            session.persist(game);
        });
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.persist(game);
            session.getTransaction().commit();
        }

        try(Session session = factory.openSession()){
            session.beginTransaction();
            Game g5 = session.find(Game.class, 5);
            System.out.println(g5);
            g5.setTitle("Ciberpun 20777777777777");
            session.merge(g5);
            session.getTransaction().commit();
            System.out.println(g5);
        }

        factory.inTransaction((session) -> {
            Game g = session.find(Game.class, 41);
            if (g != null) session.remove(g);
        });

        try(Session session = factory.openSession()){
            List<Game> games = session.createQuery("from Game").list();
            games.forEach(System.out::println);
        }

        System.out.println("--------------------------------------");
        factory.inSession((session) -> {
            Query<Game> query = session.createQuery("from Game where year = :title");
            query.setParameter("title", 1998);
            List<Game> games = query.getResultList();
            games.forEach(System.out::println);
        });*/

        GameRepository gameRepository = new GameRepository(DataProvider.getSessionFactory());

        Game game = new Game();
        game.setTitle("Title");
        game.setPlatform("Platform");
        game.setDescription("Description");
        game.setYear(2018);

        System.out.println( gameRepository.findById(6L) );

        gameRepository.findAll().forEach(g -> {
            System.out.println(g.getTitle());
        });

        System.out.println(gameRepository.count());

        gameRepository.findById(7L).ifPresent(
                (g)-> gameRepository.delete(g)
        );


    }
}