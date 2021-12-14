package A;

public class Bee implements Runnable{
    Bear bear;
    Pot pot;
    int number;

    Bee (int number, Bear bear, Pot pot){
        this.number = number;
        this.bear = bear;
        this.pot = pot;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Bee #" + number + " brought honey");
            pot.addHoney();
            if (pot.isFull()) {
                System.out.println("Pot is FULL");
                bear.wakeUp();
            }
        }
    }
}