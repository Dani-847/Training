package org.example;

import java.util.ArrayList;
import java.util.List;

public class Nodo extends Thread{
    private List <Integer> lista;

    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(List<Integer> listaPricipal){
        this.lista=new  ArrayList<>(listaPricipal);
        this.izquierda=null;
        this.derecha=null;
    }

    public List<Integer> getLista() {
        return lista;
    }

    public void setLista(List<Integer> lista) {
        this.lista = new ArrayList<>(lista);
    }

    private void anadir(List<Integer> list, int desde, int hasta){
        list.addAll(lista.subList(desde, hasta));
    }

    private void mezcla(List<Integer> lista1, List<Integer> lista2){
        List<Integer> lista_resultado = new ArrayList<>();
        int l1=0;
        int l2=0;

        while(l1<lista1.size() && l2<lista2.size()){
            if (lista1.get(l1) <= lista2.get(l2)){
                lista_resultado.add(lista1.get(l1));
                l1++;
            } else  {
                lista_resultado.add(lista2.get(l2));
                l2++;
            }
        }

        while(l1<lista1.size()){
            lista_resultado.add(lista1.get(l1));
            l1++;
        }

        while(l2<lista2.size()){
            lista_resultado.add(lista2.get(l2));
            l2++;
        }

        lista =lista_resultado;
    }

    public void run() {
        if (lista.size()<2){
            return;
        }

        int mitad= lista.size()/2;

        List<Integer> lista1=new ArrayList<>();
        List<Integer> lista2=new ArrayList<>();

        anadir(lista1, 0, mitad);
        anadir(lista2, mitad, lista.size());

        izquierda=new Nodo(lista1);
        derecha=new Nodo(lista2);

        izquierda.start();
        derecha.start();

        try{
            izquierda.join();
            derecha.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mezcla(izquierda.getLista(), derecha.getLista());
    }
}