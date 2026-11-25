package org.drk;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int a = 60;
        int b = 100;
        for (int i = 0; i <= a; i++) {
            System.out.println(i + " min es en % " + (double)i*b/a);
        }
    }
}