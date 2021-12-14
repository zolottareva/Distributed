package A;

public class A {
    public static void main(String[] args) {
        Bees.setManager(new Manager(50,50));
        int beesTeams = 5;
        Thread[] threads = new Thread[beesTeams];
        for (int i = 0; i < beesTeams; i++) {
            threads[i] = new Thread(new Bees());
            threads[i].start();
        }
        for (int i = 0; i < beesTeams; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Winni's coords: (" + Bees.getWinniX() + "," + Bees.getWinniY() + ")");
    }
}