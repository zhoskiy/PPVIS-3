package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    public Point point;
    public Graphic graphic;
    private double coefficient = 0.05;

    public MouseListener(Graphic graphic) {
        this.graphic = graphic;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        point = event.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (point != null) {
            JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, graphic);
            if (viewPort != null) {

                int diffX = point.x - event.getX();
                int diffY = point.y - event.getY();

                Rectangle view = viewPort.getViewRect();

                view.x += coefficient * diffX;
                view.y += coefficient * diffY;

                graphic.scrollRectToVisible(view);
            }
        }
    }
}
