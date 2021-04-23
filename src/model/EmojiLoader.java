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

    // File representing the folder that you select using a FileChooser
    static final File dir = new File("./src/asset");

    // array of supported extensions (use a List if you prefer)
    static final String ext = "png";
    
    public static ArrayList<Image> emojis = new ArrayList<Image>();
    // filter to identify images based on their extensions
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
    	 System.out.println(dir.getAbsolutePath());
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;

                try {
                    img = ImageIO.read(f);
                    
                    // you probably want something more involved here
                    // to display in your UI
                    System.out.println("image: " + f.getName());
                    System.out.println(" width : " + img.getWidth());
                    System.out.println(" height: " + img.getHeight());
                    System.out.println(" size  : " + f.length());
                    emojis.add(SwingFXUtils.toFXImage(img, null));
                    
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
    }
}