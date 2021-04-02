package controller;

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

	
	
	
	
}