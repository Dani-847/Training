package org.drk;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Game> games = new ArrayList<Game>();

    public void addGame(Game g){
        g.setUser(this);
        games.add(g);
    }

    public void removeGame(Game g){
        games.remove(g);
    }
}