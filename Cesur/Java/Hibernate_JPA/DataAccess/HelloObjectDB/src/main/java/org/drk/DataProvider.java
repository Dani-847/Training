package org.drk;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DataProvider {

    private final static String DB_FILE = "data/data.odb";

    private static EntityManagerFactory entityManagerFactory = null;

    static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory(DB_FILE);
        }
        return entityManagerFactory;
    }

}