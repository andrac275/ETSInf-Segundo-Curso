package aplicaciones.primitiva;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;

/** 
 * ApuestaPrimitiva: representa una apuesta aleatoria de La Primitiva, 
 * o combinacion de 6 numeros distintos entre el 1 y el 49 elegidos  
 * de forma aleatoria.
 * 
 * @version Febrero 2019
 */

public class ApuestaPrimitiva {
    
    // Una Primitiva TIENE UNA Lista con PI que almacena
    // una combinacion de 6 numeros de La Primitiva
    private ListaConPI<NumeroPrimitiva> combinacion;
    
    /**
     * Crea una ApuestaPrimitiva, o una combinacion de  
     * seis numeros aleatorios con valores distintos y   
     * en el intervalo [1, 49].
     * 
     * @param ordenada Un boolean que indica si la combinacion,  
     *                 sus 6 numeros, debe estar ordenada Asc.
     *                 (true) o no (false).           
     */
    public ApuestaPrimitiva(boolean ordenada) {
        /* COMPLETAR */
        if(ordenada){//Si esta ordenada
            combinacion = new LEGListaConPIOrdenada();
        }else{//Si NO esta ordenada
            combinacion = new LEGListaConPI();
        }
        
        int contador = 0;        
        while(contador < 6){
            NumeroPrimitiva n = new NumeroPrimitiva();
            if(posicionDe(n) == -1){
                combinacion.insertar(n);
                contador++;
            }            
            
        }
        
    }
    
    /**
     * Devuelve la posicion del numero n en una ApuestaPrimitiva, 
     * o -1 si n no forma parte de la combinacion. 
     * IMPORTANTE: se asume que el primer elemento de una combinacion 
     * esta en su posicion 0 y el ultimo en la 5.
     * 
     * @param n un int
     * @return  la posicion de n en una combinacion, un valor int
     *          en el intervalo [0, 5] si n esta en la combinacion      
     *          o -1 en caso contrario
     */
    protected int posicionDe(NumeroPrimitiva n) {
        /* COMPLETAR */        
        if(combinacion.talla() == 0){ return -1;}
        
        int contador =0;        
        combinacion.inicio();
        while (contador < combinacion.talla()){
            if(combinacion.recuperar().equals(n)){
                return contador;
            }
            combinacion.siguiente();
            contador++;
        }
        
        return -1;
    }
    
    /**
     * Devuelve el String que representa una ApuestaPrimitiva en el formato
     * texto que muestra el siguiente ejemplo: "16, 25, 28, 49, 9, 20"
     * 
     * @return el String con la ApuestaPrimitiva en el formato texto dado. 
     */
    public String toString() {
        /* COMPLETAR */
        // No possar coma despres de l'ultim nombre. 
        //Comprobar que estem en el penultim
        // Afegir al string sense la coma darrere.
        String respuesta="";
        combinacion.inicio();
        int contador = 0;
        while(contador < combinacion.talla() - 1){
            respuesta =respuesta + combinacion.recuperar() + ", ";
            combinacion.siguiente();
            contador++;
        }
        respuesta = respuesta + combinacion.recuperar();
        return respuesta;
    }
}
