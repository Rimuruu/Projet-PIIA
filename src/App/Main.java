package App;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.AppContext;
import model.EmojiLoader;
import model.ShapeLoader;
import model.Preference;
import view.CanvasPane;
import view.MainBar;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import controller.AppController;
import controller.Credit;
import controller.Documentation;
import controller.EmojiSelector;
import controller.FileController;
import controller.FiltreSelector;
import controller.PreferenceModifier;

public class Main extends Application {
	Rectangle2D screenBounds;
	private Boolean resizebottom = false;
	private double dx;
	private double dy;
	private FileChooser fileChooser;
	class Position { double x, y; } 
	public AppContext app;
	public static ArrayList<AppContext> historique = new ArrayList<AppContext>();
	
	public MainBar mainbar ; 
	public CanvasPane cv;
	public Position windowPos;
	public Preference pref;
	public static Stage primaryStage;
	
	
	

	
	public static void save(AppContext app,CanvasPane cv) {
		historique.add(app.deepCopy(cv));
	}
	
	public void annuler(CanvasPane cv) {
		if(historique.size() >0) {
			app.setHistorique(historique.remove(historique.size()-1));		
			cv.update(app);
		}

		}
	
	
	 private double valueOf(Color c) {
	        return c.getRed() + c.getGreen() + c.getBlue();
	    }
	
	public void setController(Stage primaryStage) {
			mainbar.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			  @Override public void handle(MouseEvent mouseEvent) {
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
					primaryStage.close();
					
					
					 
				  }
				});
			
			mainbar.menuItem2.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	FileController.open(fileChooser,primaryStage,cv, mainbar,app);
	            }
	        });
			
			
			mainbar.menuItem42.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.ZoomOut(app, cv);
	      
	            }
	        });
			
			mainbar.menuItem41.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            	AppController.ZoomIn(app, cv);
	      
	            }
	        });
			
			mainbar.menuItem4.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            	FileController.saveAs(fileChooser,primaryStage,cv,app);
	      
	            }
	        });
			
			mainbar.menuItem3.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            	FileController.save(fileChooser,primaryStage,cv,app);
	            }
	        });
			
			mainbar.menuItem43.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.ZoomDefault(app,cv);
	      
	      
	            }
	        });
			
			mainbar.menuItem1.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		FileController.newFile(fileChooser, primaryStage, app,mainbar,cv);
	      
	      
	            }
	        });
			
			mainbar.menuItemForme3.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.addEllipse(app, cv);
	      
	      
	            }
	        });
			
			mainbar.menuItemForme2.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.addTriangle(app, cv);
	      
	      
	            }
	        });
			
			
			mainbar.menuItemForme4.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.addEtoile(app, cv);
	      
	      
	            }
	        });
			
			mainbar.menuItemForme1.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.addRectangle(app, cv);
	      
	      
	            }
	        });
			
			mainbar.menuItem52.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.addText(app, cv);
	      
	      
	            }
	        });
			
			
			mainbar.menuItem21.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            	AppController.Copier(app);
	      
	      
	            }
	        });
			mainbar.menuItem22.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.Couper(app,cv);
	      
	      
	            }
	        });
			mainbar.menuItem23.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.Coller(app, cv);
	      
	      
	            }
	        });
			
			mainbar.menuItem51.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            			
	            			FiltreSelector selector = new FiltreSelector(app,cv);
					
	      
	      
	            }
	        });
			
			mainbar.menuItem54.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            			
	            			EmojiSelector selector = new EmojiSelector(app,cv);
					
	      
	      
	            }
	        });
			
			mainbar.menuItem31.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.Redimensionner(app, cv);
	      
	      
	            }
	        });
			
			mainbar.menuItem32.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            	AppController.moveComposantDown(app,cv);;
	      
	      
	            }
	        });
			
			mainbar.menuItem33.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {
	            		AppController.moveComposantUp(app,cv);;
	      
	      
	            }
	        });
		
			mainbar.menuItem61.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {

						Documentation doc = new Documentation();
						      
	            }
	        });
			
			mainbar.menuItem62.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {

						Credit cred = new Credit();
	            }
	        });
			
			mainbar.menuItem26.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {

	            	annuler(cv);
						      
	            }
	        });
			
			primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	            	keyHandler(event); 
	            }
	        });
			
			mainbar.menuItem25.setOnAction(new EventHandler<ActionEvent>() {
				 
	            @Override
	            public void handle(ActionEvent event) {

	            	PreferenceModifier p = new PreferenceModifier(pref);
						      
	            }
	        });
		
		
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg","*.jpg");
		FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
		FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("BMP files (*.png)", "*.bmp");
		FileChooser.ExtensionFilter extFilter4 = new FileChooser.ExtensionFilter("PH files (*.ph)", "*.ph");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.getExtensionFilters().add(extFilter2);
		fileChooser.getExtensionFilters().add(extFilter3);
		fileChooser.getExtensionFilters().add(extFilter4);
		EmojiLoader.load();
		ShapeLoader.load();
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		//primaryStage.setResizable(true);
		
		pref = new Preference();
		screenBounds = Screen.getPrimary().getBounds();

		primaryStage.setTitle("App");
		app = new AppContext();
		mainbar = new MainBar();
		windowPos = new Position();
		cv = new CanvasPane(app);

		VBox pane = new VBox();
	
		app.addPropertyChangeListener(mainbar);
		pane.getChildren().addAll(cv);
		
		
		VBox vbox = new VBox(mainbar);
		
		vbox.getChildren().add(pane);
		vbox.setVgrow(pane, Priority.ALWAYS);
	
		Scene scene = new Scene(vbox,1024,800);
		pane.setAlignment(Pos.CENTER);

		
		
		scene.getStylesheets().add("style.css");
		
		FileController.newFile(fileChooser, primaryStage, app,mainbar,cv);
		primaryStage.setOnHiding( event -> {FileController.close(fileChooser, primaryStage, cv, mainbar, app);} );
		this.primaryStage = primaryStage;
		primaryStage.setScene(scene);
		setController(primaryStage);
		primaryStage.show();

		
	}
	
	
	

	
	public void keyHandler(KeyEvent event) {


		if(event.isControlDown()) {
			
			if(event.getCode() == Preference.ANNULER) {
				annuler(cv);
			}
			else if(event.getCode() == Preference.SAVE) {
				FileController.save(fileChooser, primaryStage, cv, app);
			}
			else if(event.getCode() == Preference.COPIER) {
				AppController.Copier(app);
			}
			else if(event.getCode() == Preference.COUPER) {
				AppController.Couper(app, cv);
			}
			else if(event.getCode() == Preference.COLLER) {
				AppController.Coller(app, cv);
			}
					
			
		}
    }
	
	
	public static void main(String[] args) {

		Application.launch(args);
	}
	
	

}