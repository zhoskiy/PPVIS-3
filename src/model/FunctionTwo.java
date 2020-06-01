package model;


import controller.Sort;
import view.MainFrame;

import java.util.concurrent.locks.Lock;

public class FunctionTwo implements Runnable {
    public static final int FUNCTION_ID = 1;
    private int n;
    private int k;
    private Lock lock;
    private MainFrame frame;
    private int sleepTime;
    private  Sort sort;


    public FunctionTwo(int n, int k, Lock lock, MainFrame frame) {
        this.n = n;
        this.k = k;
        this.lock = lock;
        this.frame = frame;
        sleepTime = 1000;
    }

    @Override
    public void run() {
        for (int currentSize = 2; currentSize <= n; currentSize++) {
                double commonTime = 0;
                for (int currentArrayCount = 1; currentArrayCount < k; currentArrayCount++) {
                    sort = new Sort(currentSize);
                    commonTime += sort.getTime();
                }
                double averageTime = commonTime / (10*k);
            lock.lock();
            try {
                Point point = new Point(currentSize, averageTime);
                frame.getGraphic().addValue(FUNCTION_ID, point);
                frame.getTable().addPoint(point);
                frame.getGraphic().repaint();
            } finally {
                lock.unlock();
            }

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                currentSize = n;
            }
        }
    }
}
