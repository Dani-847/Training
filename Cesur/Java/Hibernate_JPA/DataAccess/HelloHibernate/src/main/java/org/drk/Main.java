package org.drk;

import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = DataProvider.getSessionFactory();
    }
}