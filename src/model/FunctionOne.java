package model;

import view.MainFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class FunctionOne implements Runnable {
    public double beginI = 0;
    public double endI;
    private Lock lock;
    public static final int FUNCTION_ID = 0;
    private MainFrame frame;

    public FunctionOne( int n, Lock lock, MainFrame frame) {
        this.lock = lock;
        this.endI = n;
        this.frame = frame;
    }

    public double function(double x) {
        return 3 * x + 5;
    }


    @Override
    public void run() {
        double beginX = beginI;
        double endX = endI;
        double tempFx;
        double step = 1;
        for (double x = beginX; x <= endX; x += step) {
            tempFx = function(x);
            tempFx = Math.round(tempFx * 10d) / 10d;
            x = Math.round(x * 10d) / 10d;
            lock.lock();
            try {
                Point secondPoint = new Point(x, tempFx);
                frame.getGraphic().addValue(FUNCTION_ID, secondPoint);
                frame.getGraphic().repaint();
            } finally {
                lock.unlock();
            }
            try {
                int sleepTime = 1000;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                x=endX;
            }
        }

    }
}
