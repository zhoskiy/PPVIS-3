package view;

import controller.Controller;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graphic extends JPanel {

    private int width = 500;
    private int height = 500;
    public List<Point> valuesLinear;
    public int fontSize;
    public Dimension firstSize;
    public Dimension size;
    public Dimension center;
    private Controller controller;
    private Graphics graph;
    private String zero = "0";
    private List<List<Point>> functionsData;
    private int unitSegmentCoefficient = 15;
    private int extendRatio = 2000;

    public Graphic(Controller controller) {

        valuesLinear = new ArrayList<Point>();
        size = new Dimension(width, height);
        center = new Dimension(width / 2, height / 2);
        setPreferredSize(size);
        setSize(size);
        fontSize = 10;
        firstSize = new Dimension(600, 500);
        this.controller = controller;
        functionsData = new ArrayList<>();
        functionsData.add(new ArrayList<>());
        functionsData.add(new ArrayList<>());
    }

    public void incrementUnitSegment() {
            this.unitSegmentCoefficient++;
    }

    public void decrementUnitSegment() {
        if (unitSegmentCoefficient > 5)
            this.unitSegmentCoefficient--;
    }

    private void drawAxis(Graphics graph) {
        center = new Dimension(size.width / 2, size.height / 2);

        graph.drawLine(10, size.height / 2, size.width - 1, size.height / 2);
        graph.drawLine(size.width / 2, 10, size.width / 2, size.height - 1);
        graph.drawLine(size.width - 30, size.height / 2 - 10, size.width - 1, size.height / 2);
        graph.drawLine(size.width - 30, size.height / 2 + 10, size.width - 1, size.height / 2);
        graph.drawLine(size.width / 2, 10, size.width / 2 - 10, 30);
        graph.drawLine(size.width / 2, 10, size.width / 2 + 10, 30);


        graph.drawString("X", size.width - 20, size.height / 2 + 20);
        graph.drawString("Y", size.width / 2 - 20, 20);
        graph.drawString(zero, size.width / 2 - 10, size.height / 2 + 10);

        int counter = 1;

        for (int index = (int) size.getWidth() / 2; index < size.width; index += unitSegmentCoefficient) {
            graph.drawLine((index), size.height / 2, (index),
                    size.height / 2 + 3);
            graph.drawString(Integer.toString(counter), index + unitSegmentCoefficient,
                    (int) size.getHeight() / 2 - 5);
            counter++;
        }
        counter = -1;
        for (int index = (int) size.getHeight() / 2; index < size.height; index += unitSegmentCoefficient) {
            graph.drawLine(size.width / 2 - 3, index, size.width / 2, index);
            graph.drawString(Integer.toString(counter), (int) size.getWidth() / 2, index + unitSegmentCoefficient);
            counter--;
        }
        counter = -1;
        for (int index = (int) size.getWidth() / 2; index > 0; index -= unitSegmentCoefficient) {
            graph.drawLine((index), size.height / 2, (index),
                    size.height / 2 + 3);
            graph.drawString(Integer.toString(counter), index - unitSegmentCoefficient, (int) size.getHeight() / 2 - 5);
            counter--;
        }
        counter = +1;
        for (int index = (int) size.getHeight() / 2; index > 0; index -= unitSegmentCoefficient) {
            graph.drawLine(size.width / 2 - 3, index, size.width / 2, index);
            graph.drawString(Integer.toString(counter), (int) size.getWidth() / 2, index - unitSegmentCoefficient);
            counter++;
        }
    }

    public void drawFunction(List<Point> values, int i) {
        for (int index = 1; index < values.size(); index++) {

            if(i==1)
                graph.setColor(Color.RED);
            else
                graph.setColor(Color.GREEN);

            double tempFx = (values.get(index)).getY();
            double tempX = (values.get(index)).getX();
            double prevFx = (values.get(index - 1)).getY();
            double prevX = (values.get(index - 1)).getX();
            int newFx = (int) (unitSegmentCoefficient * tempFx);
            int newX = (int) (unitSegmentCoefficient * tempX);
            int newprevFx = (int) (unitSegmentCoefficient * prevFx);
            int newPrevX = (int) (unitSegmentCoefficient * prevX);
            int drawPrevX = center.width + newPrevX;
            int drawPrevY = center.height - newprevFx;
            int drawX = center.width + newX;
            int drawY = center.height - newFx;
            graph.drawLine(drawPrevX, drawPrevY, drawX, drawY);

        }
    }

    private void extendChart(int pointX, int pointY) {
        while (pointX * unitSegmentCoefficient > getSize().getWidth() / 2 || pointY * unitSegmentCoefficient > getSize().getHeight() / 2) {
            setSize((int) getSize().getWidth() + extendRatio,
                    (int) getSize().getHeight() + extendRatio);
            this.size = getSize();
            setPreferredSize(size);
            revalidate();
        }
    }

    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        graphic.setColor(Color.BLACK);
        size = new Dimension(this.getWidth(), this.getHeight());
        Graphics2D graph = (Graphics2D) graphic;
        this.graph = graph;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setFont(new Font("Font", Font.BOLD, fontSize));
        drawAxis(graph);

        if(!functionsData.isEmpty()) {
            drawFunction(functionsData.get(0), 0);
            drawFunction(functionsData.get(1), 1);
        }
    }

    public void clear() {
        this.size = firstSize;
        setSize(size);
        setPreferredSize(size);
        revalidate();
        functionsData.clear();
    }

    public void addValue(int id, Point point) {
        if (!functionsData.isEmpty()) {
            functionsData.get(id).add(point);
            extendChart((int) point.getX(), (int) point.getY());
        }
    }
    public List<List<Point>> getFunctionsData() {
        return functionsData;
    }
    public void initialization(){

        functionsData.add(new ArrayList<>());
        functionsData.add(new ArrayList<>());
    }
}
