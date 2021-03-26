package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.Instant;

public class MainBar extends HBox {
	Label filename;
	public MainBar() {
		
		MenuBar menuBar = new MenuBar();
	

		Menu menu1 = new Menu("Fichier");
		Menu menu2 = new Menu("�dition");
		Menu menu3 = new Menu("Image");
		Menu menu4 = new Menu("Affichage");
		Menu menu5 = new Menu("Outils");
		Menu menu6 = new Menu("Aide");
		this.filename = new Label("filename");

		HBox labelbox = new HBox(this.filename);
		labelbox.setAlignment(Pos.CENTER_RIGHT);

		
		MenuItem menuItem1 = new MenuItem("Nouveau");
		MenuItem menuItem2 = new MenuItem("Ouvrir");
		MenuItem menuItem3 = new MenuItem("Enregistrer");
		MenuItem menuItem4 = new MenuItem("Enregistrer sous");
		MenuItem menuItem5 = new MenuItem("Quitter");
		
		MenuItem menuItem21 = new MenuItem("Copier");
		MenuItem menuItem22 = new MenuItem("Couper");
		MenuItem menuItem23 = new MenuItem("Coller");
		Menu menuItem24 = new Menu("Police");
		MenuItem menuItem25 = new MenuItem("Pr�f�rence");
		MenuItem menuItem26 = new MenuItem("Annuler");
		
		
		MenuItem menuItem31 = new MenuItem("Redimensionner");
		MenuItem menuItem32 = new MenuItem("Mettre en avant");
		MenuItem menuItem33 = new MenuItem("Mettre en arri�re");
		
		MenuItem menuItem41 = new MenuItem("Zoom avant");
		MenuItem menuItem42 = new MenuItem("Zoom arri�re");
		MenuItem menuItem43 = new MenuItem("Restaurer le Zoom par d�faut");
		
		MenuItem menuItem51 = new MenuItem("Filtre");
		MenuItem menuItem52 = new MenuItem("Texte");
		Menu menuItem53 = new Menu("Formes");
		MenuItem menuItem54 = new MenuItem("Emoji");
		
		MenuItem menuItem61 = new MenuItem("Documentation");
		MenuItem menuItem62 = new MenuItem("Cr�dit");

		MenuItem menuItemForme1 = new MenuItem("Carr�");
		MenuItem menuItemForme2 = new MenuItem("Triangle");
		MenuItem menuItemForme3 = new MenuItem("Ovale");
		MenuItem menuItemForme4 = new MenuItem("�toile");
		
		menuItem53.getItems().add(menuItemForme1);
		menuItem53.getItems().add(menuItemForme2);
		menuItem53.getItems().add(menuItemForme3);
		menuItem53.getItems().add(menuItemForme4);
		
		menu1.getItems().add(menuItem1);
		menu1.getItems().add(menuItem2);
		menu1.getItems().add(menuItem3);
		menu1.getItems().add(menuItem4);
		menu1.getItems().add(menuItem5);
		
		menu2.getItems().add(menuItem21);
		menu2.getItems().add(menuItem22);
		menu2.getItems().add(menuItem23);
		menu2.getItems().add(menuItem24);
		menu2.getItems().add(menuItem25);
		menu2.getItems().add(menuItem26);
		
		menu3.getItems().add(menuItem31);
		menu3.getItems().add(menuItem32);
		menu3.getItems().add(menuItem33);
		
		menu4.getItems().add(menuItem41);
		menu4.getItems().add(menuItem42);
		menu4.getItems().add(menuItem43);
		
		menu5.getItems().add(menuItem51);
		menu5.getItems().add(menuItem52);
		menu5.getItems().add(menuItem53);
		menu5.getItems().add(menuItem54);
		
		menu6.getItems().add(menuItem61);
		menu6.getItems().add(menuItem62);
	

		
		
		menuBar.getMenus().add(menu1);
		menuBar.getMenus().add(menu2);
		menuBar.getMenus().add(menu3);
		menuBar.getMenus().add(menu4);
		menuBar.getMenus().add(menu5);
		menuBar.getMenus().add(menu6);
		menuBar.setBackground(Background.EMPTY);
		
		this.getChildren().addAll(menuBar,labelbox);
		this.setHgrow(labelbox, Priority.ALWAYS);
		this.setBackground(new Background(new BackgroundFill(Color.web("7A1D76"),CornerRadii.EMPTY, Insets.EMPTY)));

	}

}