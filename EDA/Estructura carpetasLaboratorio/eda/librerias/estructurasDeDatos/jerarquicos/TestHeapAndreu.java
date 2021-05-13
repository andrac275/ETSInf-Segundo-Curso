package librerias.estructurasDeDatos.jerarquicos;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.util.Ordenacion;
import java.util.Random;

/**
 * Write a description of class TestHeapAndreu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestHeapAndreu{
    public static void main(String[] args) {
        final int talla = 10;
        Random gen = new Random();
        Integer[] vec = new Integer[talla];
        
        ColaPrioridad<Integer> cua = new MonticuloBinarioR0<Integer>();
                
        
        System.out.println("Numeros sense ordenar.");
        for (int i = 0; i < talla; i++) {
            int num = gen.nextInt(1000);
            vec[i]=num; //guardar en un vector els valors
            System.out.print(num + " ");
            cua.insertar(num);

        }
         System.out.println("\n\nHe insertat els numeros anteriors en el MONTICLE \n");       
        
         System.out.println("Ara Ordene nombres del array amb el quick sort i queden aixi:");
        // Ordenar els valors del vector
            Ordenacion.quickSort(vec);
        
        
        for (int i = 0; i < talla; i++) {
            System.out.print(vec[i] + " ");

        }
        
        System.out.println("");
        //System.out.println("Min = "+cua.recuperarMin());
        
        System.out.println("\n\nEliminar un a un el minim del MONTICLE i el mostre per pantalla. ");
        while (!cua.esVacia()){
            System.out.print(cua.eliminarMin()+" ");
        }
        
    }
}
