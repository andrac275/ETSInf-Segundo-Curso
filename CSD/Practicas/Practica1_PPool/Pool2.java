// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    int instructors = 0;
    int kids = 0;
    int maxKI;   //Maxim xiquets per instructor
    
    public synchronized void init(int ki, int cap) {//ki es xiquets per instructor
        maxKI =ki;
    } 
    public synchronized void kidSwims(){
        
        while((kids + 1) > (maxKI * instructors)){
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
        instructors++;
        
        notifyAll();
        log.swimming();        
    }
    public synchronized void instructorRests(){
        
        while(kids > (maxKI * (instructors - 1))){
            log.waitingToRest();
            try{
                wait();
            } catch (InterruptedException e){}
        }
        instructors--;
        log.resting();        
    }
}
