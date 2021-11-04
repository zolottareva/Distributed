package C;

import java.util.concurrent.ForkJoinPool;

public class C {
    public static void main(String[] args) {
        Integer numberOfMonks = 50;
        Tournament tournament = new Tournament(numberOfMonks);
        ForkJoinPool pool = new ForkJoinPool();
        Monk winner = pool.invoke(tournament);
        System.out.println("The winner is " + winner.toString());
    }
}