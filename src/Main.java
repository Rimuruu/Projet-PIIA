import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import view.MainBar;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("App");
		MainBar mainbar = new MainBar(); 
		VBox vbox = new VBox(mainbar);
		
		Scene scene = new Scene(vbox,800,600);
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		

		
		primaryStage.show();
		
		
	}
	
	
public static void main(String[] args) {

		Application.launch(args);
	}
}