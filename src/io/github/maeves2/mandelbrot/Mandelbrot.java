package io.github.maeves2.mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class Mandelbrot extends JPanel {
    public static final int ITERATIONS = 10000;
    public static final int THRESHOLD = 500;
    public static final double ZOOM = 1;
    private final BufferedImage buffer;

    private final RenderingHints QUALITY_RENDER = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    private final RenderingHints ANTIALIASING = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    public Mandelbrot(BufferedImage buffer) {
        this.buffer = buffer;
        int time = renderMandelbrotSet(this.buffer);
        Util.writeBuffer(this.buffer, Path.of("/home/aka/Documents/Java/Mandelbrot/src/io/github/maeves2/mandelbrot/buffer.png"));

        System.out.printf("Iterations: %d | Time: %dms%n", ITERATIONS, time);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gx = (Graphics2D) g;
        gx.addRenderingHints(this.ANTIALIASING);
        gx.addRenderingHints(this.QUALITY_RENDER);


        gx.drawImage(this.buffer, 0, 0, null);
        gx.dispose();
    }

    int renderMandelbrotSet(BufferedImage buffer) {
        long start = System.currentTimeMillis();
        for (int x = 0; x < Main.WIDTH; x++) {
            for (int y = 0; y < Main.HEIGHT; y++) {
                double re = Util.map(x, 0, Main.WIDTH, -2*ZOOM, 1*ZOOM);
                double im = Util.map(y, 0, Main.HEIGHT, -1*ZOOM, 1*ZOOM);

                double re2 = re;
                double im2 = im;

                int n = 0;

                for (int i = 0; i < ITERATIONS; i++) {
                    double re_next = re*re - im*im;
                    double im_next = 2*re*im;

                    re = re_next + re2;
                    im = im_next + im2;

                    if (Math.abs(re + im) > THRESHOLD) {
                        break;
                    } else n++;
                }

                int c = (int) Util.map(n, 0, 100, 0, 0xFF);
                if (Math.abs(re + im) < THRESHOLD) c = 0x000000;
                buffer.setRGB(x, y, c);
            }
        }
        return (int) (System.currentTimeMillis() - start);
    }

}
