package C;

import java.util.Random;

public class Monk implements Comparable {
    private Integer energy;
    private String monastery;

    public Monk() {
        energy = new Random().nextInt(100);
        monastery = (new Random().nextInt(2) == 0) ? "Huan-un" : "Huan-in";
    }

    @Override
    public String toString() {
        return "Monk lvl " + energy + " from " + monastery;
    }

    public int compareTo(Object o) {
        Monk other = (Monk)o;
        if(this.energy > other.energy) {
            return 1;
        } else if (this.energy < other.energy){
            return -1;
        } else {
            return 0;
        }
    }

    static Monk max(Monk first, Monk second){
        if(first.energy > second.energy){
            return first;
        } else {
            return second;
        }
    }
}
