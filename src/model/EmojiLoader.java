package model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class EmojiLoader {


    static final File dir = new File("./src/asset");


    static final String ext = "png";
    
    public static ArrayList<Image> emojis = new ArrayList<Image>();

    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {

                if (name.endsWith("." + ext)) {
                    return (true);
                }
                return (false);
        }
    };

    public static void load() {
        if (dir.isDirectory()) {
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;

                try {
                    img = ImageIO.read(f);

                    emojis.add(SwingFXUtils.toFXImage(img, null));
                    
                } catch (final IOException e) {
        
                }
            }
        }
    }
}