package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;




import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
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
	public String selectedMode;
	
	
	
	public AppContext(){
		composants = new ArrayList<Shape>();
		selected = null;
	}
	
	
	public void setDefaultZoom() {
		zoom = 1;
	}
	
	public void addCircle(CanvasPane cv) {

		Circle circle = new Circle(100);

		circle.setCenterX((cv.getWidth()/2));
		circle.setCenterY((cv.getHeight()/2));

		composants.add(circle);
	}
	
	public void addRectangle(CanvasPane cv) {
		Rectangle rectangle = new Rectangle((cv.getWidth()/2),(cv.getHeight()/2),50,50);
		
		
		composants.add(rectangle);
	}
	
	public void addText(CanvasPane cv) {
		Text t = new Text((cv.getWidth()/2), (cv.getHeight()/2), "This is a test");
		composants.add(t);
	}
	
	
	
	
	
}