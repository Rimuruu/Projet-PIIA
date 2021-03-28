package view;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.AppContext;
public class CanvasPane extends Canvas {
	
	GraphicsContext gc;
	
	
	public CanvasPane(){
		super(800,600);
		gc= this.getGraphicsContext2D();
		gc.setFill(Color.GREY);
		gc.fillRect(0,0,this.getWidth(),this.getHeight());
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
	}
}