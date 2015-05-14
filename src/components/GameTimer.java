package components;

import java.util.Timer;
import java.util.TimerTask;

import windows.GameWindow;

public class GameTimer {
	private Timer timer;
	private int currentTime = -1;

	public GameTimer(int delay, int seconds) {
		timer = new Timer();
		currentTime = seconds;
		timer.schedule(new GameTimerTask(), delay, 220);
	}

	public void incrementTime() {
		currentTime++;
	}

	public int getCurrentTime() {
		return currentTime;
	}
	
	public void stop() {
		timer.cancel();
	}

	private class GameTimerTask extends TimerTask {
		public void run() {
			if(currentTime == 0) {
				GameWindow.gameWindow.lostGame();	
			} else {
				currentTime--;
				GameWindow.gameWindow.refreshHeaderBar();
			}
		}
	}

	//    public static void main(String args[]) {
	//        new Reminder(5);
	//        System.out.println("Task scheduled.");
	//    }
}
