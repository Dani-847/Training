package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        Random random = new Random();

        int tamanoLista=random.nextInt(100);

        System.out.println("Lista inicial");
        for  (int i = 0; i < tamanoLista; i++) {
            int num=random.nextInt(100);
            lista.add(num);
            System.out.print(num+" ");
        }
        System.out.println();
        Nodo nodo=new Nodo(lista);

        nodo.start();

        try{
            nodo.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Lista ordenada");
        for (Integer i : nodo.getLista()) {
            System.out.print(i+" ");
        }
    }
}