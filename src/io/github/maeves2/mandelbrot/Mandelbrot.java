package io.github.maeves2.mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class Mandelbrot extends JPanel {
<<<<<<< HEAD
    public static final int ITERATIONS = 1000;
=======
    public static final int ITERATIONS = 10000;
>>>>>>> fd144e97df649c9cef49f6d82837fa8542ccc306
    public static final int THRESHOLD = 500;
    public static final double ZOOM = 1;
    private final BufferedImage buffer;

<<<<<<< HEAD
=======
    private final RenderingHints QUALITY_RENDER = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    private final RenderingHints ANTIALIASING = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

>>>>>>> fd144e97df649c9cef49f6d82837fa8542ccc306
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
<<<<<<< HEAD
        gx.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)); // quality render
        gx.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)); // antialiasing
=======
        gx.addRenderingHints(this.ANTIALIASING);
        gx.addRenderingHints(this.QUALITY_RENDER);
>>>>>>> fd144e97df649c9cef49f6d82837fa8542ccc306


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
<<<<<<< HEAD
                if (Math.abs(re + im) < THRESHOLD) c = 0;

                /* COLORS:
                 * c<<0: blue
                 * c<<8: green
                 * c<<16: red
                 * use bitwise or to mix colors, xor to invert the color e.g. c | c<<16 is purple/pink
                 * trying to mix already mixed colors will make it white
                 */
                buffer.setRGB(x, y, c | c<<16);
=======
                if (Math.abs(re + im) < THRESHOLD) c = 0x000000;
                buffer.setRGB(x, y, c);
>>>>>>> fd144e97df649c9cef49f6d82837fa8542ccc306
            }
        }
        return (int) (System.currentTimeMillis() - start);
    }

}
