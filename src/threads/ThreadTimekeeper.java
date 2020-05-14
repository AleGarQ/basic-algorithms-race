package threads;

import ui.RaceGUI;

public class ThreadTimekeeper extends Thread {
	private RaceGUI raceGUI;
	private boolean run;

	public ThreadTimekeeper(RaceGUI race) {
		raceGUI = race;
		run = true;
	}

	@Override
	public void run() {
		while (run) {
			try {
				raceGUI.timekeeper();
				if (raceGUI.arrayProgress() == 1 && raceGUI.abbProgress() == 1 && raceGUI.linkedProgress() == 1) {
					run = false;
				}
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
