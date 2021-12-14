package b;

import javax.swing.*;

public class SemaphoreRunnable implements Runnable{
    private final int value;
    private final JSlider slider;

    public SemaphoreRunnable(int value, JSlider slider) {
        this.slider = slider;
        this.value = value;
    }

    @Override
    public void run() {
        if(b.Main.semaphore.compareAndSet(0,1)) {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (slider) {
                    slider.setValue(value);
                }
            }
            Main.semaphore.set(0);
        }
    }
}
