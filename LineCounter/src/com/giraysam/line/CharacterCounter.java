package com.giraysam.line;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterCounter {
    private List<Line> lines;

    public CharacterCounter(List<Line> lines) throws IOException {
        this.lines = lines;
    }

    public List<Character> getCharacterList() throws IOException {
        List<Character> characterList = new ArrayList<>();
        int x, y, red, a, b, startXPoint = -1;

        for (Line line: lines) {

            BufferedImage lineCopy = Utils.copyImage(line.getLineImage());

            for (x= 0; x < line.getLineImage().getWidth(); x++) {
                for (y=0; y < line.getLineImage().getHeight(); y++) {
                    red = new Color(line.getLineImage().getRGB(x, y)).getRed();

                    if (red == 0) {
                        for (int k = 0; k < line.getLineImage().getHeight() ; k++) {
                                lineCopy.setRGB(x, k, Color.BLACK.getRGB());
                        }
                    }
                }
            }

            for (y=0; y < 1; y++) {
                for (x= 1; x < lineCopy.getWidth(); x++) {
                    a = new Color(lineCopy.getRGB(x-1, y)).getRed();
                    b = new Color(lineCopy.getRGB(x, y)).getRed();

                    if (a == 255 && b == 0) {
                        startXPoint = x;
                    }

                    if (a == 0 && b == 255 && startXPoint != -1) {
                        int width = x - startXPoint;
                        int height = lineCopy.getHeight();
                        BufferedImage subImage = line.getLineImage().getSubimage(startXPoint, 0, width, height);
                        Character character = new Character(subImage, startXPoint, line.getStartYPoint());
                        characterList.add(character);
                        startXPoint = -1;
                    }
                }
            }
        }

        return characterList;
    }
}
