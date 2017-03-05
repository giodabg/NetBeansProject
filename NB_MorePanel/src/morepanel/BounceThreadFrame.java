/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 * http://www.javamex.com/tutorials/threads/invokelater.shtml

 */
package morepanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BounceThreadFrame extends JFrame implements ActionListener {

    private JPanel canvas;
    Ball b;
    JButton b1, b2;
    static Boolean stop;

    public BounceThreadFrame() {
        setTitle("Bounce");

        setSize(300, 200);
        setLocationRelativeTo(null);

        stop = false;
        Container contentPane = getContentPane();
        canvas = new JPanel();
        contentPane.add(canvas, "Center");
        JPanel p = new JPanel();

        b1 = new JButton("Start");
        p.add(b1);
        b1.addActionListener(this);

        b2 = new JButton("Close");
        p.add(b2);
        b2.addActionListener(this);

        contentPane.add(p, "South");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            startAction();
        } else if (e.getSource() == b2) {
            stopAction();
        }
    }

    private void startAction() {
        stop = false;
        if (b == null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    b = new Ball(canvas);
                    b.start();
                }
            });
        }
    }

    private void stopAction() {
        try {
            stop = true;
            if (b != null) {
                b.join();
                b = null;
            }
            setVisible(false);
            dispose();
            // System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(BounceThreadFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
