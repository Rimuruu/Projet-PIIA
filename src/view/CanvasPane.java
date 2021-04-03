package view;

import java.io.File;

import controller.CanvasController;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.AppContext;
public class CanvasPane extends Canvas {
	
	GraphicsContext gc;

	
	public CanvasPane(AppContext app){
		super(800,600);
		gc= this.getGraphicsContext2D();
		gc.setFill(Color.GREY);
		gc.fillRect(0,0,this.getWidth(),this.getHeight());
		this.setOnMouseClicked(e -> {CanvasController.click(e,app,this);});
		this.setOnMouseDragged(e -> {CanvasController.dragged(e,app,this);});
		
	}
	
	public void defaultCanvas() {
		this.setHeight(600);
		this.setWidth(800);
		gc.setFill(Color.GREY);
		gc.fillRect(0,0,this.getWidth(),this.getHeight());
	}
	
	public void drawRect(double x,double y,double w,double h,double zoom) {
		double margin = 10*zoom;
		gc.setFill(Color.BLACK);
		gc.strokeLine(x-margin, y-margin, x+w+margin, y-margin);
		gc.strokeLine(x-margin, y-margin, x-margin, y+h+margin);
		gc.strokeLine(x+w+margin, y-margin, x+w+margin,y+h+margin);
		gc.strokeLine(x-margin, y+h+margin, x+w+margin, y+h+margin);
	}
	
	public void update(AppContext app){
		Image image = new Image(app.file.toURI().toString());
		this.setHeight(image.getHeight()*app.zoom);
		this.setWidth(image.getWidth()*app.zoom);
		gc.setFill(Color.GREY);
		gc.fillRect(0,0,this.getWidth(),this.getHeight());
		gc.clearRect(0,0,image.getWidth(),image.getHeight());
	    gc.drawImage(image,
                0,
                0,image.getWidth()*app.zoom,image.getHeight()*app.zoom);
	    gc.setFill(Color.BLACK);
	    for (Node n : app.composants) {
	    	if(n instanceof Circle) {
	    		double x = ((Circle) n).getCenterX()*app.zoom;
	    		double y = ((Circle) n).getCenterY()*app.zoom;
	    		double r = ((Circle) n).getRadius()*app.zoom;
	    		System.out.println("x y "+x+" "+y+" "+r);
	    		gc.fillOval(x-(r), y-(r), r*2, r*2);
	    	}else if(n instanceof Rectangle) {
	    		double x = ((Rectangle) n).getX()*app.zoom;
	    		double y = ((Rectangle) n).getY()*app.zoom;
	    		double w = ((Rectangle) n).getWidth()*app.zoom;
	    		double h = ((Rectangle) n).getHeight()*app.zoom;
	    		gc.fillRect(x, y, w, h);
	    	}
	    	else if (n instanceof Text) {
	    		double x = ((Text) n).getX()*app.zoom;
	    		double y = ((Text) n).getY()*app.zoom;
	    		String text = ((Text) n).getText();
	    		Font f =((Text) n).getFont();
	    		Font f2 = new Font(f.getSize()*app.zoom);
	    		gc.setFont(f2);
	    		gc.fillText(text, x, y);
	    	}
	    }
	    
	    if(app.selected!=null) {
	    	Bounds b = app.selected.getBoundsInLocal();
	    	drawRect(b.getMinX()*app.zoom,b.getMinY()*app.zoom,b.getWidth()*app.zoom,b.getHeight()*app.zoom,app.zoom);
	    }
	
	}
}