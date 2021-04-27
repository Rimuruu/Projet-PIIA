package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Documentation extends Stage{
	public Stage secondStage = null;
	public Documentation()  {
		
		this.setTitle("Documentation");
		
        Text titre = new Text();
        titre.setText("Documentation:");
        titre.setTextOrigin(null);
        titre.setLayoutX(90);
        titre.setLayoutY(90);
        titre.setWrappingWidth(200);
        titre.setFont(Font.font("Verdana", FontPosture.ITALIC,20));
        
        
        Text texte3 = new Text("\n1) Ajouter des composants :\nOutils puis cliquer sur les composants proposés (texte, formes ou emoji).\n\n"
        		+"Pour déplacer le composant :\n"
        		+ "Cliquer une fois sur le composant pour le selectionner puis, positionner le composant à  l'endroit de votre choix avec le curseur de la souris en le glissant.\n\n"
        		+ "2) Supprimer/ redimensionner :\n"
        		+ "Sélectionner le composant que vous voulez modifier, en cliquant sur celui-ci, puis : \n"
        		+ "- Suppression : Édition puis Couper \n"
        		+ "- Redimensionner : Image puis Redimensionner\n\n"
        		+ "3) Ajouter un filtre à la photo :\n"
        		+ "Pour l'ajout d'un filtre à votre photo, il faut aller dans l'onglet Outils puis cliquer sur Filtre.\n"
        		+ "Une galerie de filtre sera alors proposée.\n"
        		+ "Selectionné un filtre puis cliquer sur appliquer\n\n"
        		+ "4) L'option pour mettre un composant au premier plan ou au dernier plan se trouve dans l'onglet Affichage, \n"
        		+ "Il faut que le composant soit selectionné \n\n"
 
        		+ "5) Sauvegarder la photo :\n"
        		+ "L'enregistrement de l'image se réalise dans le menu déroulant Fichier puis Enregistrer\n"
        		+ "Pour préciser le dossier destination ainsi que l'extension du fichier il faut utiliser l'option Enregistrer sous.");
		
		
		
		HBox hbox = new HBox(titre);
		hbox.setPrefWidth(200);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
//		label2.setPadding(new Insets(8, 5, 8, 5));
		VBox vbox = new VBox(hbox,texte3);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(vbox);
		this.setScene(scene);
		
		
		this.show();
	}
}
