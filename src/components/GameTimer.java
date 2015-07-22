package components;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {

	public GameTimer(int milliseconds, Runnable action) {
		this.schedule(new GameTask(action), 0, milliseconds);			
	}
	
	private class GameTask extends TimerTask {

		private Runnable action = null;
		
		public GameTask(Runnable a) {
			action = a;
		}
		
		public void run() {
			action.run();
		}
		
	}
}
