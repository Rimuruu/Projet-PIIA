package controller;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import App.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Preference;

public class PreferenceModifier  extends Stage{
	Preference p;
	HashMap<String,Button> buttonMap = new HashMap<String,Button>();
	public  PreferenceModifier(Preference p) {
		super(StageStyle.UTILITY);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(Main.primaryStage);
		this.setTitle("Préférence");
		 HBox containerh = new HBox();
		 HBox containerh2 = new HBox();
		 VBox containerv = new VBox();
		 containerh.setPadding(new Insets(5, 5, 5, 5));
		 this.p = p;
		 GridPane gridpane = new GridPane();
		 gridpane.setVgap(10);
		 gridpane.setHgap(10);
		 
		 Iterator it = p.inputs.entrySet().iterator();
		 	int row = 0;
		    while (it.hasNext()) {
		    	Map.Entry pair = (Map.Entry)it.next();
		    	Label t = new Label((String) pair.getKey());
		    	Button b = new Button("CTRL+"+pair.getValue().toString());
		    	buttonMap.put((String) pair.getKey(), b);
		    	b.setOnAction(event ->{
		    		KeyModifier k = new KeyModifier(this,(KeyCode) pair.getValue(),(String) pair.getKey());
		    		
		    		Iterator it2 = p.inputs.entrySet().iterator();

				    while (it2.hasNext()) {
				    	Map.Entry pair2 = (Map.Entry)it2.next();
				    	if(pair2.getValue() == k.k) {
				    		Button tmp = buttonMap.get(pair2.getKey());
				    		tmp.setText("Vide");
				    		p.inputs.replace((String) pair2.getKey(), null);
				    		break;
				    	}
				    }
		    		
				    buttonMap.get(pair.getKey()).setText("CTRL+"+k.k.toString());
		    		p.inputs.replace((String) pair.getKey(), k.k);
		    		
		    	} );
		    	gridpane.add(t, 0, row);
		    	gridpane.add(b, 1, row);
		    	row++;
		    	
		    }
		Button submit = new Button("Appliquer");
		submit.setOnAction(event -> submit());
		containerh.getChildren().add(gridpane);
		containerh.setAlignment(Pos.CENTER);
		
		containerh2.getChildren().add(submit);
		containerh2.setAlignment(Pos.CENTER);
		containerv.getChildren().addAll(containerh,containerh2);
		containerv.setAlignment(Pos.CENTER);
		containerh2.setPadding(new Insets(20, 20, 20, 20));
		
		this.setScene(new Scene(containerv, 400, 300));
		this.show();
		
	}
	
	public void submit() {
		Preference.setPreference(p);
		this.close();
	}
		
	}
	