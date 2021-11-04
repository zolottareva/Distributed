package a;

import javax.swing.*;

public class Main {
    private static Thread thread1;
    private static Thread thread2;
    private static final JSlider slider = new JSlider();
    private static final JButton runButton = new JButton("Run");

    public static void main(String[] args) {
        renderer();

        thread1 = new Thread(new SimpleRunnable(10, slider));
        thread1.setDaemon(true);
        thread1.setPriority(1);

        thread2 = new Thread(new SimpleRunnable(90, slider));
        thread2.setDaemon(true);
        thread2.setPriority(1);
    }

    private static void onStart() {
        thread1.start();
        thread2.start();
        runButton.setEnabled(false);
    }

    private static void renderer() {
        JFrame frame = new JFrame("Lab 1 Task A");

        slider.setBounds(50,10,300,50);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);

        runButton.setBounds(100,200,200,40);
        runButton.addActionListener(e -> onStart());

        SpinnerModel firstModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner firstSpinner = new JSpinner(firstModel);
        firstSpinner.addChangeListener(e -> {
            int changedValue = (int) firstSpinner.getValue();
            thread1.setPriority(changedValue);
        });
        firstSpinner.setBounds(75, 100, 100, 20);

        SpinnerModel secondModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner secondSpinner = new JSpinner(secondModel);
        secondSpinner.addChangeListener(e -> {
            int changedValue = (int) secondSpinner.getValue();
            thread2.setPriority(changedValue);
        });
        secondSpinner.setBounds(200, 100, 100, 20);

        frame.add(slider);
        frame.add(runButton);
        frame.add(firstSpinner);
        frame.add(secondSpinner);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
