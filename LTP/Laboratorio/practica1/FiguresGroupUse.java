package practica1;


/**
 * class FiguresGroupUse.
 * 
 * @author LTP 
 * @version 2020-21
 */
public class FiguresGroupUse {
    public static void main(String[] args) {
        // //Equals 
        // FiguresGroup g = new FiguresGroup();        
        // g.add(new Circle(10, 5, 4));  
        // g.add(new Triangle(1,2,3,4));
        // g.add(new Rectangle(1,2,3,4));
        // g.add(new Square(10, 5, 3));
        // System.out.println(g);
        
        // FiguresGroup g2 = new FiguresGroup();
        // g2.add(new Circle(10, 5, 4));
        // g2.add(new Triangle(1,2,3,4));
        // g2.add(new Rectangle(1,2,3,4));
        // g2.add(new Square(10, 5, 3));
        // System.out.println(g2);
                
        // System.out.println(g.equals(g2));
        
        // Area
        Square s = new Square(1 , 2 , 3);
        Circle c = new Circle(10, 5, 4);
        Triangle t = new Triangle(1,2,10,5);
        Rectangle r = new Rectangle(1,2,3,4);
        System.out.println(s.area() + "\n" + c.area() + "\n" 
            + t.area() + "\n" + r.area() + "\n");
        
        // Figura mes gran
        FiguresGroup g3 = new FiguresGroup();
        g3.add(new Square(1 , 2 , 3));
        g3.add(new Circle(10, 5, 4));
        g3.add(new Triangle(1,2,10,5));
        g3.add(new Rectangle(1,2,3,4));   
        
        System.out.println("El area mes gran es: " 
            + g3.greatestFigure() + " amb valor " + g3.greatestFigure().area());
        
        
    }
}