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
import model.AppContext;
import view.CanvasPane;

public class Documentation extends Stage{
	public Stage secondStage = null;
	public Documentation() throws Exception {
		
		this.setTitle("Documentation");
		
        Text titre = new Text();
        titre.setText("Documentation:");
        titre.setTextOrigin(null);
        titre.setLayoutX(90);
        titre.setLayoutY(90);
        titre.setWrappingWidth(200);
        titre.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC,20));
        
        Text texte2 = new Text("L’application possède un menu principal avec 6 sous menu déroulant : \nFichier, Édition, Image, Affichage, Outils, Aide. \n\nCe menu respect les standards des autres logiciels d’édition photos et le choix des sous menus déroulant permettra une interface plus concise pour l’utilisateur.\n");
        Text texte3 = new Text("\n1) Ajouter des composants :\nOutils puis cliquer sur les composants proposés (texte, formes ou emoji).\n\nUne fois après avoir cliqué dessus, positionner le composant à l’endroit de votre choix avec le curseur de la souris.\n\n2)Déplacer/supprimer/ redimensionner :\nSélectionner le composant que vous voulez modifier, en cliquant sur celle-ci, puis : \n- Déplacer : Image → Sélectionner/Déplacer \n- Suppression : Édition → Couper \n- Redimensionner : Image → Redimensionner\n\n3) Ajouter des filtres aux photos :\nPour l’ajout d’un filtre à votre photo, il faut aller dans l’onglet Outils puis cliquer sur filtre.\nUne liste déroulante avec un panel de filtres sera alors proposée.\n\n4) Mettre un composant au premier plan ou au dernier plan :\nSe trouve dans l’onglet Affichage,\npar la suite choisir Mettre en avant ou Mettre en arrière, \npeut être refait plusieurs fois si l’on veut disposer le composant au dernier ou premier plan.\n\n5) Sauvegarder la photo :\nL’enregistrement se réalise dans le menu déroulant Fichier → Enregistrer sous… \nEnsuite sélectionner le fichier où enregistrer la photo.");
		
		
		
		HBox hbox = new HBox(titre);
		hbox.setPrefWidth(200);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
//		label2.setPadding(new Insets(8, 5, 8, 5));
		VBox vbox = new VBox(hbox,texte2,texte3);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(vbox);
		this.setScene(scene);
		
		
		this.show();
	}
}
