package org.drk;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class Game implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String platform;
    private Integer year;
    private String imagen;

    @ManyToOne
    private User user;
}