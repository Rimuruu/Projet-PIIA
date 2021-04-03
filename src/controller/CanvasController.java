package controller;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppContext;
import view.CanvasPane;

public class CanvasController {
	
	

	
	public static Boolean intersectCircle(Circle c,double x,double y,double zoom) {
		double cx = c.getCenterX()*zoom;
		double cy = c.getCenterY()*zoom;
		double r = c.getRadius()*zoom;
		return (Math.pow((x - cx),2) + Math.pow((y - cy),2)) < Math.pow(r,2);
	}
	public static Boolean intersectRectangle(Rectangle r,double x,double y,double zoom) {
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
		if(s instanceof Circle) {
	
			return intersectCircle((Circle)s,x,y,zoom);
				
		
		}
		if(s instanceof Rectangle) { 
			return intersectRectangle((Rectangle)s,x,y,zoom);
			
		}
		else {
			return intersectShape(s,x,y,zoom);
			
		}
		
		
	}
	
	public static void click(MouseEvent e,AppContext app,CanvasPane cv) {
		double x = e.getX();
		double y = e.getY();
		Shape lastSelected = app.selected;
		app.selected = null;
		for(int i = app.composants.size()-1;i >=0;i-- ) {
			Shape s =app.composants.get(i); 
			if(s instanceof Circle) {
				System.out.println("isclicked  "+intersectCircle((Circle)s,x,y,app.zoom));
				if(intersectCircle((Circle)s,x,y,app.zoom)==true) {
					
					app.selected = s;
					
				
				}
			}
			if(s instanceof Rectangle) { 
				if(intersectRectangle((Rectangle)s,x,y,app.zoom)==true) {
					app.selected = s;

					
				}
			}
			else {
				if(intersectShape(s,x,y,app.zoom)==true) {
					app.selected = s;
					if(lastSelected == s) {
						if(s instanceof Text) { 
							TextInput modif = new TextInput((Text)s,app,cv);
						}
					}
					
				}
			}
			}
		cv.update(app);
		
	}
	
	public static void dragged(MouseEvent e,AppContext app,CanvasPane cv) {
		if(app.selected != null) {
			double x = e.getX();
			double y = e.getY();
			if(intersect(app.selected,x,y,app.zoom)) {
				if(app.selected instanceof Circle) {
					((Circle) app.selected).setCenterX(x/app.zoom);
					((Circle) app.selected).setCenterY(y/app.zoom);
				}
				if(app.selected instanceof Rectangle) {
					Rectangle r =(Rectangle) app.selected;
					r.setX((x/app.zoom)-(r.getWidth()/2));
					r.setY((y/app.zoom)-(r.getHeight()/2));
				}
				else if(app.selected instanceof Text){
					Text r =(Text) app.selected;
					Bounds b = r.getBoundsInLocal();
					r.setX((x/app.zoom)-(b.getWidth()/2));
					r.setY((y/app.zoom)+(b.getHeight()/2));
				}
			}
			cv.update(app);
		}
	}

	
	
	
	
}