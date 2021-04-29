import java.util.concurrent.locks.*;

/**
 * Write a description of class Terrain1 here.
 * 
 * @author Andreu
 * @version 2021
 */
public class Terrain2 implements Terrain {
    Viewer v;
    ReentrantLock candado;
    Condition condicio[][];
    public  Terrain2 (int t, int ants, int movs) {
        v=new Viewer(t,ants,movs,"Terrain 2: General monitor, 1 condition per cell");
        candado = new ReentrantLock();
        condicio = new Condition[t][t];
        for(int i = 0; i < t; i++){
            for(int j = 0; j < t; j++){
                condicio[i][j] = candado.newCondition();
            }
        }
        
        for (int i=0; i<ants; i++) new Ant(i,this,movs).start();
        
        
    }
    public void hi(int a) {
        candado.lock();
        try{
            v.hi(a);
        }finally{candado.unlock();}
    }
    public void bye(int a) {
        candado.lock();
        try{                
            v.bye(a);
        }finally{candado.unlock();}
    }
    public void move(int a) throws InterruptedException {
        candado.lock();
        try{
            v.turn(a); 
            Pos actual = v.getPos(a);
            Pos dest=v.dest(a); 
            while (v.occupied(dest)) {condicio[dest.x][dest.y].await(); v.retry(a);}
            v.go(a);
            condicio[actual.x][actual.y].signalAll();
        }finally{candado.unlock();}
    }
}