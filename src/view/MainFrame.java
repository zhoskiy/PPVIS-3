package view;

import controller.Controller;
import model.FunctionOne;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class MainFrame extends Frame {
    public JFrame frame;
    public Controller controller;
    private ControlPanel controlPanel;
    private Table table;
    private Graphic graphic;
    public JScrollPane scroll;
    private ReentrantLock lock;

    public MainFrame() {


        lock = new ReentrantLock();
        controller = new Controller(this, lock);
        frame = new JFrame();
        controlPanel = new ControlPanel();

        frame.setTitle("График функции");

        frame.setMinimumSize(new Dimension(900, 700));
        frame.setMaximumSize(new Dimension(900, 700));

        setMaximumSize(new Dimension(1000, 800));
        setMinimumSize(new Dimension(1000, 800));
        setLocationRelativeTo(null);

       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        graphic = new Graphic(controller);
        table = new Table();

        scroll = new JScrollPane(graphic);
        scroll.setPreferredSize(new Dimension(600, 500));
        scroll.setAutoscrolls(false);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);


       frame.add(table.createTable(), BorderLayout.EAST);
       frame.add(scroll, BorderLayout.CENTER);
       frame.add(controlPanel, BorderLayout.NORTH);

        MouseListener listener = new MouseListener(graphic);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        scroll.getViewport().addMouseListener(listener);
        scroll.getViewport().addMouseMotionListener(listener);
        scroll.getHorizontalScrollBar().setValue(scroll.getHorizontalScrollBar().getMaximum()/2);
        scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum()/2);

        ZoomListener zoomListener = new ZoomListener(this, graphic);

        frame.setVisible(true);

        controlPanel.getButtonStart().addActionListener(actionEvent -> {
            controller.stopThreads();
            table.clear();
            if(graphic.getFunctionsData().isEmpty())
            {
                graphic.initialization();
            }
            drawing();
        });

        controlPanel.getButtonClear().addActionListener(actionEvent -> {
            table.clear();
            graphic.clear();
            controller.stopThreads();
        });

        controlPanel.getIncrease().addActionListener(actionEvent -> {
            zoomListener.increase();
        });

        controlPanel.getDecrease().addActionListener(actionEvent -> {
            zoomListener.decrease();
        });

        controlPanel.getButtonStop().addActionListener(actionEvent ->  {
            controller.stopThreads();


        });
        controlPanel.getButtonCenter().addActionListener(actionEvent -> {
            scroll.getHorizontalScrollBar().setValue(scroll.getHorizontalScrollBar().getMaximum()/5*2);
            scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum()/5*2);
        });
    }

    public void drawing() {
        controller.startFunctionOneThread(controlPanel.getN());
        controller.startFunctionTwoThread(controlPanel.getN(), controlPanel.getK());
    }


    public Graphic getGraphic() {
        return graphic;
    }

    public Table getTable() {
        return table;
    }

    public static void main(String[] args) {
        new MainFrame();
    }

}
