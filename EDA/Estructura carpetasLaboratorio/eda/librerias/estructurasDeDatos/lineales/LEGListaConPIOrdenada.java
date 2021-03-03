package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.NodoLEG;
import librerias.estructurasDeDatos.modelos.ListaConPI;


/**
 * Practica1
 * 
 * @author Andreu Mut Portes
 * @version 2021
 */
public class LEGListaConPIOrdenada<E extends Comparable<E>> extends LEGListaConPI<E> {
    
    /**
     * Constructor objetos LEGListaConPIOrdenada
     */
    public LEGListaConPIOrdenada(){
        super();
    }
    
    public void insertar(E e){
        //asegurar que el pi apunta a l'inici
        inicio();
        //col·locar el pi apuntant al primer element que siga major que e.
        while(!esFin() && recuperar().compareTo(e) < 0){
            siguiente(); //Avançar punter si no es major
        }
        //Crida al insertar de la clase LEGListaConPI
        super.insertar(e);        
    }
        
}
