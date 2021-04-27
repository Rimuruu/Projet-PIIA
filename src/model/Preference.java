package model;

import java.util.HashMap;

import javafx.scene.input.KeyCode;

public class Preference {
	public static KeyCode COPIER = KeyCode.C;
	public static KeyCode COLLER = KeyCode.V;
	public static KeyCode ANNULER = KeyCode.Z;
	public static KeyCode COUPER = KeyCode.X;
	public static KeyCode SAVE = KeyCode.S;
	
	public HashMap<String,KeyCode> inputs = new HashMap<String,KeyCode>(){{
		put("Copier", KeyCode.C);
	    put("Coller",KeyCode.V);
	    put("Annuler", KeyCode.Z);
	    put("Couper", KeyCode.X);
	    put("Enregistrer", KeyCode.S);
	   
	}};
	

	
	
	public static void setPreference(Preference p) {
		COPIER = p.inputs.get("Copier");
		COLLER = p.inputs.get("Coller");
		ANNULER = p.inputs.get("Annuler");
		COUPER = p.inputs.get("Couper");
		SAVE = p.inputs.get("Enregistrer");
	}
	
	
	
	
	
}