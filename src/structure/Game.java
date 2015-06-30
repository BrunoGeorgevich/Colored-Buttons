package structure;

import java.util.ArrayList;

public class Game {

	//Attributes
	private int numOfLines = -1; 
	private ArrayList<Line> lines = new ArrayList<Line>();

	private int prevNumber = -1;
	private Settings settings = null;
	
	//Methods

	public Game(Settings newSettings){

		settings = newSettings;
		numOfLines = settings.getDifficulty();

		for(int i = 0; i < numOfLines; i++) {
			
			int number = (int)(Math.random()*numOfLines);

			while(number == prevNumber)
				number = (int)(Math.random()*numOfLines);

			prevNumber = number;
			
			lines.add(new Line(number, numOfLines));
			
		}
	}

	public void printLines() {
		for (Line line : lines) {
			line.print();
			System.out.println();
		}
	}

	public void rightAnswer() {

		lines.remove(lines.size() - 1);

		int number = (int)(Math.random()*numOfLines);

		while(number == prevNumber)
			number = (int)(Math.random()*numOfLines);

		prevNumber = number;

		lines.add(0,new Line(number, numOfLines));
	}

	//Getters and Setters

	public ArrayList<Line> getLines() {
		return lines;
	}

	public int getNumOfLines() {
		return numOfLines;
	}	
}
