package model;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ImageShape extends Rectangle{
	public Image i;
	
	public ImageShape( Image i) {
		super(i.getWidth(),i.getHeight());
		this.i = i;
		System.out.println(this.getBoundsInLocal());
		
		
		
	}
	
	public ImageShape( Image i,double width, double height) {
		super(width,height);
		this.i = i;
		System.out.println(this.getBoundsInLocal());
		
		
		
	}
	
	
	
}