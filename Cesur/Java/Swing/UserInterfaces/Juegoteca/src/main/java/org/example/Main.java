package org.example;

import org.example.data.CsvDataService;
import org.example.data.DataService;
import org.example.user.FileUserService;
import org.example.user.UserService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DataService ds = new CsvDataService("juegos.csv");
        UserService us = new FileUserService("user.properties");
        (new org.example.ui.Main(ds,us)).start();

    }
}