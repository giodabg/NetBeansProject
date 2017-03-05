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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;

/**
 * This program demonstrates how to draw lines using Graphics2D object.
 *
 * @author www.codejava.net
 *
 */
public class CanvasLineSimple extends JFrame {

    public CanvasLineSimple() {
        super("Lines Drawing Demo");

        setSize(480, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void paint(Graphics g) {
        super.paint(g);
            Random rn = new Random();

        for (int i = 0; i < 100; i++) {
            int number = rn.nextInt(100) + 1;
            g.setColor(new Color(number, number, number));
            g.drawLine(10+i*2, 50, 10+i*2, 50+number);
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
        g.setColor(Color.DARK_GRAY);
        g.drawString("Linee parallele", 10, 50);
    }
}
