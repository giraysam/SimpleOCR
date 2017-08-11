package com.giraysam.line;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Utils {

    private static int threshold = 255;

    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    public static BufferedImage makeGray(BufferedImage bufferedImage) {
        int x, y, red, green, blue, grayLevel, gray, min = 255, max = 0;

        for (y=0; y < bufferedImage.getHeight(); y++) {
            for (x=0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();

                grayLevel = (red + green + blue) / 3;

                if (grayLevel < min) {
                    min = grayLevel;
                }

                if (grayLevel > max) {
                    max = grayLevel;
                }

                gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                bufferedImage.setRGB(x, y, gray);
            }
        }

        threshold = (min + max) / 2;

        return bufferedImage;
    }

    public static int getThreshold() {
        return threshold;
    }

    public static BufferedImage makeBinary(BufferedImage bufferedImage, int threshold) throws IOException {
        int x, y, red, green, blue, avg;

        for (y=0; y < bufferedImage.getHeight(); y++) {
            for (x=0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();

                avg = (red + green + blue) / 3;


                if (avg > threshold) {
                    bufferedImage.setRGB(x, y, Color.WHITE.getRGB());
                }
                else {
                    bufferedImage.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }

        ImageIO.write(bufferedImage, "jpg", new File("binary.jpg"));

        return bufferedImage;
    }
}
