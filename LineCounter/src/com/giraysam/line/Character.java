package com.giraysam.line;


import java.awt.image.BufferedImage;

public class Character {
    private BufferedImage character;
    private int startXPoint;
    private int startYPoint;

    public Character(BufferedImage character, int startXPoint, int startYPoint) {
        this.character = character;
        this.startXPoint = startXPoint;
        this.startYPoint = startYPoint;
    }

    public BufferedImage getCharacter() {
        return character;
    }

    public void setCharacter(BufferedImage character) {
        this.character = character;
    }

    public int getStartXPoint() {
        return startXPoint;
    }

    public void setStartXPoint(int startXPoint) {
        this.startXPoint = startXPoint;
    }

    public int getStartYPoint() {
        return startYPoint;
    }

    public void setStartYPoint(int startYPoint) {
        this.startYPoint = startYPoint;
    }
}
