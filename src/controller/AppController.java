package controller;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.AppContext;
import model.ImageShape;
import model.SerializableEllipse;
import model.SerializableRectangle;
import model.SerializableText;
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
	public static void addEllipse(AppContext app, CanvasPane cv) {
		
		app.addEllipse(cv);
		cv.update(app);
		
	}
	
public static void addRectangle(AppContext app, CanvasPane cv) {
		
		app.addRectangle(cv);
		cv.update(app);
		
	}

public static void addTriangle(AppContext app, CanvasPane cv) {
	
	app.addTriangle(cv);
	cv.update(app);
	
}

public static void addEtoile(AppContext app, CanvasPane cv) {
	
	app.addEtoile(cv);
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
	if(app.cache !=null) app.setIsSaved(false);
	cv.update(app);
}
public static void Copier(AppContext app){
	
	app.cache = app.selected;
}

// Deecopy, on veut pas une copie du pointeur mais un objet différent ayant les même variables.
public static Shape deepCopy(Shape s,CanvasPane cv,AppContext app) {
	if(s instanceof SerializableEllipse) {
		
		SerializableEllipse c = new SerializableEllipse(((SerializableEllipse) s).getRadiusX(),((SerializableEllipse) s).getRadiusY());
		c.setCenterX(((cv.getWidth()/app.zoom)/2));
		c.setCenterY(((cv.getHeight()/app.zoom)/2));
		return (Shape) c;
	}
	else if((s instanceof SerializableRectangle) && !(s instanceof ImageShape)) {
		
		SerializableRectangle r = new SerializableRectangle(((cv.getWidth()/app.zoom)/2),((cv.getHeight()/app.zoom)/2),((SerializableRectangle) s).getWidth(),((SerializableRectangle) s).getHeight());
		return (Shape) r;
	}
	else if(s instanceof SerializableText) {
		
		SerializableText t = new SerializableText(((cv.getWidth()/app.zoom)/2), ((cv.getHeight()/app.zoom)/2), ((Text) s).getText());
		Font f = ((SerializableText) s).getFont();
		t.setFont(f);
		return t;
	}
	else if(s instanceof ImageShape) {
		ImageShape image = (ImageShape) s;
		ImageShape copy = new ImageShape(image.i,image.getWidth(),image.getHeight(),((cv.getWidth()/app.zoom)/2), ((cv.getHeight()/app.zoom)/2));
		return copy;
	}
	return null;
}
public static void Coller(AppContext app ,CanvasPane cv) {
	if(app.cache != null) { 
		app.composants.add(deepCopy(app.cache,cv,app));
		app.setIsSaved(false);
	}
	cv.update(app);
}

public static void Redimensionner(AppContext app, CanvasPane cv) {
	if(app.selected != null && !(app.selected instanceof Text)) {
		app.selectedMode = "REDI";
		Bounds b = app.selected.getBoundsInLocal();
		double size = app.selector.getHeight();
		double margin = 10;
    	app.selector.setX(b.getMinX()+b.getWidth()+margin-size);
    	app.selector.setY(b.getMinY()+b.getHeight()-margin);
	}
	else if(app.selected != null && app.selected instanceof Text) {
		TextResize modif = new TextResize((SerializableText)app.selected,app,cv);
	}
	cv.update(app);
}

public static void moveComposantUp(AppContext app, CanvasPane cv) {
	if(app.selected != null) {
		int i = app.composants.indexOf(app.selected);
		if(i > 0) {
			
			app.composants.set(i, app.composants.get(i-1));
			app.composants.set(i-1, app.selected);
		}
		app.setIsSaved(false);
	}
	cv.update(app);
}

public static void moveComposantDown(AppContext app, CanvasPane cv) {
	if(app.selected != null) {
		int i = app.composants.indexOf(app.selected);
		if(i < app.composants.size()-1) {
			app.composants.set(i, app.composants.get(i+1));
			app.composants.set(i+1, app.selected);
		}
		app.setIsSaved(false);
	}
	cv.update(app);
}

	
	
	
	
}