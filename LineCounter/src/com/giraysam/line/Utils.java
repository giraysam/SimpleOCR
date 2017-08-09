package com.giraysam.line;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Utils {

    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    public static BufferedImage makeGray(BufferedImage bufferedImage) {
        int x, y, red, green, blue, grayLevel, gray;

        for (y=0; y < bufferedImage.getHeight(); y++) {
            for (x=0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();

                grayLevel = (red + green + blue) / 3;

                gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                bufferedImage.setRGB(x, y, gray);
            }
        }

        return bufferedImage;
    }

    public static BufferedImage makeBinary(BufferedImage bufferedImage) {
        int x, y, red, green, blue, avg;

        for (y=0; y < bufferedImage.getHeight(); y++) {
            for (x=0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();

                avg = (red + green + blue) / 3;

                if (avg > 120) {
                    bufferedImage.setRGB(x, y, Color.WHITE.getRGB());
                }
                else {
                    bufferedImage.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }

        return bufferedImage;
    }
}
