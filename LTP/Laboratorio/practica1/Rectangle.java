package practica1;


/**
 * Write a description of class Rectangle here.
 * 
 * @author Andreu
 * @version Octubre 2020
 */
public class Rectangle extends Figure
{
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
}
