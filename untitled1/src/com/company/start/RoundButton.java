package com.company.start;

import javax.swing.*;
import java.awt.*;

public class RoundButton extends JButton {
    public RoundButton(String label) {
        super(label);
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 20, 20);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 20, 20);
    }
}
