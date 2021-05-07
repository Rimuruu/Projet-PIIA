package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

public class ImageShape extends SerializableRectangle implements Serializable{
	private static final long serialVersionUID = 1L;
	public transient Image i;
	
	public ImageShape( Image i) {
		super(200,200);
		this.i = i;
	
		
		
		
	}
	
	public ImageShape( Image i,double width, double height) {
		super(width,height);
		this.i = i;

		
		
		
	}
	
	public ImageShape( Image i,double width, double height,double x, double y) {
		super(width,height);
		this.i = i;
		this.setX(x);
		this.setY(y);

		
		
		
	}
	
	private void writeObject(ObjectOutputStream oos)
		    throws IOException {
					oos.writeBoolean(i != null);
				    if (i != null) {
				        int w = (int) i.getWidth();
				        int h = (int) i.getHeight();
			
				        byte[] b = new byte[w * h * 4];
				        i.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), b, 0, w * 4);
			
				        oos.writeInt(w);
				        oos.writeInt(h);
				        oos.write(b);
				    }
					oos.writeDouble(this.getHeight());
					oos.writeDouble(this.getWidth());
					oos.writeDouble(this.getX());
					oos.writeDouble(this.getY());
		   }
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
	{

		if (s.readBoolean()) {
	        int w = s.readInt();
	        int h = s.readInt();

	        byte[] b = new byte[w * h * 4];
	        s.readFully(b);

	        WritableImage wImage = new WritableImage(w, h);
	        wImage.getPixelWriter().setPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), b, 0, w * 4);

	        i = wImage;
	    }
		this.setHeight(s.readDouble());
		this.setWidth(s.readDouble());
		this.setX(s.readDouble());
		this.setY(s.readDouble());

	   }
	
	
	
}