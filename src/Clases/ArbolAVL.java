/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
/**
 * Esta es la clase ArbolAVL que hereda de l clase Comparable la cual sirve como una ayuda para comparar los elementos 
 * a insertar en el arreglo e insertarlos de manera ordenada y para buscar los datos en el arbol.
 * @author IngridNiño, Zuleyka Guzman
 */
public class ArbolAVL<T extends Comparable<T>>{
    /**
     * Esta es la raiz del arbol de tipo  NodoAVL 
     */
    private NodoAVL raiz;
    /**
     * Este es el metodo get de la raiz
     * @return raiz
     */
    public NodoAVL getRaizAVL(){
        return raiz;
    }
    /**
     * Este es el metodo revisarI en el cual se evalua si se tiene q hacer una rotacion II(izquierda-izquierda) 
     * o una rotacion ID (izquierda-derecha).
     * @param nodo
     * @param direccion 
     */
    public void revisarI(NodoAVL<T> nodo, Integer direccion){
        NodoAVL<T> nodo1, nodo2, nodo3;
        if (direccion ==-1){
          nodo1= nodo.getIzq();
          nodo2= nodo1.getIzq();
        }else{
          nodo1=(NodoAVL<T>) nodo.getDer();
          nodo2=(NodoAVL<T>) nodo1.getIzq();    
        }
        if(nodo2.getFe() == -1){// Rotacion II
            nodo1.setIzq(nodo2.getDer());
            nodo2.setDer(nodo1);
            nodo1.setFe(0);
            if (direccion == -1){
                nodo.setIzq(nodo2);
            }else{
                nodo.setDer(nodo2); 
            }
        }else{ // Rotacion ID
            nodo3 = (NodoAVL<T>) nodo2.getDer();
            nodo1.setIzq(nodo3.getDer());
            nodo3.setDer(nodo1);
            nodo2.setDer(nodo3.getIzq());
            nodo3.setIzq(nodo2);
            if(nodo3.getFe() == -1){
                nodo1.setFe(1);
            }else{
                nodo1.setFe(0);
            }
            if (nodo3.getFe() == 1){
                nodo2.setFe(-1);
            }else{
                nodo2.setFe(0);
            }
            if (direccion == -1){
                nodo.setIzq(nodo3);
            }else{
                nodo.setDer(nodo3); 
            }
            
        }
        nodo1.setFe(0);
    }
    /**
     * Este es el metodo revisarD en el cual se evalua si se tiene que hacer una rotacion DD (Derecha-derecha) o
     * DI(derecha-izquierda)
     * @param nodo
     * @param direccion 
     */
    public void revisarD(NodoAVL<T> nodo, Integer direccion){
        NodoAVL<T> nodo1, nodo2, nodo3;
        if (direccion ==-1){
            nodo1=nodo.getIzq();
            nodo2= nodo1.getDer();
        }else{
            nodo1 =  nodo.getDer();
            nodo2= nodo1.getDer();
        }
        if(nodo2.getFe() == 1){// Rotacion DD
            nodo1.setDer(nodo2.getIzq());
            nodo2.setIzq(nodo1);
            nodo1.setFe(0);
            if (direccion == -1){
                nodo.setIzq(nodo2);
            }else{
                nodo.setDer(nodo2); 
            }
            
            
        }else{ // Rotacion DI
            nodo3 = nodo2.getIzq();
            nodo1.setDer(nodo3.getIzq());
            nodo3.setIzq(nodo1);
            nodo2.setIzq(nodo3.getDer());
            nodo3.setDer(nodo2);
            if(nodo3.getFe() == 1){
                nodo1.setFe(-1);
            }else{
                nodo1.setFe(0);
            }
            if (nodo3.getFe() == -1){
                nodo2.setFe(1);
            }else{
                nodo2.setFe(0);
            }
            if (direccion == -1){
                nodo.setIzq(nodo3);
            }else{
                nodo.setDer(nodo3); 
            }
            
        }
        nodo1.setFe(0);
    }
    /**
     * Este es el metodo _insertar en el cual se revisa si el arbol esta balanceado,
     * en caso de que no lo este se manda a llamar al metodo revisarI o revisarD segun la rotacion correpondiente,
     * en base al lado del cual se cargo el arbol
     * @param nodo
     * @param dato
     * @return resultado
     */
    private Integer _insertar(NodoAVL<T> nodo, T dato){
        Integer resultado = 0;
        if (dato.compareTo(nodo.getDato()) < 0){
            if (nodo.getIzq() == null){
                nodo.setIzq(new NodoAVL(dato));
                switch (nodo.getFe()){
                case 1: // El arbol se balanceÃ³
                    nodo.setFe(0);
                    resultado = 0;
                    break;
                case 0: // Se cargÃ³ del lado izquierdo
                    nodo.setFe(-1);
                    resultado = 1;
                    break;              
                }
                //resultado = 1;
                
            }else{
                switch (_insertar(nodo.getIzq(),dato)){
                case 1: // Se insrtÃ³ un dato nuevo, revisar.
                    switch (nodo.getFe()){
                    case 1: // El arbol se balanceÃ³
                        nodo.setFe(0);
                        resultado = 0;
                        break;
                    case 0: // Se cargÃ³ del lado izquierdo
                        nodo.setFe(-1);
                        resultado = 1;
                        break;  
                    case -1: // Reestructuracion del arbol
                            resultado = -1;
                        break;
                    }
                    break;
                case -1:
                    revisarI(nodo,-1);
                    break;
                case -2:
                    revisarD(nodo,-1);
                    break;
                    
            
                    
                }
                
                
            }
        }else{
            if (dato.compareTo(nodo.getDato()) > 0){
                if (nodo.getDer() == null){
                    nodo.setDer(new NodoAVL(dato));
                    switch (nodo.getFe()){
                    case -1: // El arbol se balanceÃ³
                        nodo.setFe(0);
                        resultado = 0;
                        break;
                    case 0: // Se cargÃ³ del lado izquierdo
                        nodo.setFe(1);
                        resultado = 1;
                        break;              
                    }
                }else{
                    switch (_insertar(nodo.getDer(),dato)){
                    case 1: // Se insrtÃ³ un dato nuevo, revisar.
                        switch (nodo.getFe()){
                        case -1: // El arbol se balanceÃ³
                            nodo.setFe(0);
                            resultado = 0;
                            break;
                        case 0: // Se cargÃ³ del lado der
                            nodo.setFe(1);
                            resultado = 1;
                            break;  
                        case 1: // Reestructuracion del arbol
                                resultado = -2;
                            }
                            break;
                    case -1:
                        revisarI(nodo,1);
                        break;
                    case -2:
                        revisarD(nodo,1);
                    break;
                    
                        }                       
                        
                    }
                }
            }
        
        
        return resultado;
     
   }
    /**
     * Este es el metodo insertar en el cual se inserta un nodo al arbol y se evaluan las condiciones para verificar 
     * que el arbol este balanceado checado nuevamente los factores de equilibrio y en caso de que no lo este
     * se hace una restruccturacion del arbol.
     * @param dato
     * @return 0
     */
    public Integer insertar(T dato) {
        if (raiz == null){
            raiz = new NodoAVL<T>(dato);
        }else{
            Integer result = _insertar(raiz, dato);
            if (result == -1){
            
                // ReestructuraciÃ³n del arbol
                    NodoAVL<T> nodo1, nodo2;
                    nodo1=(NodoAVL) raiz.getIzq();
                    
                    if(nodo1.getFe() == -1){// Rotacion II
                        raiz.setIzq(nodo1.getDer());
                        nodo1.setDer(raiz);
                        raiz.setFe(0);
                        raiz = nodo1;
                    }else{ // Rotacion ID
                        nodo2 = nodo1.getDer();
                        raiz.setIzq(nodo2.getDer());
                        nodo2.setDer(raiz);
                        nodo1.setDer(nodo2.getIzq());
                        nodo2.setIzq(nodo1);
                        if(nodo2.getFe() == -1){
                            raiz.setFe(1);
                        }else{
                            raiz.setFe(0);
                        }
                        if (nodo2.getFe() == 1){
                            nodo1.setFe(-1);
                        }else{
                            nodo1.setFe(0);
                        }
                        raiz = nodo2;
                    }
                    raiz.setFe(0);
            
                
                         
            }else if (result == -2){
                // Reestructuración del arbol
                NodoAVL<T> nodo1, nodo2;
                nodo1= raiz.getDer();
                
                if(nodo1.getFe() == 1){// Rotacion DD
                    raiz.setDer(nodo1.getIzq());
                    nodo1.setIzq(raiz);
                    raiz.setFe(0);
                    raiz = nodo1;
                }else{ // Rotacion DI
                    nodo2 = (NodoAVL<T>) nodo1.getIzq();
                    raiz.setDer(nodo2.getIzq());
                    nodo2.setIzq(raiz);
                    nodo1.setIzq(nodo2.getDer());
                    nodo2.setDer(nodo1);
                    if(nodo2.getFe() == 1){
                        raiz.setFe(-1);
                    }else{
                        raiz.setFe(0);
                    }
                    if (nodo2.getFe() == -1){
                        nodo1.setFe(1);
                    }else{
                        nodo1.setFe(0);
                    }
                    raiz = nodo2;
                    
                }
                
                raiz.setFe(0);
        
            }
        }
        return 0;
    }
    /**
     * Este es el metodo preorden el cual recorre el arbol 
     * @param raiz 
     */
    public void preorden(NodoAVL<T> raiz){
 
        if(raiz != null){
            System.out.println(raiz.getDato());
              
            preorden(raiz.getIzq());
            
            preorden(raiz.getDer());
        }
    }
    /**
     * Este es el metodo buscar de la clase arbol el cual utiliza el metodo compareTo  de la clase Comparable
     * utilizando este metodo compara si el dato a buscar es menor que la raiz, en cuyo caso se va por la izquierda,
     * y si este es menor se va por la derecha y asi sigue comparando los elementos hasta encontrar el elemento buscado.
     * Y si el elemento buscado no se encuentra en el arbol entonces se imprime un mensaje que dice que el 
     * elemento no se encontr en el arbol.
     * @param raiz
     * @param dato
     * @return resultado
     */
    public String buscar(NodoAVL<T> raiz, T dato){
        String resultado = "";
        if (raiz != null){
            if (dato.compareTo(raiz.getDato()) < 0){ // Vamos por la izquierda
               resultado = buscar(raiz.getIzq(), dato);
            }else if (dato.compareTo(raiz.getDato()) > 0){ // Vamos por la derecha
              resultado =  buscar(raiz.getDer(), dato);
            }else{
                return "Se encontro " + dato + " en el arbol.";
            }
        }else{
            return  "No se encontro " + dato + " en el arbol.";
        }
        return resultado;
    } 
}

