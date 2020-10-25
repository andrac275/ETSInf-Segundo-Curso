package practica1;


/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Square extends Rectangle
{
    public Square(double x, double y, double b) {
        super(x, y, b);
        
    }

    public boolean equals(Object o) {
        if (!(o instanceof Square)) { return false; }
        Square s = (Square) o;
        return super.equals(s);
    }
    
    public String toString() {
        return "Square:\n\t" +
            super.toString();
    }
    
}
