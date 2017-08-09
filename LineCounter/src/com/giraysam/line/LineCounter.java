package com.giraysam.line;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class LineCounter {
    private BufferedImage smearingX;
    private BufferedImage binaryImage;
    private int width;
    private int height;

    public LineCounter(BufferedImage bufferedImage) throws IOException {
        this.binaryImage = bufferedImage;
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();

        BufferedImage grayImage = Utils.makeGray(bufferedImage);
        BufferedImage binaryImage = Utils.makeBinary(grayImage);
        smearingX = smearingX(binaryImage);
    }

    public List<Line> getLineList() throws IOException {
        List<Line> lineList = new ArrayList<>();

        int x, y, a, b, startYPoint = -1;

        for (y=1; y < height; y++) {
            for (x= 0; x < 1; x++) {
                a = new Color(smearingX.getRGB(x, y-1)).getRed();
                b = new Color(smearingX.getRGB(x, y)).getRed();

                if (a == 255 && b == 0) {
                    startYPoint = y;
                }

                if (a == 0 && b == 255 && startYPoint != -1) {
                    int height = y - startYPoint;
                    BufferedImage subImage = binaryImage.getSubimage(0, startYPoint, width, height);

                    Line line = new Line(subImage, startYPoint);
                    lineList.add(line);
                    startYPoint = -1;
                }
            }
        }

        return lineList;
    }

    private BufferedImage smearingX(BufferedImage bufferedImage) {
        int x, y, red;
        BufferedImage smearingXImage = Utils.copyImage(bufferedImage);

        for (y=0; y < height; y++) {
            for (x= 0; x < width; x++) {

                red = new Color(bufferedImage.getRGB(x, y)).getRed();

                if (red == 0) {
                    for (int k = 0; k < width ; k++) {
                            smearingXImage.setRGB(k, y, Color.BLACK.getRGB());
                    }
                }
            }
        }

        return smearingXImage;
    }
}
