import java.util.concurrent.locks.*;

/**
 * Write a description of class Terrain1 here.
 * 
 * @author Andreu
 * @version 2021
 */
public class Terrain1 implements Terrain {
    Viewer v;
    ReentrantLock candado;
    Condition condicio;
    public  Terrain1 (int t, int ants, int movs) {
        v=new Viewer(t,ants,movs,"Terrain 1: General monitor, 1 general condition");
        candado = new ReentrantLock();
        condicio = candado.newCondition();
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
            condicio.signalAll();
            v.bye(a);
        }finally{candado.unlock();}
    }
    public void move(int a) throws InterruptedException {
        candado.lock();
        try{
            v.turn(a); 
            Pos dest=v.dest(a); 
            while (v.occupied(dest)) {condicio.await(); v.retry(a);}
            v.go(a);
            condicio.signalAll();
        }finally{candado.unlock();}
    }
}