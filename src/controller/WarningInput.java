package controller;

import App.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppContext;
import view.CanvasPane;

public class WarningInput  extends Stage{

	int b = 0;
	
	public WarningInput (AppContext app,CanvasPane cv) {
		super(StageStyle.UTILITY);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(Main.primaryStage);
		Text l = new Text("Le fichier n'est pas sauvegardé.");
		Text l2 = new Text(" Voulez-vous le sauvegarder ?");
		 TextFlow textFlow = new TextFlow(l, l2);
		
		Button b1 = new Button("Oui");
		Button b2 = new Button("Non");
		VBox v = new VBox();
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		h1.getChildren().add(textFlow);
		h1.setPadding(new Insets(5, 5, 5, 5));
		h2.getChildren().add(b1);
		h2.getChildren().add(b2);
		h2.setPadding(new Insets(5, 5, 5, 5));
		
		v.getChildren().addAll(h1,h2);
		
		b1.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            		b = 1; 
            		close();
      
            }
        });
		
		b2.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            		b = 2; 
            		close();
      
      
            }
        });
		this.setScene(new Scene(v, 200, 100));
		this.showAndWait();
		
	}
	
	public int getResult() {
		return b;
	}
	

	

	
	
	
}
	