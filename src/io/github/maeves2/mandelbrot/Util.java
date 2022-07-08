package io.github.maeves2.mandelbrot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class Util {
    public static double map(double val2map, double val_min, double val_max, double map_min, double map_max) {
        return (val2map - val_min) / (val_max - val_min) * (map_max - map_min) + map_min;
    }

    public static void writeBuffer(BufferedImage buffer, Path location) {
        try {
            ImageIO.write(buffer, "png", location.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}