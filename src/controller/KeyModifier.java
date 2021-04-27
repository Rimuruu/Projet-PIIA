package controller;



import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Preference;

public class KeyModifier  extends Stage{
	public KeyCode k;
	public  KeyModifier(Stage owner,KeyCode k,String action) {
		super(StageStyle.UTILITY);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(owner);
		this.k = k;
		this.setTitle("Modifier un raccourci");
		BorderPane bp = new BorderPane(new Label("Appuyer sur la touche"));
		
		this.setScene(new Scene(bp, 200, 150));
		this.getScene().setOnKeyPressed(event -> {
			if(event.getCode().isLetterKey()) {
				this.k = event.getCode();
				this.close();
			}
			
		});
		this.showAndWait();
		
	}
	
	
		
	}
	