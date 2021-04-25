package model;


import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import view.CanvasPane;
import view.MainBar;

public class AppContext implements Serializable{
	private static final long serialVersionUID = 1L;
	public transient double zoom = 1;
	public transient File file;
	public transient ArrayList<Shape> composants;
	public transient Shape selected;
	public transient Shape cache;
	public transient SerializableRectangle selector;
	public transient String selectedMode="SELECT";
	public transient  Image image;
	public transient double  lastX,lastY=0;
	public transient boolean isSaved = true;
	protected PropertyChangeSupport propertyChangeSupport;
	

	
	
	public void setContext(AppContext a) {
		this.zoom = a.zoom;
		this.composants = a.composants;
		this.file = a.file;
		this.image = a.image;
	}
	
	public void setIsSaved(boolean b) {
		boolean old = this.isSaved;
		this.isSaved = b;
		propertyChangeSupport.firePropertyChange("isSaved",old, b);
	}
	
	
	private void writeObject(ObjectOutputStream oos)
		    throws IOException {
			oos.writeBoolean(image != null);
		    if (image != null) {
		        int w = (int) image.getWidth();
		        int h = (int) image.getHeight();
	
		        byte[] b = new byte[w * h * 4];
		        image.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), b, 0, w * 4);
	
		        oos.writeInt(w);
		        oos.writeInt(h);
		        oos.write(b);
		    }

				oos.writeDouble(zoom);
				oos.writeInt(composants.size());
				for(Shape composant : composants) {
					oos.writeObject(composant);
				}
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
	
		        image = wImage;
		    }
			selectedMode="SELECT";
			isSaved = true;
			zoom = s.readDouble();
			System.out.println("zoom "+zoom);
			composants = new ArrayList<Shape>();
			selected = null;
			selector = new SerializableRectangle(0,0,20,20);
			int size = s.readInt();
			System.out.println("size "+size);
			for(int i = 0 ; i<size ; i++ ) {
				Shape a = (Shape) s.readObject();
				composants.add(a);
			}
		   }
	
	public AppContext(){
		composants = new ArrayList<Shape>();
		selected = null;
		selector = new SerializableRectangle(0,0,20,20);
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	
	
	
	
	private double valueOf(Color c) {
		return c.getRed() + c.getGreen() + c.getBlue();
	}


	public void setDefaultZoom() {
		zoom = 1;
	}
	
	public void addEllipse(CanvasPane cv) {

		SerializableEllipse Ellipse = new SerializableEllipse(100,100);

		Ellipse.setCenterX((cv.getWidth()/2));
		Ellipse.setCenterY((cv.getHeight()/2));

		composants.add(Ellipse);
	}
	
	public void addRectangle(CanvasPane cv) {
		SerializableRectangle rectangle = new SerializableRectangle((cv.getWidth()/2),(cv.getHeight()/2),200,200);
		
		
		composants.add(rectangle);
	}

	
	public void addTriangle(CanvasPane cv) {
		Image i = ShapeLoader.shapes.get("triangle.png");
		ImageShape triangle = new ImageShape(i);
		this.setIsSaved(false);
		composants.add(triangle);
	}
	
	public void addEtoile(CanvasPane cv) {
		Image i = ShapeLoader.shapes.get("star.png");
		ImageShape etoile = new ImageShape(i);
		this.setIsSaved(false);
		composants.add(etoile);
		}
	
	public void addText(CanvasPane cv) {
		SerializableText t = new SerializableText((cv.getWidth()/2), (cv.getHeight()/2), "Nouveau texte");
		this.setIsSaved(false);
		composants.add(t);
	}
	
	public void applyFilter(CanvasPane cv,Filter filtre) throws IOException {
		Image image = this.image;
		Image image2 =  filtre.apply(image);
        this.image = image2;
        this.setIsSaved(false);
		cv.update(this);
	}

	public void addPropertyChangeListener(MainBar mainbar) {
		propertyChangeSupport.addPropertyChangeListener(mainbar);
		
	}


	

	
	
	
	
	
}