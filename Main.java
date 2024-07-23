package org.example;

import org.example.index.Complex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.MathContext;

public class Main extends JFrame implements KeyListener {
    static float w = 0.2f;
    static float getTheta = 0f;
    static float getPhi = -0.25f;
    static float theta = (float) (getTheta * Math.PI * 2);
    static float phi = (float) (getPhi * Math.PI * 2);

    int width = 1200;
    int height = 700;
    float mag = 50;

    public static void main(String[] args) throws InterruptedException {
        JPanel p = new JPanel();
        JFrame frame = new Main(p);
        JLabel w_label = new JLabel("w = " + w);
        p.add(w_label);
        while (true) {
            Thread.sleep(50);
            theta = (float) (getTheta * Math.PI * 2);
            phi = (float) (getPhi * Math.PI * 2);
            w += 0f;
            w_label.setText("w = " + w);
            frame.repaint();
        }
    }
    Main(JPanel p) {
        ImageIcon icon = new ImageIcon("your icon's file space!");
        setTitle("your frame's name");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width,height);
        setIconImage(icon.getImage());

        addKeyListener(this);

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.NORTH);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> getPhi += 0.005f;
            case KeyEvent.VK_DOWN -> getPhi -= 0.005f;
            case KeyEvent.VK_LEFT -> getTheta -= 0.005f;
            case KeyEvent.VK_RIGHT -> getTheta += 0.005f;
            case KeyEvent.VK_R -> w = 0;
            case KeyEvent.VK_W -> mag += 0.1f;
            case KeyEvent.VK_S -> mag -= 0.1f;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        baseLine(g);
        g.setColor(Color.black);

        g.setColor(Color.black);

        g.setColor(Color.black);
        FractionalFunctionLine(g);
    }
    private void baseLine(Graphics g) {
        Complex[] a = new Complex[5];
        a[0] = new Complex(-width * mag / 2, 0);
        a[1] = new Complex(width * mag / 2, 0);
        a[2] = new Complex(0, width * mag);
        a[3] = new Complex(0, -width * mag);
        a[4] = new Complex(0, 0);
        Complex[] b = new Complex[6];
        b[0] = Complex.rotate(a[0], 0, theta, phi);
        b[1] = Complex.rotate(a[1], 0, theta, phi);
        b[2] = Complex.rotate(a[2], 0, theta, phi);
        b[3] = Complex.rotate(a[3], 0, theta, phi);
        b[4] = Complex.rotate(a[4], -width * mag / 2, theta, phi);
        b[5] = Complex.rotate(a[4], width * mag / 2, theta, phi);
        g.setColor(Color.red);
        g.drawLine((int) b[0].complexFloat(true) + width / 2, (int) b[0].complexFloat(false) + height / 2, (int) b[1].complexFloat(true) + width / 2, (int) b[1].complexFloat(false) + height / 2);
        g.setColor(Color.green);
        g.drawLine((int) b[2].complexFloat(true) + width / 2, (int) b[2].complexFloat(false) + height / 2, (int) b[3].complexFloat(true) + width / 2, (int) b[3].complexFloat(false) + height / 2);
        g.setColor(Color.blue);
        g.drawLine((int) b[4].complexFloat(true) + width / 2, (int) b[4].complexFloat(false) + height / 2, (int) b[5].complexFloat(true) + width / 2, (int) b[5].complexFloat(false) + height / 2);
    }
    private void QuadraticFunctionLine(Graphics g) {
        for (float y = -10; y <= 10; y += 0.1f) {
            for (int p = 0; p <= 3; p++) {
                int[] s = new int[2];
                if (p % 4 <= 1) s[0] = -1;
                else s[0] = 1;
                if (p % 2 < 1) s[1] = -1;
                else s[1] = 1;
                Complex[] xy = new Complex[2];
                xy[0] = new Complex(s[0] * (float) Math.sqrt((y - 0.1f + Math.sqrt(Math.pow(y - 0.1f, 2) + Math.pow(w, 2))) / 2) * mag, (y - 0.1f) * mag);
                xy[1] = new Complex(s[0] * (float) Math.sqrt((y + Math.sqrt(Math.pow(y, 2) + Math.pow(w, 2))) / 2) * mag, y * mag);
                Complex[] b = new Complex[2];
                b[0] = Complex.rotate(xy[0],s[1] * (float) Math.sqrt((- y + 0.1f + Math.sqrt(Math.pow(y - 0.1f, 2) + Math.pow(w, 2))) / 2) * mag, theta, phi);
                b[1] = Complex.rotate(xy[1],s[1] * (float) Math.sqrt((- y + Math.sqrt(Math.pow(y, 2) + Math.pow(w, 2))) / 2) * mag, theta, phi);
                g.drawLine((int) (b[0].complexFloat(true) + width / 2), (int) (b[0].complexFloat(false) + height / 2), (int) (b[1].complexFloat(true) + width / 2), (int) (b[1].complexFloat(false) + height / 2));
            }
        }
    }
    private void LogarithmicFunctionLine (Graphics g) {
        for (float y = -10; y <= 10; y += 0.1f) {
            Complex[] xy = new Complex[2];
            xy[0] = new Complex((float) (Math.pow(Math.E, y - 0.1f) * Math.cos(w)) * mag, (y - 0.1f) * mag);
            xy[1] = new Complex((float) (Math.pow(Math.E, y) * Math.cos(w)) * mag, y * mag);
            Complex[] b = new Complex[2];
            b[0] = Complex.rotate(xy[0],(float) (Math.pow(Math.E, y - 0.1f) * Math.sin(w)) * mag, theta, phi);
            b[1] = Complex.rotate(xy[1],(float) (Math.pow(Math.E, y) * Math.sin(w)) * mag, theta, phi);
            g.drawLine((int) (b[0].complexFloat(true) + width / 2), (int) (b[0].complexFloat(false) + height / 2), (int) (b[1].complexFloat(true) + width / 2), (int) (b[1].complexFloat(false) + height / 2));
        }
    }
    private void FractionalFunctionLine (Graphics g) {
        for (float y = -10f; y <= 10f; y += 0.1f) {
            Complex[] xy = new Complex[2];
            xy[0] = new Complex((float) ((y - 0.1) / (Math.pow(y - 0.1, 2) + Math.pow(w, 2))) * mag, (y - 0.1f) * mag);
            xy[1] = new Complex((float) (y / (Math.pow(y, 2) + Math.pow(w, 2))) * mag, y * mag);
            Complex[] b = new Complex[2];
            b[0] = Complex.rotate(xy[0],(float) (- w / (Math.pow(y - 0.1, 2) + Math.pow(w, 2))) * mag, theta, phi);
            b[1] = Complex.rotate(xy[1],(float) (- w / (Math.pow(y, 2) + Math.pow(w, 2))) * mag, theta, phi);
            g.drawLine((int) (b[0].complexFloat(true) + width / 2), (int) (b[0].complexFloat(false) + height / 2), (int) (b[1].complexFloat(true) + width / 2), (int) (b[1].complexFloat(false) + height / 2));
        }
    }
}
