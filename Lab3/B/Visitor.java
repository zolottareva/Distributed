package B;

public class Visitor implements Runnable {

    private final Barber barber;

    public Visitor(Barber barber) {
        this.barber = barber;
    }

    @Override
    public void run() {
        barber.addVisitorToQueue(this);
    }
}
