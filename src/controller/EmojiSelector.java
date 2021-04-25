package controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import App.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppContext;
import model.EmojiLoader;
import model.Filter;
import model.ImageShape;
import view.CanvasPane;

public class EmojiSelector  extends Stage{

	public HBox selector = null;
	public Image emoji = null;

	
	public EmojiSelector(AppContext app,CanvasPane cv) {
		super(StageStyle.UTILITY);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(Main.primaryStage);
		this.setResizable(false);
		FlowPane flow = new FlowPane();
		VBox vbox = new VBox(); 
		HBox hbox = new HBox();
		ScrollPane scrollPane = new ScrollPane();
	    flow.setPadding(new Insets(5, 5, 5, 5));
	    flow.setVgap(5);
	    flow.setHgap(5);
	    flow.setPrefWrapLength(625); 

	    Button b = new Button("Appliquer");
	    hbox.getChildren().addAll(b);
	 
	    	for (Image emoji : EmojiLoader.emojis){
	

	        ImageView iv = new ImageView(emoji);
	        Label t = new Label("Emoji");
	        HBox tmp = new HBox();
	        VBox tmp2 = new VBox();
	        HBox tmp3 = new HBox();
	        
	        tmp.setPadding(new Insets(5, 5, 5, 5));
	        iv.setFitWidth(50);
	        iv.setFitHeight(50);
	        iv.setOnMouseClicked(e->select(tmp,emoji));
	        tmp.getChildren().add(iv);
	        tmp3.getChildren().add(t);
	        tmp3.setAlignment(Pos.CENTER);
	        tmp2.getChildren().addAll(tmp,tmp3);
	        flow.getChildren().add(tmp2);

	    }
	    b.setAlignment(Pos.CENTER);
	    hbox.setHgrow(b, Priority.ALWAYS);
	    hbox.setAlignment(Pos.CENTER);
	    vbox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
	    	    CornerRadii.EMPTY,
	    	    Insets.EMPTY)));
	    scrollPane.setContent(flow);
	    vbox.getChildren().addAll(scrollPane,hbox);
	    b.setOnMouseClicked(e->submit(app,cv));
	   
	    
        // Always show vertical scroll bar
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        // Horizontal scroll bar is only displayed when needed
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setScene(new Scene(vbox, 650, 340));
		this.show();
		
	}
	
	
	public void select(HBox hb,Image e) {
		if(selector != null && emoji != null) {
			selector.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
		    	    CornerRadii.EMPTY,
		    	    Insets.EMPTY)));
			
		}
        hb.setBackground(new Background(new BackgroundFill(Color.SKYBLUE,
	    	    CornerRadii.EMPTY,
	    	    Insets.EMPTY)));
        this.selector = hb;
        this.emoji = e;
	}
	
	public void submit(AppContext app,CanvasPane cv) {
		if(emoji!= null ) {
			app.composants.add(new ImageShape(emoji));
			app.setIsSaved(false);
			
		}
		this.close();
		cv.update(app);
	}

	}
	
	
	
	
	