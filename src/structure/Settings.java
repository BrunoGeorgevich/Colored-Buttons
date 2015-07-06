package structure;

import java.awt.Color;

/*
 * Essa classe detém todas as configurações do usuário
 */

public class Settings {

	private int difficulty = -1;
	private Color primaryColor = null;
	private Color secondaryColor = null;
	private String keyBindings = null;
	
	private String contentSettings = "";

	public Settings(String newDifficulty, String newPrimaryColor, String newSecondaryColor, String newKeyBindings) {
		difficulty = translateDifficulty(newDifficulty);
		primaryColor = translateColor(newPrimaryColor);
		secondaryColor = translateColor(newSecondaryColor);
		keyBindings = newKeyBindings;

		contentSettings += newDifficulty + "\n";
		contentSettings += newPrimaryColor + "\n";
		contentSettings += newSecondaryColor + "\n";
		contentSettings += newKeyBindings + "\n";
	}

	public String exportSettings() {
			return contentSettings;
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
		case "Verde":
			return Color.GREEN;
		case "Vermelho":
			return Color.RED;
		case "Amarelo":
			return Color.YELLOW;
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
