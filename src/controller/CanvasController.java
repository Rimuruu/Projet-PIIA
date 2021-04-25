package controller;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
		return (Math.pow((x - cx),2) + Math.pow((y - cy),2)) < Math.pow(rx,2);
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
			for(int i = app.composants.size()-1;i >=0;i-- ) {
				Shape s =app.composants.get(i); 
				if(s instanceof SerializableEllipse) {
					System.out.println("isclicked  "+intersectEllipse((SerializableEllipse)s,x,y,app.zoom));
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
			}
		}
		
		cv.update(app);
		
		
	}
	
	public static void dragged(MouseEvent e,AppContext app,CanvasPane cv) {
		double x = e.getX();
		double y = e.getY();
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
		System.out.println(width + " "+height);
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
		}
		cv.update(app);
	}

	
	
	
	
}