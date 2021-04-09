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
import model.Filter;
import view.CanvasPane;

public class FiltreSelector  extends Stage{
	public Image image;
	public HBox selector = null;
	public Filter f = null;
	public static HashMap<String,Filter> filters = new HashMap<String,Filter>(){{
		put("Négatif", new Filter("Négatif", c -> c.invert()));
	    put("NEB", new Filter("Noir et Blanc", c -> c.grayscale()));
	    put("Cyan", new Filter("Cyan", c ->{
	    	return c.deriveColor(-180, c.getSaturation(), c.getBrightness(), c.getOpacity());
	    }));
	    put("Sepia", new Filter("Cyan", c ->{
	    	return c.deriveColor(-180, c.getSaturation(), c.getBrightness(), c.getOpacity());
	    }));
	   
	}};
	
	
	public FiltreSelector(AppContext app,CanvasPane cv) {
		super(StageStyle.UTILITY);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(Main.primaryStage);
		this.setResizable(false);
		FlowPane flow = new FlowPane();
		VBox vbox = new VBox(); 
		HBox hbox = new HBox();
		//vbox.setAlignment(Pos.CENTER);
	    flow.setPadding(new Insets(5, 5, 5, 5));
	    flow.setVgap(3);
	    flow.setHgap(2);
	    flow.setPrefWrapLength(170); // preferred width allows for two columns
	    this.image = app.image;
	    Button b = new Button("Appliquer");
	    hbox.getChildren().addAll(b);
	    Iterator it = filters.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Filter f = (Filter) pair.getValue();
	        ImageView iv = new ImageView(applyFilter(app.image,f));
	        HBox tmp = new HBox();
	        tmp.setPadding(new Insets(5, 5, 5, 5));
	        iv.setFitWidth(200);
	        iv.setFitHeight(120);
	        iv.setOnMouseClicked(e->select(tmp,f));
	        tmp.getChildren().add(iv);
	        flow.getChildren().add(tmp);

	    }
	    b.setAlignment(Pos.CENTER);
	    hbox.setHgrow(b, Priority.ALWAYS);
	    hbox.setAlignment(Pos.CENTER);
	    vbox.setBackground(new Background(new BackgroundFill(Color.WHITE,
	    	    CornerRadii.EMPTY,
	    	    Insets.EMPTY)));
	    vbox.getChildren().addAll(flow,hbox);
	    b.setOnMouseClicked(e->submit(app,cv));
		this.setScene(new Scene(vbox, 645, 300));
		this.show();
		
	}
	
	
	public void select(HBox hb,Filter f) {
		if(selector != null && f != null) {
			selector.setBackground(new Background(new BackgroundFill(Color.WHITE,
		    	    CornerRadii.EMPTY,
		    	    Insets.EMPTY)));
			
		}
        hb.setBackground(new Background(new BackgroundFill(Color.BLUE,
	    	    CornerRadii.EMPTY,
	    	    Insets.EMPTY)));
        this.selector = hb;
        this.f = f;
	}
	
	public void submit(AppContext app,CanvasPane cv) {
		if (selector != null && f != null) app.image= applyFilter(image,f);
		this.close();
		cv.update(app);
	}
	
	public Image applyFilter(Image i,Filter filtre)  {
		Image image = i;
		Image image2 =  filtre.apply(image);
        return image2;

	}
	
	
	
	
	
	
}