package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import view.CanvasPane;

public class AppContext {
	public double zoom = 1;
	public File file;
	public  ArrayList<Shape> composants;
	public Shape selected;
	public Shape cache;
	public Rectangle selector;
	public String selectedMode="SELECT";
	public Image image;
	public double lastX,lastY=0;
	public List<Filter> filters = Arrays.asList(
            new Filter("Négatif", c -> c.invert()),
            new Filter("Noir et Blanc", c -> c.grayscale()),
            new Filter("Cyan", c -> {
            	Color c2 = c.grayscale()
;            	return Color.rgb((int)c2.getRed(),255,255);
            	
            	
            })
            
      
    );
	
	
	public AppContext(){
		composants = new ArrayList<Shape>();
		selected = null;
		selector = new Rectangle(0,0,20,20);
	}
	
	
	
	
	
	private double valueOf(Color c) {
		return c.getRed() + c.getGreen() + c.getBlue();
	}


	public void setDefaultZoom() {
		zoom = 1;
	}
	
	public void addEllipse(CanvasPane cv) {

		Ellipse Ellipse = new Ellipse(100,100);

		Ellipse.setCenterX((cv.getWidth()/2));
		Ellipse.setCenterY((cv.getHeight()/2));

		composants.add(Ellipse);
	}
	
	public void addRectangle(CanvasPane cv) {
		Rectangle rectangle = new Rectangle((cv.getWidth()/2),(cv.getHeight()/2),50,50);
		
		
		composants.add(rectangle);
	}
	
	public void addText(CanvasPane cv) {
		Text t = new Text((cv.getWidth()/2), (cv.getHeight()/2), "This is a test");
		composants.add(t);
	}
	
	public void applyFilter(CanvasPane cv,Filter filtre) throws IOException {
		Image image = this.image;
		Image image2 =  filtre.apply(image);
        this.image = image2;
		cv.update(this);
	}
	
	
	
	
	
}