package practica1;


/**
 * class Circle.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class Circle extends Figure {
    private double radius;

    public Circle(double x, double y, double r) {
        super(x, y);
        radius = r;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Circle)) { return false; }
        Circle c = (Circle) o;
        return super.equals(c) && radius == c.radius;
    }
    
    public String toString() {
        return "Circle:\n\t" +
            super.toString() +
            "\n\tRadius: " + radius;
    }
    
    public double area() {
        return Math.PI * radius * radius;
    }
    
    public double perimeter(){
        return 2 * Math.PI * radius;
    }
}