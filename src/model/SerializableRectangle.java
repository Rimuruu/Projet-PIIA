package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SerializableRectangle extends Rectangle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SerializableRectangle(double x,double y,double width, double height) {
		super(x,y,width,height);
		
		
	}
	
	public SerializableRectangle(double width, double height) {
		super(width,height);
		
		
	}
	
	
	private void writeObject(ObjectOutputStream oos)
		    throws IOException {
			
					oos.writeDouble(this.getWidth());
				    oos.writeDouble(this.getHeight());
					oos.writeDouble(this.getX());
					oos.writeDouble(this.getY());
		   }
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
	{
		this.setWidth(s.readDouble());
		this.setHeight(s.readDouble());
		this.setX(s.readDouble());
		this.setY(s.readDouble());

	   }
	
}