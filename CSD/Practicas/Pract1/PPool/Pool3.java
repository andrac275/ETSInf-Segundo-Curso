// CSD feb 2015 Juansa Sendra

public class Pool3 extends Pool{ //max capacity
    int instructors = 0;
    int kids = 0;
    int maxKI;   //Maxim xiquets per instructor
    int maxCap; //Capacitat maxima de la piscina
    
    public synchronized void init(int ki, int cap) {//ki es xiquets per instructor
        maxKI =ki;
        maxCap = cap;
    } 
    public synchronized void kidSwims(){
        
        while((kids + 1) > (maxKI * instructors) || 
        (instructors + kids + 1) > maxCap){
            log.waitingToSwim();
            try{
                wait();
            } catch (InterruptedException e){}
        }
        kids++;
        log.swimming();
    }
    public synchronized void kidRests(){
        kids--;
        notifyAll();
        log.resting();        
    }
    public synchronized void instructorSwims(){
        while( (instructors + kids + 1) > maxCap){
            log.waitingToSwim();
            try{
                wait();
            } catch (InterruptedException e){}
        }
        instructors++;        
        notifyAll();
        log.swimming();        
    }
    public synchronized void instructorRests(){
        
        while(kids > (maxKI * (instructors - 1)) || 
        (instructors + kids - 1) > maxCap){
            log.waitingToRest();
            try{
                wait();
            } catch (InterruptedException e){}
        }
        instructors--;
        log.resting();        
    }
}
