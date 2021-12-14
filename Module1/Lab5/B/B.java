import java.util.concurrent.CyclicBarrier;

public class B {
    private static final int NUMBER_OF_THREADS = 4;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_THREADS);
        Checker checker = new Checker(NUMBER_OF_THREADS);

        Thread c1 = new Thread(new Changer("ABCDCDAABCD", barrier, checker, 1));
        Thread c2 = new Thread(new Changer("AAACAACBBAC", barrier, checker, 2));
        Thread c3 = new Thread(new Changer("ACDCADCACDC", barrier, checker, 3));
        Thread c4 = new Thread(new Changer("CDABBABCDAB", barrier, checker, 4));

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}