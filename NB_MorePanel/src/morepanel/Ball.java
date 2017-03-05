/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morepanel;

import java.awt.Dimension;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import javax.swing.JPanel;

/**
 *
 * @author Gio
 */
public class Ball extends Thread {

    private JPanel box;
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private int x;
    private int y;
    private int dx;
    private int dy;

    public Ball(JPanel b) {
        box = b;
        x = 0;
        y = 0;
        dx = 2;
        dy = 2;
    }

    public void draw() {
        Graphics g = box.getGraphics();
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    public void move() {
        if (!box.isVisible()) {
            return;
        }
        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
        g.fillOval(x, y, XSIZE, YSIZE);
        x += dx;
        y += dy;
        Dimension d = box.getSize();
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= d.width) {
            x = d.width - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= d.height) {
            y = d.height - YSIZE;
            dy = -dy;
        }
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    public void run() {
        try {
            draw();
            while (!BounceThreadFrame.stop) {
                move();
                sleep(5);
            }
        } catch (InterruptedException e) {
        }
    }

}

