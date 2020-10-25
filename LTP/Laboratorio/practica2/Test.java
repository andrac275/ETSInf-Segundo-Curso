package practica2;
import java.util.*;
import practica1.*;

/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class Estaticos {
    public static <T> void metodo(ArrayList<T> p){
        System.out.println(p);
    }
}

public class Test
{
    public static void main(String[] args){
        ArrayList<Figure> lf = new ArrayList<Figure>();
        lf.add(new Circle(1,2,3));
        Estaticos.metodo(lf);
    }
}
