/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *Esta es la clase NodoAVL 
 * @author IngridNiño, Zuleyka Guzman
 */
public class NodoAVL<T>{
    /**
     * Esta es la variable dato que almacena la informacion del nodo
     */
    private T dato;
    /**
     * Esta es la variable izq del nodo que apunta al nodo que se encuentra a la izquierda del nodo
     */
    private NodoAVL izq;
    /**
     * Esta es la variable der del nodo que apunta al nodo que se encuentra a la derecha del nodo
     */
    private NodoAVL der;
    /**
     * Esta es la variable fe, la cual guarda el valor del factor de equilibrio de cada nodo, el cual sirve para checar
     * si el arbol esta balanceado o no
     */
    private Integer fe;
    /**
     * Este es el constructor de la clase el cual le asigna un valor al dato e inicializa las variables izq y der en null 
     * y la variable fe en 0
     * @param dato 
     */
     public NodoAVL(T dato){
        this.dato=dato;
        this.izq = der = null;
        fe=0;
    }
    /**
     * Este es el metodo get de la variable fe
     * @return fe
     */
    public Integer getFe() {
        return fe;
    }
    /**
     * Este es el metodo set de la variable fe
     * @param fe 
     */
    public void setFe(Integer fe) {
        this.fe = fe;
    }
    /**
     * Este es el metodo get de la variable dato
     * @return dato
     */
    public T getDato() {
        return dato;
    }
    /**
     * Este es el metodo set de la variable dato
     * @param dato 
     */
    public void setDato(T dato) {
        this.dato = dato;
    }
    /**
     * Este es el metodo get de la variable izq
     * @return izq
     */
    public NodoAVL getIzq() {
        return izq;
    }
    /**
     * Este es el metodo set de la variable izq
     * @param izq 
     */
    public void setIzq(NodoAVL izq) {
        this.izq = izq;
    }
    /**
     * Este es el metodo get de la variable der
     * @return der
     */
    public NodoAVL getDer() {
        return der;
    }
    /**
     * Este es el metodo set de la variable der
     * @param der 
     */
    public void setDer(NodoAVL der) {
        this.der = der;
    }   
}
    
