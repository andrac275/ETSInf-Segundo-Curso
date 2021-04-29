// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    public LimitedTable(StateManager state) {super(state);}
    int genteMesa = 0;
    
    public synchronized void enter(int id) throws InterruptedException {
        try{
            while(genteMesa ==4) {wait();}
            genteMesa++;
            state.enter(id);
        }catch (InterruptedException ex) {}
    }
    public synchronized void exit(int id)  {
        genteMesa--;
        state.exit(id);
        notifyAll();
    }
}
