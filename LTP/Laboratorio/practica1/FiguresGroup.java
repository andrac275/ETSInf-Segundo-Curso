package practica1;


/**
 * class FiguresGroup.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class FiguresGroup {
    private static final int NUM_FIGURES = 10;
    private Figure[] figuresList = new Figure[NUM_FIGURES];
    private int numF = 0;
    
    public void add(Figure f) { figuresList[numF++] = f; }
    
    public String toString() {
        String s = "";
        for (int i = 0; i < numF; i++) {
            s += "\n" + figuresList[i];
        }
        return s;
    }

    private boolean found(Figure f) {
        for (int i = 0; i < numF; i++) {
            if (figuresList[i].equals(f)) {return true;}
        }
        return false;
    }

    private boolean included(FiguresGroup g) {
        for (int i = 0; i < g.numF; i++) {
            if (!found(g.figuresList[i])) {return false;}
        }
        return true;
    }
    
    public boolean equals(Object o){
        boolean res = false;
        if (!(o instanceof FiguresGroup)) { return false; }
        FiguresGroup g = (FiguresGroup) o;
        if (this.included(g) && g.included(this)) {res = true;}
        return res;
    }
    
    public double area() {
        double suma = 0;
            for (int i = 0; i < numF; i++) {
                suma = suma + figuresList[i].area();
            }
        return suma;
    }
    
    public Figure greatestFigure() {
        Figure res = null;
        double aux = 0;
            for (int i = 0; i < numF; i++) {
                double area = figuresList[i].area();
                if (area > aux) {
                    aux = area;
                    res = figuresList[i];
                }
            }
        return res;        
    }
}