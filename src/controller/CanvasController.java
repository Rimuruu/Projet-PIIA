package controller;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.AppContext;
import view.CanvasPane;

public class CanvasController {
	
	

	
	public static Boolean intersectCircle(Circle c,double x,double y,double zoom) {
		double cx = c.getCenterX()*zoom;
		double cy = c.getCenterY()*zoom;
		double r = c.getRadius()*zoom;
		System.out.println("cx cy r"+cx+" "+cy+" "+r);
		return (Math.pow((x - cx),2) + Math.pow((y - cy),2)) < Math.pow(r,2);
	}
	public static Boolean intersectRectangle(Rectangle r,double x,double y,double zoom) {
		double rx = r.getX()*zoom;
		double ry = r.getY()*zoom;
		double rw = r.getWidth()*zoom;
		double rh = r.getHeight()*zoom;
		return rx<x&&ry<y&&(rw+rx)>x&&(rh+ry)>y;
	}
	
	
	
	public static void click(MouseEvent e,AppContext app,CanvasPane cv) {
		double x = e.getX();
		double y = e.getY();
		System.out.println("x y "+x+" "+y);
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
					System.out.println("selected  "+app.selected);
					
				}
			}
			}
		cv.update(app);
		
	}
	
	public static void dragged(MouseEvent e,AppContext app,CanvasPane cv) {
		if(app.selected != null) {
			double x = e.getX();
			double y = e.getY();
			if(app.selected instanceof Circle) {
				((Circle) app.selected).setCenterX(x);
				((Circle) app.selected).setCenterY(y);
			}
			if(app.selected instanceof Rectangle) {
				Rectangle r =(Rectangle) app.selected;
				r.setX(x-(r.getWidth()/2));
				r.setY(y-(r.getHeight()/2));
			}
			cv.update(app);
		}
	}

	
	
	
	
}