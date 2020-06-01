package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ZoomListener  {

    public Graphic graphic;
    public MainFrame mainFrame;

    public ZoomListener(MainFrame frame, Graphic graphic) {
        this.mainFrame = frame;
        this.graphic = graphic;

    }

    public void increase(){
        graphic.incrementUnitSegment();
        graphic.repaint();
    }
    public void decrease(){
        graphic.decrementUnitSegment();
        graphic.repaint();
    }

}
