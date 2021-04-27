package model;


import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import App.Main;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
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
	

	public AppContext deepCopy(CanvasPane cv) {
		AppContext tmp = new AppContext();
		tmp.selected =  deepCopyComposant(this.selected,cv,this);
		for(Shape composant : this.composants){
			tmp.composants.add( deepCopyComposant(composant,cv,this));
			
		}

		tmp.isSaved = this.isSaved;
		tmp.lastX = this.lastX;
		tmp.lastY = this.lastY;

		tmp.image = this.image;
		tmp.cache =  deepCopyComposant(this.cache,cv,this);
		return tmp;
	}
	
	public static Shape deepCopyComposant(Shape s,CanvasPane cv,AppContext app) {
		if(s instanceof SerializableEllipse) {
			
			SerializableEllipse c = new SerializableEllipse(((SerializableEllipse) s).getRadiusX(),((SerializableEllipse) s).getRadiusY());
			c.setCenterX(((SerializableEllipse) s).getCenterX());
			c.setCenterY(((SerializableEllipse) s).getCenterY());
			return (Shape) c;
		}
		else if((s instanceof SerializableRectangle) && !(s instanceof ImageShape)) {
			
			SerializableRectangle r = new SerializableRectangle(((cv.getWidth()/app.zoom)/2),((cv.getHeight()/app.zoom)/2),((SerializableRectangle) s).getWidth(),((SerializableRectangle) s).getHeight());
			r.setX(((SerializableRectangle) s).getX());
			r.setY(((SerializableRectangle) s).getY());
			return (Shape) r;
		}
		else if(s instanceof SerializableText) {
			
			SerializableText t = new SerializableText(((cv.getWidth()/app.zoom)/2), ((cv.getHeight()/app.zoom)/2), ((Text) s).getText());
			Font f = ((SerializableText) s).getFont();
			t.setX(((Text) s).getX());
			t.setY(((Text) s).getY());
			t.setFont(f);
			return t;
		}
		else if(s instanceof ImageShape) {
			ImageShape image = (ImageShape) s;
			ImageShape copy = new ImageShape(image.i,image.getWidth(),image.getHeight());
			copy.setX(image.getX());
			copy.setY(image.getY());
			return copy;
		}
		return null;
	}
	
	
	public void setHistorique(AppContext a) {
		this.composants = a.composants;
		this.cache = a.cache;
		this.selected = a.selected;
		this.image = a.image;
		this.lastX = a.lastX;
		this.lastY = a.lastY;
		this.isSaved = a.isSaved;
		
		
	}
	
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

			composants = new ArrayList<Shape>();
			selected = null;
			selector = new SerializableRectangle(0,0,20,20);
			int size = s.readInt();
	
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
		Main.save(this, cv);
		Ellipse.setCenterX((cv.getWidth()/2));
		Ellipse.setCenterY((cv.getHeight()/2));

		composants.add(Ellipse);

	}
	
	public void addRectangle(CanvasPane cv) {
		SerializableRectangle rectangle = new SerializableRectangle((cv.getWidth()/2),(cv.getHeight()/2),200,200);
		Main.save(this, cv);
		rectangle.setX((cv.getWidth()/2)-((rectangle.getWidth()/2)*this.zoom));
		rectangle.setY((cv.getHeight()/2)-((rectangle.getHeight()/2)*this.zoom));
		composants.add(rectangle);
		
	}

	
	public void addTriangle(CanvasPane cv) {
		Image i = ShapeLoader.shapes.get("triangle.png");
		ImageShape triangle = new ImageShape(i,200,200);
		triangle.setX((cv.getWidth()/2)-((triangle.getWidth()/2)*this.zoom));
		triangle.setY((cv.getHeight()/2)-((triangle.getHeight()/2)*this.zoom));
		Main.save(this, cv);
		this.setIsSaved(false);
		composants.add(triangle);

	}
	
	public void addEtoile(CanvasPane cv) {
		Image i = ShapeLoader.shapes.get("star.png");
		ImageShape etoile = new ImageShape(i,200,200);
		etoile.setX((cv.getWidth()/2)-((etoile.getWidth()/2)*this.zoom));
		etoile.setY((cv.getHeight()/2)-((etoile.getHeight()/2)*this.zoom));
		Main.save(this, cv);
		this.setIsSaved(false);
		composants.add(etoile);
		
		}
	
	public void addText(CanvasPane cv) {
		SerializableText t = new SerializableText((cv.getWidth()/2), (cv.getHeight()/2), "Nouveau texte");
		t.setFont(new Font(50));
		Bounds b = t.getBoundsInLocal();
		t.setX((cv.getWidth()/2)-((b.getWidth()/2)*this.zoom));
		t.setY((cv.getHeight()/2)-((b.getHeight()/2)*this.zoom));
		Main.save(this, cv);
		this.setIsSaved(false);
		composants.add(t);

	}
	
	public void applyFilter(CanvasPane cv,Filter filtre) throws IOException {
		Main.save(this, cv);
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