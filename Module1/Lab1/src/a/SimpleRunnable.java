package a;

import javax.swing.*;

public class SimpleRunnable implements Runnable{
    private final int value;
    private final JSlider slider;

    public SimpleRunnable(int value, JSlider slider) {
        this.slider = slider;
        this.value = value;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (slider){
                slider.setValue(value);
            }
        }
    }
}
