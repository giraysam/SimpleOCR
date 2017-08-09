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
            File file = new File("./test2.jpg");
            BufferedImage originalImage = ImageIO.read(file);
            LineCounter lineCounter = new LineCounter(originalImage);
            List<Line> lineList = lineCounter.getLineList();
            CharacterCounter characterCounter = new CharacterCounter(lineList);

            List<Character> characterList = characterCounter.getCharacterList();

            System.out.println("Line count: " + lineList.size());
            System.out.println("Character count: " + characterList.size());
            int counter = 0;

            for (Character character : characterList) {
                int x = character.getStartXPoint();
                int y = character.getStartYPoint();
                int width = character.getCharacter().getWidth();
                int height = character.getCharacter().getHeight();

                counter++;

                BufferedImage subImage = originalImage.getSubimage(x, y, width, height);
                BufferedImage resizedImage = new BufferedImage(16, 16, subImage.getType());
                Graphics2D resizedGraphics = resizedImage.createGraphics();
                resizedGraphics.drawImage(subImage, 0, 0, 16, 16, null);
                resizedGraphics.dispose();

                Graphics2D g2d = originalImage.createGraphics();
                g2d.setColor(Color.BLUE);
                g2d.drawRect(x, y, width, height);
                g2d.dispose();

                // ImageIO.write(resizedImage, "jpg", new File("characters/c_" + counter + ".jpg"));
            }

            ImageIO.write(originalImage, "jpg", new File("export_image.jpg"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
