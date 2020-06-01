package view;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends Panel {
    public JTextField nValue;
    public JTextField kValue;
    private JLabel nLabel = new JLabel("n = ");
    private JLabel kLabel = new JLabel("k = ");
    private JButton buttonStart  = new JButton("Рисовать");;
    private JButton buttonStop = new JButton("Остановить");
    private JButton buttonCenter = new JButton("Начало координат");
    private JButton buttonClear = new JButton("Очистить");
    private JButton increase = new JButton("+");
    private JButton decrease = new JButton("-");

    public ControlPanel() {
        nValue = new JTextField(4);
        kValue = new JTextField(4);
        setPreferredSize(new Dimension(350, 35));
        add(nLabel);
        add(nValue);
        add(kLabel);
        add(kValue);
        add(buttonStart);
        add(buttonStop);
        add(buttonClear);
        add(buttonCenter);
        add(increase);
        add(decrease);
        nValue.setText("100");
        kValue.setText("500");
        setVisible(true);

    }

   public int getN() {
        String stringValue = nValue.getText();
        if (!stringValue.equals("")) {
            return Integer.parseInt(stringValue);
        } else {
            return 10;
        }

    }

   public int getK() {
        String stringValue = kValue.getText();
        if (!stringValue.equals("")) {
            return Integer.parseInt(stringValue);
        } else {
            return 100;
        }

    }

    public JButton getButtonStart() {
        return buttonStart;
    }

    public JButton getButtonStop() {
        return buttonStop;
    }

    public JButton getButtonClear() {
        return buttonClear;
    }

    public JButton getIncrease() {
        return increase;
    }

    public JButton getDecrease() {
        return decrease;
    }

    public JButton getButtonCenter() {
        return buttonCenter;
    }
}
