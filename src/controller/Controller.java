package controller;

import model.FunctionOne;
import model.FunctionTwo;
import view.MainFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Controller {
    public MainFrame mainFrame;
    private FunctionOne functionOne;
    private FunctionTwo functionTwo;
    private Lock lock;
    private List<Thread> threads;

    public Controller(MainFrame mainFrame, Lock lock) {
        this.mainFrame = mainFrame;
        this.lock = lock;
        this.functionOne = new FunctionOne(1, lock, mainFrame );
        this.functionTwo = new FunctionTwo(1, 2, lock, mainFrame);
        this.threads = new ArrayList<>();
    }


    public void startFunctionOneThread(int n) {
        this.functionOne = new FunctionOne(n, lock, mainFrame );
        Thread threadOne = new Thread(functionOne);
        threads.add(threadOne);
        threadOne.start();
    }

    public void startFunctionTwoThread(int n, int k) {
        this.functionTwo = new FunctionTwo(n, k, lock, mainFrame);
        Thread threadTwo = new Thread(functionTwo);
        threads.add(threadTwo);
        threadTwo.start();
    }

    public void stopThreads() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        threads.clear();
    }
}
