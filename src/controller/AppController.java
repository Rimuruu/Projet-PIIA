package controller;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import model.AppContext;
import view.CanvasPane;

public class AppController {
	public static void ZoomIn(AppContext app, CanvasPane cv) {
		app.zoom /= 0.75;
        cv.update(app);
	}
	
	public static void ZoomOut(AppContext app, CanvasPane cv) {
		app.zoom *= 0.75;
        cv.update(app);
	}
	public static void ZoomDefault(AppContext app, CanvasPane cv) {
		app.setDefaultZoom();
        cv.update(app);
	}
	public static void addCircle(AppContext app, CanvasPane cv) {
		
		app.addCircle(cv);
		cv.update(app);
		
	}
	
public static void addRectangle(AppContext app, CanvasPane cv) {
		
		app.addRectangle(cv);
		cv.update(app);
		
	}
	
public static void addText(AppContext app, CanvasPane cv) {
	
	app.addText(cv);
	cv.update(app);
	
}
public static void Couper(AppContext app ,CanvasPane cv){
	app.composants.remove(app.selected);
	app.cache = app.selected;
	app.selected = null;
	cv.update(app);
}
public static void Copier(AppContext app){
	app.cache = app.selected;
}
public static Shape deepCopy(Shape s,CanvasPane cv) {
	if(s instanceof Circle) {
		
		Circle c = new Circle(((Circle) s).getRadius());
		c.setCenterX((cv.getWidth()/2));
		c.setCenterY((cv.getHeight()/2));
		return (Shape) c;
	}
	else if(s instanceof Rectangle) {
		
		Rectangle r = new Rectangle((cv.getWidth()/2),(cv.getHeight()/2),((Rectangle) s).getWidth(),((Rectangle) s).getHeight());
		return (Shape) r;
	}
	else if(s instanceof Text) {
		
		Text t = new Text((cv.getWidth()/2), (cv.getHeight()/2), ((Text) s).getText());
		return t;
	}
	return null;
}
public static void Coller(AppContext app ,CanvasPane cv) {
	if(app.cache != null) app.composants.add(deepCopy(app.cache,cv));
	cv.update(app);
}

	
	
	
	
}