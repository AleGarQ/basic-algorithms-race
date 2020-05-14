package threads;

import java.util.ArrayList;

import model.Arraylist;
import ui.*;

public class ThreadArray extends Thread {

	private RaceGUI raceGUI;
	private Arraylist arraylist;
	private String operation;
	private String mode;
	private long time;
	private ArrayList<Long> numbers;
	private ArrayList<Long> toAdd;

	public ThreadArray(RaceGUI rc, ArrayList<Long> n, String op, String mod) {
		raceGUI = rc;
		numbers = n;
		operation = op;
		mode = mod;
		arraylist = new Arraylist(0, 0);
		
		if (op.equals("Search") || op.equals("Delete")) {
			toAdd = raceGUI.numbers(numbers.size());
		}
	}

	@Override
	public void run() {
		long x = System.currentTimeMillis();
		if (mode.equals("Iterative")) {
			switch (operation) {
			case "Add":
				for (int i = 0; i < numbers.size(); i++) {
					arraylist.add(numbers.get(i));
					raceGUI.progressArray(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Search":
				for (int i = 0; i < toAdd.size(); i++) {
					arraylist.add(toAdd.get(i));
				}
				for (int i = 0; i < numbers.size(); i++) {
					arraylist.searchIterative(numbers.get(i));
					raceGUI.progressArray(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Delete":
				for (int i = 0; i < toAdd.size(); i++) {
					arraylist.add(toAdd.get(i));
				}
				for (int i = 0; i < numbers.size(); i++) {
					arraylist.removeIterative(numbers.get(i));
					raceGUI.progressArray(i + 1);
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
					arraylist.add(numbers.get(i));
					raceGUI.progressArray(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Search":
				for (int i = 0; i < toAdd.size(); i++) {
					arraylist.add(toAdd.get(i));
				}
				for (int i = 0; i < numbers.size(); i++) {
					arraylist.searchRecursive(numbers.get(i));
					raceGUI.progressArray(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Delete":
				for (int i = 0; i < toAdd.size(); i++) {
					arraylist.add(toAdd.get(i));
				}
				for (int i = 0; i < numbers.size(); i++) {
					arraylist.recursiveRemove(numbers.get(i));
					raceGUI.progressArray(i + 1);
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
		raceGUI.setTimeArray(time);
	}

}
