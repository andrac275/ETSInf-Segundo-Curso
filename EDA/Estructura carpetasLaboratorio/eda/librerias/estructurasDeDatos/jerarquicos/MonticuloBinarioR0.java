package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;

/** Clase MonticuloBinario: representa un Heap con Raiz en 1.
 * 
 *  Sus caracteristicas son las siguientes:
 *  @param <E>, el tipo de sus elementos es E extends Comparable<E>
 *  ATRIBUTOS (protected para la herencia): 
 *      TIENE UN array con los elementos (E[] elArray)
 *      TIENE UNA talla que indica el numero de elementos
 *      
 * @author (profesores EDA)
 * @version (Curso 2018-2019)
 **/
 
public class MonticuloBinarioR0<E extends Comparable<E>> 
    implements ColaPrioridad<E> {  
    
    protected static final int CAPACIDAD_POR_DEFECTO = 10;
    
    // Un Heap es un AB Completo y, por tanto,...
    // TIENE UNA Representacion Implicita
    protected E[] elArray;
    // TIENE UNA talla o numero de nodos
    protected int talla;
    
    /** Crea una Cola de Prioridad (CP) vacia 
     *  con capacidad inicial CAPACIDAD_POR_DEFECTO
     */
    @SuppressWarnings("unchecked")
    public MonticuloBinarioR0() {
        elArray = (E[]) new Comparable[CAPACIDAD_POR_DEFECTO];
        talla = 0;
    }
    
    /** Comprueba si una CP esta vacia o no 
     *  @return boolean que indica si la CP esta vacia
     */
    public boolean esVacia() { return talla == 0; }
        
    /** SII !esVacia(): obtiene el dato con maxima prioridad de la CP 
     *  @return Elemento con maxima prioridad de la CP
     */
     //Es guarda el mínim a elArray [0]
    public E recuperarMin() { return elArray[0]; }
   
    /** Inserta el dato e en una CP, atendiendo a su prioridad 
     *  @param e  Elemento a insertar  
     */
    public void insertar(E e) {
        //Duplicar si la talla es igual al tamany del array. En paper se veu per que es aixi, dibuixateu.
        if (talla == elArray.length) { duplicarArray(); }
        
        //Inserim inicialment a la primera posicio lliure del array.
            //PEr a R0, la primera posicio lliure esta en talla. No en ++talla que pasava abans.
            //Talla++ posa la talla que teniem en posActul i despres la incrementa.
        int  posActual = talla++;
        
        //Intercanviem la posicio de 'e' per la del seu pare fins a complir la propietat
        //Per a R0 la posicio del pare respecte el fill es (i-1)/2
        //Lo de l'esquerre dels && ara es posActual>0 en lloc de posActual>1 que era antes. 
            //Mentre el lloc on anem a insertar siga major que 0 es que no es l'arrel i busquem el lloc 
        while (posActual > 0 && e.compareTo(elArray[(posActual -1) / 2]) <  0) {
            elArray[posActual] = elArray[(posActual -1) / 2];
            posActual = (posActual -1) / 2;
        }
        elArray[posActual] = e;
    }
    // Duplica la capacidad de elArray
    @SuppressWarnings("unchecked")
    protected void duplicarArray() {
        E[] nuevo = (E[]) new Comparable[elArray.length * 2];
        //arrayCopy(Object origen, posPrimerElementoOrigen,Object destino, posPrimerElementoDestino, numElementosACopiar)
        System.arraycopy(elArray, 0, nuevo, 0, talla);
        elArray = nuevo;
    }  
    
    /** SII !esVacia(): obtiene y borra el dato con 
     *  maxima prioridad de la CP 
     *  @return Elemento con maxima prioridad de la CP
     */
    public E eliminarMin() {
        E elMinimo = recuperarMin();
        //Colocar en l'arrel l'element que esta en el numero de elements inserits - 1
        elArray[0] = elArray[talla - 1];
        talla--;//Reduir talla
        hundir(0); //Afonar el element de la pos 0
        return elMinimo;
    }
    
    //  Si es necesario, restablece la propiedad de orden 
    //  de un Heap hundiendo el elemento de elArray situado 
    //  en la posicion pos 
    //  @param pos  Posicion del elemento a hundir
    protected void hundir(int pos) {
        int posActual = pos;
        E aHundir = elArray[posActual]; 
        int hijo = (posActual * 2) + 1; //Ara el fill esquerra es (psActual * 2) + 1
        boolean esHeap = false;
        while (hijo < talla && !esHeap) {
            //Mirar quin dels dos fills que penjen es el menos i anar cap a ell.
            //Si fill següent (el de la dreta) menor que el de l'esquerre, avances a ell amb el hijo++
            if (hijo != (talla - 1) && elArray[hijo + 1].compareTo(elArray[hijo]) < 0) { 
                hijo++; //Aci es per anar cap al subArbre de la dreta
            }//Si no entrem al if implica que el de l'esquerre es menor i comparem en el subarbre
                //esquerra, pero si entrem, avancem i aleshores compare amb el subArbre de la dreta.
            
            //Comparar on estem apuntant ara amb l'element a afonar. (Subarbre esquerra o dreta)
            //Si arrel del subarbre on estem ara es menor que el pare, intercanviem posicions pare i fill
            if (elArray[hijo].compareTo(aHundir) < 0) {
                elArray[posActual] = elArray[hijo];
                posActual = hijo; 
                hijo = (posActual * 2) + 1; //Ara el fill es posActual * 2 + 1
            } else { esHeap = true; }
            
        }
        elArray[posActual] = aHundir;
    }
}
