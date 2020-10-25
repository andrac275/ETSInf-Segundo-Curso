package practica3;
import java.util.*;


/**
 * Write a description of class Rectangle here.
 * 
 * @author Andreu
 * @version Octubre 2020
 */
public class Rectangle extends Figure implements ComparableRange<Figure> , Printable {
    private double base;
    private double height;

    public Rectangle(double x, double y, double b, double h) {
        super(x, y);
        base = b;
        height = h;
    }
    
    public Rectangle(double x, double y, double b) {
        super(x, y);
        base = b;
        height = b;
    }
    

    public boolean equals(Object o) {
        if (!(o instanceof Rectangle)) { return false; }
        Rectangle r = (Rectangle) o;
        return super.equals(r) && base == r.base && height == r.height;
    }
    
    public String toString() {
        return "Rectangle:\n\t" +
            super.toString() +
            "\n\tBase: " + base +
            "\n\tHeight: " + height;
    }
    
    public double area() {
        return (base * height);
    }
    
    public double perimeter(){
        return 2 * (base * height);
    }
    
    //Exercici 5
    public int compareToRange(Figure r){
        if(!(r instanceof Rectangle)){
            throw new ClassCastException("no es manté la nonotonicitat de la compatibilitat de tipus perquè les variables de tipus s'intancien en compilació i no en execució ");
        }
        double diferencia = Math.abs(this.area() - r.area());
        double sumaArees = this.area() + r.area();
        if(sumaArees*0.1 >= diferencia ){
            return 0;
        }else  {
            return this.compareTo(r);
        }  
    }
    
    //Exercici 7
    public void print(char c){
        int b = (int) base;
        int h = (int) height;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < b; j++) {
                System.out.print(c);
            }
            System.out.println();
        } 
    }
}
