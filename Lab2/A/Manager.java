package A;

import java.util.Random;

public class Manager {
    private boolean[][] forest;
    private int currTask = -1;
    private boolean foundWinni = false;

    public Manager(int x, int y) {
        forest = new boolean[x][y];
        locateWinni();
    }

    public boolean isFoundWinni() {
        return foundWinni;
    }

    public void setFoundWinni(boolean foundWinni) {
        this.foundWinni = foundWinni;
    }

    public synchronized Task getTask() {
        if(currTask + 1 < forest.length) {
            return new Task(forest[++currTask], currTask);
        }
        return null;
    }

    private void locateWinni() {
        int winnisX = new Random().nextInt(forest.length - 1);
        int winnisY = new Random().nextInt(forest[0].length - 1);
        forest[winnisX][winnisY] = true;
    }

    public class Task{
        private boolean [] area;
        private int y;

        public Task(boolean[] area, int y) {
            this.area = area;
            this.y = y;
        }

        public boolean[] getArea() {
            return area;
        }

        public int getY() {
            return y;
        }
    }
}