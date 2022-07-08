package io.github.maeves2.mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    private final JFrame frame;
    private final BufferedImage buffer;

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    public Main() {
        this.frame = new JFrame("Mandelbrot Set");
        this.buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);

        this.frame.setSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setDefaultCloseOperation(3);
        this.frame.setResizable(true);
        this.frame.add(new Mandelbrot(this.buffer));

        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
