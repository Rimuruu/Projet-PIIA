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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		put("N�gatif", new Filter("N�gatif", c -> c.invert()));
	    put("Noir et Blanc", new Filter("Noir et Blanc", c -> c.grayscale()));
	    put("Cyan", new Filter("Cyan", c ->{
	    	return c.deriveColor(-180, c.getSaturation(), c.getBrightness(), c.getOpacity());
	    }));
	    put("Sepia", new Filter("Cyan", c ->{
	    	return c.deriveColor(-180, c.getSaturation(), c.getBrightness(), c.getOpacity());
	    }));
	   
	}};
	
	
	public FiltreSelector(AppContext app,CanvasPane cv) {
		super(StageStyle.UTILITY);
		// Le stage prend le dessus sur l'application on ne peut pas retourn� sur le stage principal sans avoir termin� avec le secondaire.
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(Main.primaryStage);
		this.setResizable(false);
		this.setTitle("Galerie des filtres");
		FlowPane flow = new FlowPane();
		VBox vbox = new VBox(); 
		HBox hbox = new HBox();;
	    flow.setPadding(new Insets(5, 5, 5, 5));
	    flow.setVgap(3);
	    flow.setHgap(2);
	    flow.setPrefWrapLength(170); 
	    this.image = app.image;
	    Button b = new Button("Appliquer");
	    hbox.getChildren().addAll(b);
	    Iterator it = filters.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Filter f = (Filter) pair.getValue();
	        ImageView iv = new ImageView(applyFilter(app.image,f));
	        Label t = new Label((String) pair.getKey());
	        HBox tmp = new HBox();
	        VBox tmp2 = new VBox();
	        HBox tmp3 = new HBox();
	        
	        tmp.setPadding(new Insets(5, 5, 5, 5));
	        iv.setFitWidth(200);
	        iv.setFitHeight(120);
	        iv.setOnMouseClicked(e->select(tmp,f));
	        tmp.getChildren().add(iv);
	        tmp3.getChildren().add(t);
	        tmp3.setAlignment(Pos.CENTER);
	        tmp2.getChildren().addAll(tmp,tmp3);
	        flow.getChildren().add(tmp2);

	    }
	    b.setAlignment(Pos.CENTER);
	    hbox.setHgrow(b, Priority.ALWAYS);
	    hbox.setAlignment(Pos.CENTER);
	    vbox.setBackground(new Background(new BackgroundFill(Color.WHITE,
	    	    CornerRadii.EMPTY,
	    	    Insets.EMPTY)));
	    vbox.getChildren().addAll(flow,hbox);
	    b.setOnMouseClicked(e->submit(app,cv));
		this.setScene(new Scene(vbox, 645, 340));
		this.show();
		
	}
	
	
	public void select(HBox hb,Filter f) {
		if(selector != null && f != null) {
			selector.setBackground(new Background(new BackgroundFill(Color.WHITE,
		    	    CornerRadii.EMPTY,
		    	    Insets.EMPTY)));
			
		}
        hb.setBackground(new Background(new BackgroundFill(Color.SKYBLUE,
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