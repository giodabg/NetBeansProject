/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morepanel;

/**
 *
 * @author Gio
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This program demonstrates how to draw lines using Graphics2D object.
 *
 * @author www.codejava.net
 * 
 */
public class CanvasLine extends JFrame {

    public CanvasLine() {
        super("Lines Drawing Demo");

        setSize(480, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawLine(120, 50, 360, 50);
        g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));
        g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }

}
