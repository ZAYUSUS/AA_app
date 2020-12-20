package com.example.aatreeshow;

import java.util.ArrayList;

public class AAtree {
    private AAnode root;// raíz
    static AAnode nil = new AAnode();//nodo base
    public int size;


    public AAtree(){//constructor
        root = nil;
    }

    public Boolean Empty(){//determina si la lista esta vacía
        return root==null;
    }

    public void Clear(){//elimina el arbol
        root = null;
    }

    public void Insert(int dato){
        root = insert(dato, this.root);

    }

    private AAnode insert(int dato, AAnode P){//compara el dato con los datos de los nodos para encontrar su posición
        if (P == nil) {
            P = new AAnode();
            P.dato = dato;
            P.izquierdo = nil;
            P.derecho = nil;
            P.nivel = 1;
        } else if ( dato < P.dato ){
            P.izquierdo = insert(dato, P.izquierdo);
        }
        else if ( dato > P.dato) {
            P.derecho = insert(dato, P.derecho);
        }
        else {
            return P;
        }

        P = skew(P);//primera operación de mantenimiento
        P = split(P);//segunda operación de mantenimiento
        size++;
        return P;
    }

    private AAnode skew(AAnode nodo){//primera operación de mantenimiento
        if (nodo == nil) {
            return nil;
        } else if (nodo.izquierdo == nil){
            return nodo;
        } else if (nodo.izquierdo.nivel == nodo.nivel){//verifica que no tenga hijos izquierdos en el mismo nivel
            //rotación
            AAnode aux = nodo.izquierdo;
            nodo.izquierdo = aux.derecho;
            aux.derecho = nodo;
            return aux;
        }
        else {
            return nodo;
        }


    }

    private AAnode split(AAnode nodo) {
        //segunda operación de mantenimiento
        if (nodo == nil) {
            return nil;
        } else if (nodo.derecho == nil || nodo.derecho.derecho == nil) {
            return nodo;
        }
        else if (nodo.nivel == nodo.derecho.derecho.nivel){ //verifica si existen 3 nodos derechos en el mismo nive
            //rotación
            AAnode aux = nodo.derecho;
            nodo.derecho = aux.izquierdo;
            aux.izquierdo = nodo;

            aux.nivel += 1;
            return aux;
        }
        else {
            return nodo;
        }
    }


    public void Eliminar(int dato){
        eliminar(dato, this.root);
    }

    private AAnode eliminar(int dato, AAnode nodo){//elimina un nodo y balancea el árbol
        if(nodo==nil){
            return nodo;
        }else if(dato>nodo.dato){
            nodo.derecho = eliminar(dato, nodo.derecho);
        }else if(dato<nodo.dato){
            nodo.izquierdo = eliminar(dato, nodo.izquierdo);
        }else{
            if(hoja(nodo)){
                return nodo.derecho;
            }else if(nodo.izquierdo == nil){
                AAnode L = suc(nodo);
                nodo.derecho = eliminar(L.dato, nodo.izquierdo);
                nodo.dato = L.dato;
            }else{
                AAnode L = pred(nodo);
                nodo.izquierdo = eliminar(L.dato,nodo.izquierdo);
                nodo.dato = L.dato;
            }
        }
        System.out.println(nodo.derecho.nivel);
        nodo = decreserNivel(nodo);
        nodo = skew(nodo);
        nodo.derecho = skew(nodo.derecho);
        if(!(nodo.derecho==nil)){
            nodo.derecho.derecho = skew(nodo.derecho.derecho);
        }
        nodo = split(nodo);
        nodo.derecho = split(nodo.derecho);
        size--;
        return nodo;
    }

    private AAnode decreserNivel(AAnode nodo){//envia el nodo a un nivel menor
        int aux = Math.min(nodo.izquierdo.nivel,nodo.derecho.nivel)+1;

        if(aux < nodo.nivel){
            nodo.nivel = aux;
            if(aux < nodo.derecho.nivel){
                nodo.derecho.nivel = aux;
            }
        }
        return nodo;
    }

    private Boolean hoja(AAnode nodo){//verifica si el nodo es una hoja
        if(nodo.derecho==null && nodo.izquierdo == null){
            return true;
        }else{
            return false;
        }
    }

    public AAnode suc(AAnode nodo){//encuetra el sucesor
        if(nodo==null){
            return nodo;
        }
        AAnode aux = nodo.derecho;
        while(aux.izquierdo!=null){
            aux = aux.izquierdo;
        }
        return aux;
    }

    public AAnode pred(AAnode nodo){//encuentra el predecesor
        if(nodo==null){
            return nodo;
        }
        AAnode aux = nodo.izquierdo;
        while(aux.derecho!=null){
            aux = aux.derecho;
        }
        return aux;
    }

    public int GetSize(){
        return size;
    }


    //recorrido
    public ArrayList<Integer> inorder()
    {
        ArrayList<Integer> arr = new ArrayList<>();
        return Inorder(this.root, arr);
    }
    public ArrayList<Integer> Inorder(AAnode root, ArrayList<Integer> arr) {
        if (root != nil){
            Inorder(root.izquierdo,arr);
            arr.add(root.dato);
            Inorder(root.derecho,arr);
        }
        return  arr;
    }

    public AAnode Getroot() {
        return this.root;
    }
}
