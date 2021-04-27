package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SerializableText extends Text implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SerializableText(double x,double y,String text) {
		super(x,y,text);
		
	}
	
	
	private void writeObject(ObjectOutputStream oos)
		    throws IOException {
			
					oos.writeObject(this.getText());
				    oos.writeDouble(this.getFont().getSize());
					oos.writeDouble(this.getX());
					oos.writeDouble(this.getY());
		   }
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
	{
	
		String text = "";
		text = (String) s.readObject();
		this.setText(text);
		this.setFont(new Font(s.readDouble()));
		this.setX(s.readDouble());
		this.setY(s.readDouble());

	   }
	
}