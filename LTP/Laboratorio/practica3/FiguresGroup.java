package practica3;
import java.util.*;
/**
 * class FiguresGroup.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class FiguresGroup implements Printable {
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
            if (figuresList[i].equals(f)) return true;
        }
        return false;
    }

    public boolean equals(Object o) {
        if (!(o instanceof FiguresGroup)) { return false; }
        FiguresGroup f = (FiguresGroup) o;
        return this.included(f) && f.included(this);
    }

    private boolean included(FiguresGroup g) {
        for (int i = 0; i < g.numF; i++) {
            if (!found(g.figuresList[i])) return false;
        }
        return true;
    }

    public double area(){ 
        double area_total = 0; 
        for (int i = 0; i <= numF-1; i++){
            area_total = area_total + figuresList[i].area(); 
        }
        return area_total; 
    } 

    public Figure greatestFigure(){
        double area = 0; 
        int x = 0;  
        for (int i = 0; i <= numF-1; i++){
            if (area <= figuresList[i].area())
                area = figuresList[i].area();
            x = i; 
        }
        return figuresList[x]; 
    }
    
    //Exercici 3
    public List<Figure> orderedList(){
        List<Figure> l = new ArrayList<Figure>();
        if(numF > 0) {
            l.add(figuresList[0]);
        }
        for(int i = 1;i<numF;i++){
            int j = l.size()-1; 
            while(j >=0 && figuresList[i].compareTo(l.get(j))<0){
                j--;
            }
            l.add(j+1, figuresList[i]); 
        }
        return l; 
    }
    
    public void print(char c) {
        for (int i = 0; i < numF; i++) {
            if(figuresList[i] instanceof Printable){
                ((Printable)figuresList[i]).print(c);
            }
        }
    }

}
