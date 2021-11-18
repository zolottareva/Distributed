package A;

public class Pot {

    private int capacity;
    private int curr;

    Pot(int cap){
        this.capacity = cap;
        curr = 0;
    }

    public synchronized void eatAll(){
        System.out.println("Pot is EMPTY");
        curr = 0;
        notifyAll();
    }

    public synchronized void addHoney(){
        while(isFull()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        curr++;
    }
    public synchronized boolean isFull(){
        return curr == capacity;
    }
}
