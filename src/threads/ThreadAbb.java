package threads;

import java.util.ArrayList;

import model.ABB;
import model.ABBNode;
import ui.RaceGUI;

public class ThreadAbb extends Thread {

	private RaceGUI race;
	private ABB abb;
	private String operation;
	private String mode;
	private long time;
	private ArrayList<Long> numbers;
	private ArrayList<Long> toAdd;

	public ThreadAbb(RaceGUI rc, ArrayList<Long> n, String op, String mod) {
		race = rc;
		numbers = n;
		operation = op;
		mode = mod;
		abb = new ABB(0, 0);
		if (op.equals("Search") || op.equals("Delete"))
			toAdd = race.numbers(numbers.size());
	}

	@Override
	public void run() {
		long x = System.currentTimeMillis();
		
		if (mode.equals("Iterative")) {
			switch (operation) {
			
			case "Add":
				for (int i = 0; i < numbers.size(); i++) {
					abb.addIterative(new ABBNode(numbers.get(i)));
					race.progressAbb(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Search":
				for (int i = 0; i < toAdd.size(); i++) {
					abb.addIterative(new ABBNode(toAdd.get(i)));
				}
				for (int i = 0; i < numbers.size(); i++) {
					abb.searchIterative(numbers.get(i));
					race.progressAbb(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Delete":
				for (int i = 0; i < toAdd.size(); i++) {
					abb.addIterative(new ABBNode(toAdd.get(i)));
				}
				for (int i = 0; i < numbers.size(); i++) {
					race.progressAbb(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			}
		} else {
			switch (operation) {
			
			case "Add":
				for (int i = 0; i < numbers.size(); i++) {
					abb.addRecursive(abb.getRoot(), numbers.get(i));
					race.progressAbb(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Search":
				for (int i = 0; i < toAdd.size(); i++) {
					abb.addRecursive(abb.getRoot(), toAdd.get(i));
				}
				for (int i = 0; i < numbers.size(); i++) {
					abb.searchRecursive(abb.getRoot(),numbers.get(i));
					race.progressAbb(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Delete":
				for (int i = 0; i < toAdd.size(); i++) {
					abb.addRecursive(abb.getRoot(), toAdd.get(i));
				}
				for (int i = 0; i < numbers.size(); i++) {
					//remove
					race.progressAbb(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			}
		}
		long y = System.currentTimeMillis();
		time = y - x;
		race.setTimeAbb(time);
	}

}
