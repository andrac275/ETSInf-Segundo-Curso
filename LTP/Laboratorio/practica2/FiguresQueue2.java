package practica2;
import practica1.*;
import practica2.librerias.implementaciones.QueueAL;
import practica2.librerias.modelos.Queue;
import java.util.*;

/**
 * Write a description of class FiguresQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FiguresQueue2<T extends Figure> extends QueueAL<T>
{
public static void main(String[] args){
    //Queue<String> a = new FiguresQueue2<String>();
    //Queue<Object> b = new FiguresQueue2<Object>();
    Queue<Circle> c = new FiguresQueue2<Circle>();
    Queue<Figure> f = new FiguresQueue2<Figure>();
    
    for (int i = 1; 1 <= 9; i++){
        c.enqueue(new Circle(0,0,i));
        //c.enqueue(new Triangle(0,0,i,i));
        //c.enqueue(new Integer(i));
    }
    
    // for (int i = 1; 1 <= 9; i++){
        // f.enqueue(new Circle(0,0,i));
        // f.enqueue(new Triangle(0,0,i,i));
        // //f.enqueue(new Integer(i));
    // }
    
}
}


