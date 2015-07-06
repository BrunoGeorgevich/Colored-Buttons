package structure;

import java.util.ArrayList;

/*
 * Essa classe representa, estruturalmente,
 * as linahs da class Game
 */

public class Line {

	private int rightNumber = -1;
	private int lineSize = -1;
	
	private ArrayList<Integer> numbers = new ArrayList<Integer>();

	public Line(int number, int size) {
		rightNumber = number;
		lineSize = size;

		for(int i = 0; i < lineSize; i++) {
			if(i == rightNumber) 
				numbers.add(new Integer(1));			
			else
				numbers.add(new Integer(0));
		}
	}
	
	public void print() {
		for (Integer integer : numbers) {
			System.out.print(integer + " ");
		}
	}

	//Getters and Setters
	
	public ArrayList<Integer> getNumbers() {
		return numbers;
	}

	public int getRightNumber(){
		return rightNumber;
	}

}
