package com.example.aatreeshow;

public class AAnode {
    /***
     * Clase asociada a la clase AA_tree, contiene los elementos que maneja.
     * @Author Bryan Esquivel
     */
    public int dato;//nivel del nodo y el dato que contiene
    public int nivel;
    public AAnode izquierdo,derecho;//hijos
    //constructor
    public AAnode()
    {
        this.dato = 0;
        this.izquierdo = this;
        this.derecho = this;
        this.nivel = 0;
    }
    void setDato(int dato) {
        this.dato = dato;
    }
    void setNivel(int nivel){
        this.nivel = nivel;
    }
    void setDerecho(AAnode nuevo){
        this.derecho = nuevo;
    }
    void setIzquierdo(AAnode nuevo){
        this.izquierdo = nuevo;
    }

    int getDato() {
        return this.dato;
    }
    int getNivel(){
        return this.nivel;
    }
    AAnode getDerecho(){
        return this.derecho;
    }
    AAnode getIzquierdo(){
        return this.izquierdo;
    }
}
