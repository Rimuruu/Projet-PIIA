package model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ShapeLoader {


    static final File dir = new File("./src/asset/shape");


    static final String ext = "png";
    
    public static HashMap<String,Image> shapes = new HashMap<String,Image>();
 // On veut que les images png
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
                    

                    shapes.put(f.getName(),SwingFXUtils.toFXImage(img, null));
                    
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
    }
}