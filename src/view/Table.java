package view;

import model.Point;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class Table {

    private JTable table;
    private Vector<String> columns;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;


    JScrollPane createTable() {
        columns = new Vector<>();
        tableModel = new DefaultTableModel(columns, 0);
        columns.add("n");
        columns.add("y = f(n)");
        table = new JTable();
        table.setModel(tableModel);
        scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(new Dimension(150, 350));
        return scrollPane;
    }

    public void clear() {
        tableModel = new DefaultTableModel(columns, 0);
        table.setModel(tableModel);
    }

    public void addPoint(Point point) {
        Vector<Double> vector = new Vector<>();
        vector.add(point.getX());
        vector.add(point.getY());
        tableModel.addRow(vector);
    }
}