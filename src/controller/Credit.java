package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Credit extends Stage{
	public Stage secondStage = null;
	public Credit() {
		
		this.setTitle("Cr�dit");
		
        Text titre = new Text();
        titre.setText("Cr�dit:");
        titre.setTextOrigin(null);
        titre.setLayoutX(90);
        titre.setLayoutY(90);
        titre.setFont(Font.font("Verdana", FontPosture.ITALIC,20));
        
        Text texte2 = new Text("Dans le but de notre projet 2021 en Programmation des Interfaces Interactives Avanc�es, nous avions eu la possibilit� de cr�er un �diteur de photo � l'aide de JavaFX.\n");
        
        Text titre1 = new Text("\nRemerciements:\n");
        titre1.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Text texte3 = new Text("Nous tenions � remercier notre tuteur de projet: Mme Zhang\n"
        		+ "Qui a su nous superviser durant cette p�riode sanitaire exceptionnelle. Elle a su �tre patiente et nous donner de son temps tout au long des TP.\n"
        		+ "Nous remercions �galement M. Grynszpan, professeur de programmation en Interfaces Int�ractives Avanc�es (IIA).\n"
        		+ "Il nous a donn� un cours de qualit� sur la th�orie et les bases de conception, d�veloppement et �valuation des Interfaces Humain-Machine (IHM).\n"
        		+ "Ce professeur a su nous transmettre sa passion et ce malgr� le distanciel.\n\nUn �norme merci aux deux !\n");
        
        Text titre2 = new Text("\nCr�ateurs et designers:\n");
        titre2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Text texte4 = new Text("Antoine Renciot (antoine.renciot@universite-paris-saclay.fr)\nBilly Thy (billy.thy@universite-paris-saclay.fr)\n");
        
        Text titre3 = new Text("\nImages:\n");
        titre3.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Text texte5 = new Text("Emoji libre de droits FLATICON\n");
        
        Text titre4 = new Text("\nCitation:\n");
        titre4.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Text quote = new Text("<< Dans la symbiose homme-machine, c'est l'homme qui doit s'adapter parce que la machine ne peut pas. >>\n~ Alan Jay Perlis\n");
		
        Hyperlink link = new Hyperlink("https://fr.tipeee.com/"); //"https://www.figma.com/file/BQOkR0X37rWD4wm674I9Fe/Projet-PIIA-%C3%A9diteur-de-photo?node-id=0%3A1"
        link.setText("Tipeee"); // Le lien hyper texte ne marche pas.
               
		HBox hbox = new HBox(titre);
		hbox.setStyle("-fx-background-color: #AC67B7;");
		hbox.setPrefWidth(200);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(hbox,texte2,titre1,texte3,titre2,texte4,titre3,texte5,titre4,quote,link);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(vbox);
		this.setScene(scene);
		
		
		this.show();
	}
}
