package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppContext;
import view.CanvasPane;

public class TextInput  extends Stage{
	public Text text;
	public TextField textField;
	public TextInput(Text text,AppContext app,CanvasPane cv) {
		super(StageStyle.UTILITY);
		 StackPane secondaryLayout = new StackPane();
		 this.text = text;
		 Button b = new Button("Ok");
		 this.textField = new TextField ();
		 HBox b1 = new HBox(textField);
		 HBox b2 = new HBox(b);
		 VBox vbox = new VBox(b1,b2);
		 vbox.setAlignment(Pos.CENTER);
		 b1.setAlignment(Pos.CENTER);
		 b2.setAlignment(Pos.CENTER);
		 secondaryLayout.getChildren().addAll(vbox);
		 this.setTitle("Modifier Texte");
		 b.setOnAction(new EventHandler<ActionEvent>() {
			 
	            @Override
	            public void handle(ActionEvent event) {
	            		submit(app,cv);
	      
	      
	            }
	        });
		this.setScene(new Scene(secondaryLayout, 200, 100));
		this.show();
		
	}
	
	public void submit(AppContext app,CanvasPane cv) {
		text.setText(textField.getText());
		this.close();
		cv.update(app);
		
	}
	
	
}