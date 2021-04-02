import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppContext;
import view.CanvasPane;
import view.MainBar;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
public class Main extends Application {
	Rectangle2D screenBounds;
	private Boolean resizebottom = false;
	private double dx;
	private double dy;
	private FileChooser fileChooser;
	class Position { double x, y; } 
	public AppContext app;
	
	public Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg","*.jpg");
		FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
		FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("BMP files (*.png)", "*.bmp");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.getExtensionFilters().add(extFilter2);
		fileChooser.getExtensionFilters().add(extFilter3);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setResizable(true);
		screenBounds = Screen.getPrimary().getBounds();
		System.out.println(screenBounds);
		primaryStage.setTitle("App");
		app = new AppContext();
		MainBar mainbar = new MainBar(); 
		CanvasPane cv = new CanvasPane();

		VBox pane = new VBox();

		pane.getChildren().add(cv);
		
		
		VBox vbox = new VBox(mainbar);
		
		vbox.getChildren().add(pane);
		vbox.setVgrow(pane, Priority.ALWAYS);
	
		Scene scene = new Scene(vbox,1024,800);
		pane.setAlignment(Pos.CENTER);

		
		
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		final Position windowPos = new Position();
		mainbar.setOnMousePressed(new EventHandler<MouseEvent>() {
		  @Override public void handle(MouseEvent mouseEvent) {
			 System.out.println("clicked");
			 windowPos.x = primaryStage.getX() - mouseEvent.getScreenX();
			 windowPos.y = primaryStage.getY() - mouseEvent.getScreenY();
		  }
		});
		mainbar.setOnMouseDragged(new EventHandler<MouseEvent>() {
		  @Override public void handle(MouseEvent mouseEvent) {
			  primaryStage.setX(mouseEvent.getScreenX() + windowPos.x);
			  primaryStage.setY(mouseEvent.getScreenY() + windowPos.y);
		  }
		});
		mainbar.menuItem5.setOnAction(new EventHandler<ActionEvent>() {
			  @Override public void handle(ActionEvent mouseEvent) {
				  Platform.exit();
			  }
			});
		
		mainbar.menuItem2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    mainbar.filename.setText(file.getName());
                    app.file = file;
                    cv.update(app);
                }
            }
        });
		
		
		mainbar.menuItem42.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            		app.zoom *= 0.75;
                    cv.update(app);
      
            }
        });
		
		mainbar.menuItem41.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            		app.zoom /= 0.75;
                    cv.update(app);
      
            }
        });
		
		mainbar.menuItem4.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	WritableImage image = cv.snapshot(new SnapshotParameters(), null);

                // TODO: probably use a file chooser here
                //Set extension filter
 


                //Show save file dialog
                File file = fileChooser.showSaveDialog(primaryStage);
                String ext  = getExtensionByStringHandling(file.getName()).get();
                
                if(ext.equals("jpg")) ext = "jpeg";
                System.out.println("extension" + ext);
                try 
                {
                    if(file != null)
                    {
                    	if(ext.equals("jpeg")) {
                    		 System.out.println("extension save" + ext);
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
        });
		
		mainbar.menuItem3.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	WritableImage image = cv.snapshot(new SnapshotParameters(), null);

                // TODO: probably use a file chooser here
                //Set extension filter
 


                //Show save file dialog
                File file = app.file;
                System.out.println(file);
                System.out.println(app.file.getAbsolutePath());
                String ext  = getExtensionByStringHandling(file.getName()).get();
               
       
                if(ext.equals("jpg")) ext = "jpeg";
                System.out.println("extension" + ext);
                try 
                {
                 
                		if(file != null) {
                			if(ext.equals("jpeg")) {
                       		 System.out.println("extension save" + ext);
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
        });
		
		mainbar.menuItem43.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            		app.setDefaultZoom();
                    cv.update(app);
      
            }
        });
		
		primaryStage.show();

		
	}
	
	
public static void main(String[] args) {

		Application.launch(args);
	}
}