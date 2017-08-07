package com.giraysam.line;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("./sample.jpg");
            BufferedImage originalImage = ImageIO.read(file);
            LineCounter lineCounter = new LineCounter(originalImage);
            List<Line> lineList = lineCounter.getLineList();
            CharacterCounter characterCounter = new CharacterCounter(lineList);

            List<Character> characterList = characterCounter.getCharacterList();

            System.out.println("Line count: " + lineList.size());
            System.out.println("Character count: " + characterList.size());

            for (Character character: characterList) {
                int x = character.getStartXPoint();
                int y = character.getStartYPoint();
                int width = character.getCharacter().getWidth();
                int height = character.getCharacter().getHeight();

                Graphics2D g2d = originalImage.createGraphics();
                g2d.setColor(Color.BLUE);
                g2d.drawRect(x, y, width, height);
                g2d.dispose();
            }

            ImageIO.write(originalImage, "jpg", new File("export_image.jpg"));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
