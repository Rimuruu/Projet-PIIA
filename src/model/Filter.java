package model;

import java.util.function.Function;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Filter{
	private String name;
    private Function<Color, Color> mapping;
	
    public Filter(String name, Function<Color, Color> mapping) {
        this.name = name;
        this.mapping = mapping;
    }
    
    public Image apply(Image source) {
        int w = (int) source.getWidth();
        int h = (int) source.getHeight();

        WritableImage image = new WritableImage(w, h);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color c1 = source.getPixelReader().getColor(x, y);
                Color c2 = mapping.apply(c1);

                image.getPixelWriter().setColor(x, y, c2);
            }
        }

        return image;
    }
}