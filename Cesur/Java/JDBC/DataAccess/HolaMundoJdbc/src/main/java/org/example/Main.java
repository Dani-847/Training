package org.example;

import javax.sql.DataSource;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        DataSource ds = DataProvider.getDataSource(
                "jdbc:mysql://localhost:3306/ad",
                "root",
                "pass"
        );

        var dao = new VideoJuegoDAO(ds);

        System.out.println("Find all");
        dao.findAll().forEach(System.out::println);

        System.out.println("Find by id");
        dao.findById(20).ifPresentOrElse(System.out::println, () -> {
            System.out.println("No encontrado");
        });
    }
}