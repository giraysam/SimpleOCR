package com.giraysam.line;

import java.awt.image.BufferedImage;


public class Line {
    private BufferedImage lineImage;
    private int startYPoint;

    public Line(BufferedImage lineImage, int startYPoint) {
        this.lineImage = lineImage;
        this.startYPoint = startYPoint;
    }

    public BufferedImage getLineImage() {
        return lineImage;
    }

    public void setLineImage(BufferedImage lineImage) {
        this.lineImage = lineImage;
    }

    public int getStartYPoint() {
        return startYPoint;
    }

    public void setStartYPoint(int startYPoint) {
        this.startYPoint = startYPoint;
    }
}
