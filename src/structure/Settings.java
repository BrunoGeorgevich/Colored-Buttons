package structure;

import java.awt.Color;

public class Settings {

	private int difficulty = -1;
	private Color primaryColor = null;
	private Color secondaryColor = null;
	private String keyBindings = null;

	public Settings(String newDifficulty, String newPrimaryColor, String newSecondaryColor, String newKeyBindings) {
		difficulty = translateDifficulty(newDifficulty);
		primaryColor = translateColor(newPrimaryColor);
		secondaryColor = translateColor(newSecondaryColor);
		keyBindings = newKeyBindings;
	}

	public static int translateDifficulty(String difficulty) {
		switch(difficulty) {
		case "Fácil":
			return 3;
		case "Médio":
			return 5;
		case "Difícil":
			return 7;
		default:
			return -1;
		}
	}
	
	public static Color translateColor(String color) {
		switch(color) {
		case "Branco":
			return Color.WHITE;
		case "Preto":
			return Color.BLACK;
		case "Azul":
			return Color.BLUE;
		default:
			return null;
		}
	}

	//Getters and Setters

	public int getDifficulty() {
		return difficulty;
	}

	public Color getPrimaryColor() {
		return primaryColor;
	}

	public Color getSecondaryColor() {
		return secondaryColor;
	}

	public String getKeyBindings() {
		return keyBindings;
	}

}
