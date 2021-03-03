// CSD feb 2015 Juansa Sendra

public class Pool1 extends Pool {   //no kids alone
    int instructors = 0;
    int kids = 0;
    
    public void init(int ki, int cap) {} //ki es xiquets per instructor
    public synchronized void kidSwims(){
        while(instructors < 1){
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
        while(kids > 0 && instructors <=1){
        log.waitingToRest();
        try{
            wait();
        } catch (InterruptedException e){}
        }
        instructors--;
        log.resting();        
    }
}
