package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AppContext;
import view.CanvasPane;
import view.MainBar;

public class FileController {
	public static Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	
	public static void newFile(AppContext app,MainBar mainbar,CanvasPane cv) {
		cv.defaultCanvas();
		WritableImage image = cv.snapshot(new SnapshotParameters(), null);
		
		app.file = new File("New File.jpeg");
		app.image = (Image)image;
		BufferedImage awtImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		try 
        {
			ImageIO.write(SwingFXUtils.fromFXImage(image,awtImage), "JPEG", app.file);
        }catch (IOException ex) 
        { 
            System.out.println(ex.toString());
        }
		mainbar.updateContext(app);
	}
	
	
	public static void save(FileChooser fileChooser,Stage primaryStage,CanvasPane cv,AppContext app) {
		WritableImage image = cv.snapshot(new SnapshotParameters(), null);

        // TODO: probably use a file chooser here
        //Set extension filter



        //Show save file dialog
        File file = app.file;
        System.out.println(file);
        System.out.println(app.file.getAbsolutePath());
        
        try 
        {
         
        		if(file != null) {
        			String ext  = getExtensionByStringHandling(file.getName()).get();
        	        if(ext.equals("jpg")) ext = "jpeg";
        			if(ext.equals("jpeg")) {
               		 BufferedImage awtImage = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_INT_RGB);
               		 ImageIO.write(SwingFXUtils.fromFXImage(image,awtImage), "JPEG", file);
               	}
               	else {
               		ImageIO.write(SwingFXUtils.fromFXImage(image, null), ext, file);
               	}
                	
        		}
            //}
       
        } catch (IOException ex) 
        { 
            System.out.println(ex.toString());
        }
		
	}
	
	public static void saveAs(FileChooser fileChooser,Stage primaryStage,CanvasPane cv) {
		WritableImage image = cv.snapshot(new SnapshotParameters(), null);

        // TODO: probably use a file chooser here
        //Set extension filter



        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
       
        try 
        {
            if(file != null)
            {
            	 String ext  = getExtensionByStringHandling(file.getName()).get();
                 if(ext.equals("jpg")) ext = "jpeg";
            	if(ext.equals("jpeg")) {
            		 BufferedImage awtImage = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_INT_RGB);
            		 ImageIO.write(SwingFXUtils.fromFXImage(image,awtImage), "JPEG", file);
            	}
            	else {
            		ImageIO.write(SwingFXUtils.fromFXImage(image, null), ext, file);
            	}
                
            }
        } catch (IOException ex) 
        { 
            System.out.println(ex.toString());
        }
	}
	
	public static void open(FileChooser fileChooser,Stage primaryStage,CanvasPane cv,MainBar mainbar,AppContext app) {
		File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            mainbar.filename.setText(file.getName());
            app.file = file;
            app.image = new Image(app.file.toURI().toString());
            cv.update(app);
        }
	}
	
	
	
}