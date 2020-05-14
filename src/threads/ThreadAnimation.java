package threads;

import ui.RaceGUI;

public class ThreadAnimation extends Thread {

	private RaceGUI raceGUI;
	private boolean run;

	public ThreadAnimation(RaceGUI race) {
		raceGUI = race;
		run = true;
	}

	@Override
	public void run() {
		int one = 33;
		int two = 10;
		boolean up = false;
		boolean change = false;
		while (run) {
			try {
				raceGUI.circles(one, two, change);
				if (raceGUI.arrayProgress() == 1 && raceGUI.linkedProgress() == 1 && raceGUI.abbProgress() == 1) {
					run = false;
				}
				sleep(90);
				if (one == 10) {
					up = true;
				}
				if (one == 33) {
					up = false;
				}
				if (one == 21) {
					change = !change;
				}
				if (up == true) {
					one++;
					two--;
				} else {
					one--;
					two++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
