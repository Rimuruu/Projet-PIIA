package controller;

import App.Main;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import model.AppContext;
import model.SerializableEllipse;
import model.SerializableRectangle;
import model.SerializableText;
import view.CanvasPane;

public class CanvasController {
	
	

	
	public static Boolean intersectEllipse(SerializableEllipse c,double x,double y,double zoom) {
		double cx = c.getCenterX()*zoom;
		double cy = c.getCenterY()*zoom;
		double rx = c.getRadiusX()*zoom;
		double ry = c.getRadiusY()*zoom;
	
		return ((Math.pow((x - cx),2)/Math.pow(rx,2)) + (Math.pow((y - cy),2)/Math.pow(ry,2)) ) <= 1;
	}
	public static Boolean intersectRectangle(SerializableRectangle r,double x,double y,double zoom) {
		double rx = r.getX()*zoom;
		double ry = r.getY()*zoom;
		double rw = r.getWidth()*zoom;
		double rh = r.getHeight()*zoom;
		return rx<x&&ry<y&&(rw+rx)>x&&(rh+ry)>y;
	}
	
	public static Boolean intersectShape(Shape s,double x,double y,double zoom) {
		double margin = 10*zoom;
		Bounds b = s.getBoundsInLocal();
		double rx = (b.getMinX()*zoom)-margin;
		double ry = (b.getMinY()*zoom)-margin;
		double rw = (b.getWidth()*zoom)+margin;
		double rh = (b.getHeight()*zoom)+margin;
		return rx<x&&ry<y&&(rw+rx)>x&&(rh+ry)>y;
	}
	
	public static boolean intersect(Shape s,double x,double y,double zoom) {
		if(s instanceof SerializableEllipse) {
	
			return intersectEllipse((SerializableEllipse)s,x,y,zoom);
				
		
		}
		if(s instanceof SerializableRectangle) { 
			return intersectRectangle((SerializableRectangle)s,x,y,zoom);
			
		}
		else {
			return intersectShape(s,x,y,zoom);
			
		}
		
		
	}
	
	public static void click(MouseEvent e,AppContext app,CanvasPane cv) {
		double x = e.getX();
		double y = e.getY();
		Shape lastSelected = app.selected;

		
		if(intersectShape(((Shape)app.selector),x,y,app.zoom) == true) {
			app.lastX = app.selector.getX();
			app.lastY = app.selector.getY();
			
		}
		else{
			app.selected = null;
			
			// On regarde si un composants a �t� selectionn�
			for(int i = app.composants.size()-1;i >=0;i-- ) {
				Shape s =app.composants.get(i); 
				if(s instanceof SerializableEllipse) {
	
					if(intersectEllipse((SerializableEllipse)s,x,y,app.zoom)==true) {
						
						app.selected = s;
						break;
						
					
					}
				}
				if(s instanceof SerializableRectangle) { 
					if(intersectRectangle((SerializableRectangle)s,x,y,app.zoom)==true) {
						app.selected = s;
						break;
						
					}
				}
				else {
					if(intersectShape(s,x,y,app.zoom)==true) {
						app.selected = s;
						if(lastSelected == s && e.getClickCount() == 2) {
							if(s instanceof SerializableText) { 
								TextInput modif = new TextInput((SerializableText)s,app,cv);
							}
						}
						break;
						
					}
				}
				}
		
			if(app.selected != lastSelected) {
				app.selectedMode = "SELECT";
				Main.save(app, cv);
				
			}
		}
		cv.update(app);
		
		
	}
	
	public static void dragged(MouseEvent e,AppContext app,CanvasPane cv) {
		double x = e.getX();
		double y = e.getY();
		// On d�place l'objet selectionn�
		if(app.selected != null && app.selectedMode.compareTo("SELECT")== 0 ) {
			
			if(intersect(app.selected,x,y,app.zoom)) {
				if(app.selected instanceof SerializableEllipse) {
					((SerializableEllipse) app.selected).setCenterX(x/app.zoom);
					((SerializableEllipse) app.selected).setCenterY(y/app.zoom);
				}
				if(app.selected instanceof SerializableRectangle) {
					SerializableRectangle r =(SerializableRectangle) app.selected;
					r.setX((x/app.zoom)-(r.getWidth()/2));
					r.setY((y/app.zoom)-(r.getHeight()/2));
				}
				else if(app.selected instanceof SerializableText){
					SerializableText r =(SerializableText) app.selected;
					Bounds b = r.getBoundsInLocal();
					r.setX((x/app.zoom)-(b.getWidth()/2));
					r.setY((y/app.zoom)+(b.getHeight()/2));
				}
				 app.setIsSaved(false);
			}
			
		}
		// On redimensionne l'objet selectionn�
		else if(app.selected != null && app.selectedMode.compareTo("REDI")== 0 ){
			SerializableRectangle r =(SerializableRectangle) app.selector;
			r.setX((x/app.zoom)-(r.getWidth()/2));
			r.setY((y/app.zoom)-(r.getHeight()/2));
			
			if(app.selected instanceof SerializableRectangle) {
				
				SerializableRectangle r2 =(SerializableRectangle) app.selected;
				
				double diffX = app.selector.getX()-app.lastX;
				double diffY = app.selector.getY()-app.lastY;
				double width = diffX;
				double height = diffY;
				r2.setWidth(r2.getWidth()+width);
				r2.setHeight(r2.getHeight()+height);
				app.lastX = app.selector.getX();
				app.lastY = app.selector.getY();
			}
			else if(app.selected instanceof SerializableEllipse) {
				
				SerializableEllipse r2 =(SerializableEllipse) app.selected;
				
				double diffX = app.selector.getX()-app.lastX;
				double diffY = app.selector.getY()-app.lastY;
				double width = diffX;
				double height = diffY;
			
				r2.setRadiusX(r2.getRadiusX()+width);
				r2.setRadiusY(r2.getRadiusY()+(height));
				app.lastX = app.selector.getX();
				app.lastY = app.selector.getY();
		
			
			}
			app.setIsSaved(false);
			
		
		}
		cv.update(app);
		
	}
	
	
	public static void released(MouseEvent e,AppContext app,CanvasPane cv) {
		double x = e.getX();
		double y = e.getY();
		double diffX = app.selector.getX()-app.lastX;
		double diffY = app.selector.getY()-app.lastY;
		double width = diffX;
		double height = diffY;

		if(app.selected!=null &&  app.selectedMode.compareTo("REDI")== 0 ) {
			if(app.selected instanceof SerializableRectangle) {
				SerializableRectangle r =(SerializableRectangle) app.selected;
				r.setWidth(r.getWidth()+width);
				r.setHeight(r.getHeight()+height);
			}
			else if(app.selected instanceof SerializableEllipse) {	
				SerializableEllipse r2 =(SerializableEllipse) app.selected;
				r2.setRadiusX(r2.getRadiusX()+width);
				r2.setRadiusY(r2.getRadiusY()+(height));
		
			}
			Main.save(app, cv);
		}
		if(app.selected != null && intersectShape(((Shape)app.selected),x,y,app.zoom) == true &&  app.selectedMode.compareTo("SELECT")== 0) {
			Main.save(app, cv);
		}
		cv.update(app);
	}


	
	
	
	
}