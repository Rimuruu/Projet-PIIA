package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class SerializableEllipse extends Ellipse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SerializableEllipse(double x,double y,double width, double height) {
		super(x,y,width,height);
		
		
	}
	
	public SerializableEllipse(double width, double height) {
		super(width,height);
		
		
	}
	
	
	private void writeObject(ObjectOutputStream oos)
		    throws IOException {
				oos.writeDouble(this.getRadiusX());
			    oos.writeDouble(this.getRadiusY());
				oos.writeDouble(this.getCenterX());
				oos.writeDouble(this.getCenterY());
				
		   }
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
	{
		this.setRadiusX(s.readDouble());
		this.setRadiusY(s.readDouble());
		this.setCenterX(s.readDouble());
		this.setCenterY(s.readDouble());
		
	   }
	
}