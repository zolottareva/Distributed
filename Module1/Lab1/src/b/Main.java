package b;

import javax.swing.*;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static Thread thread1;
    private static Thread thread2;
    private static final JSlider slider = new JSlider();
    public static AtomicInteger semaphore = new AtomicInteger(0);
    private static final JButton startButton1 = new JButton("Start 1");
    private static final JButton startButton2 = new JButton("Start 2");
    private static final JButton stopButton1 = new JButton("Stop 1");
    private static final JButton stopButton2 = new JButton("Stop 2");
    private static final JLabel logsLabel = new JLabel("Another thread is currently running.");

    public static void main(String[] args) {
        renderer();
    }

    private static void onStartThread1() {
        if(semaphore.get() == 1) {
            logsLabel.setVisible(true);
            return;
        }
        thread1 = new Thread(new SemaphoreRunnable(10, slider));
        thread1.setDaemon(true);
        thread1.setPriority(1);
        thread1.start();
        
        logsLabel.setVisible(false);
    }

    private static void onStartThread2() {
        if(semaphore.get() == 1) {
            logsLabel.setVisible(true);
            return;
        }
        thread2 = new Thread(new SemaphoreRunnable(90, slider));
        thread2.setDaemon(true);
        thread2.setPriority(10);
        thread2.start();

        logsLabel.setVisible(false);
    }

    private static void onStopThread1() {
        if (semaphore.get() == 1) {
            thread1.interrupt();
            stopButton2.setEnabled(true);
            logsLabel.setVisible(false);
        }
    }

    private static void onStopThread2() {
        if (semaphore.get() == 1) {
            thread2.interrupt();
            stopButton1.setEnabled(true);
            logsLabel.setVisible(false);
        }
    }

    private static void renderer() {
        JFrame frame = new JFrame("B");

        slider.setBounds(50,30,300,50);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        logsLabel.setBounds(150, 200, 200, 50);

        startButton1.setBounds(50,100,150,50);
        startButton1.addActionListener(e -> onStartThread1());

        startButton2.setBounds(200,100,150,50);
        startButton2.addActionListener(e -> onStartThread2());

        stopButton1.setBounds(50,150,150,50);
        stopButton1.addActionListener(e -> onStopThread1());

        stopButton2.setBounds(200,150,150,50);
        stopButton2.addActionListener(e -> onStopThread2());

        frame.add(slider);
        frame.add(startButton1);
        frame.add(startButton2);
        frame.add(stopButton1);
        frame.add(stopButton2);
        frame.add(logsLabel);

        logsLabel.setVisible(false);

        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
