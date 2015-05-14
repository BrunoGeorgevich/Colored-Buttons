package components;

/*
 * Essa classe é responsável por administrar
 * todas as caracteristicas da dificuldade escolhida
 * contendo tempo inicial e numero de colunas
 */

public class Difficulty {

	//Referencias a dificuldade, ao numero de linhas e ao numero de colunas
	static public Difficulty difficulty = new Difficulty(OptionsFile.file.readFileLine(3));
	private int columns = -1;
	private int maxTime = -1;

	private Difficulty(String difficultyLevel) {this.setDifficulty(difficultyLevel);}
	
	public void setDifficulty(String difficultyLevel) {
		
		if(difficultyLevel.equals("Fácil")) {
			this.setColumns(3);
			this.setMaxTime(120);
		} else if(difficultyLevel.equals("Médio")) {
			this.setColumns(5);
			this.setMaxTime(90);
		} else if(difficultyLevel.equals("Difícil")) {
			this.setColumns(7);
			this.setMaxTime(60);
		}
	}

	public int getColumns() {
		return columns;
	}

	private void setColumns(int columns) {
		this.columns = columns;
	}

	public int getMaxTime() {
		return maxTime;
	}

	private void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}
	
}
