package threads;

import java.util.ArrayList;

import model.LinkedList;
import model.NodeList;
import ui.*;

public class ThreadLinked extends Thread {
	private RaceGUI race;
	private LinkedList list;
	private String operation;
	private String mode;
	private long time;
	private ArrayList<Long> numbers;
	private ArrayList<Long> toAdd;

	public ThreadLinked(RaceGUI rc, ArrayList<Long> n, String op, String mod) {
		race = rc;
		numbers = n;
		operation = op;
		mode = mod;
		list = new LinkedList(0, 0);
		
		if (op.equals("Search") || op.equals("Delete")) {
			toAdd = race.numbers(numbers.size());
		}
	}

	@Override
	public void run() {
		long x = System.currentTimeMillis();
		if (mode.equals("Iterative")) {
			switch (operation) {
			case "Add":
				for (int i = 0; i < numbers.size(); i++) {
					list.addIterative(new NodeList(numbers.get(i)));
					race.progressLinked(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Search":
				for (int i = 0; i < toAdd.size(); i++) {
					list.addIterative(new NodeList(toAdd.get(i)));
				}
				for (int i = 0; i < numbers.size(); i++) {
					list.searchIterative(numbers.get(i));
					race.progressLinked(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Delete":
				for (int i = 0; i < toAdd.size(); i++) {
					list.addIterative(new NodeList(toAdd.get(i)));
				}
				for (int i = 0; i < numbers.size(); i++) {
					list.removeIterative(numbers.get(i));
					race.progressLinked(i + 1);
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
					list.addR(numbers.get(i));
					race.progressLinked(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Search":
				for (int i = 0; i < toAdd.size(); i++) {
					list.addR(toAdd.get(i));
				}
				for (int i = 0; i < numbers.size(); i++) {
					list.search(list.getFirst(), numbers.get(i));
					race.progressLinked(i + 1);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;

			case "Delete":
				for (int i = 0; i < toAdd.size(); i++) {
					list.addR(toAdd.get(i));
				}
				for (int i = 0; i < numbers.size(); i++) {
					list.remove(list.getFirst(), numbers.get(i));
					race.progressLinked(i + 1);
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
		race.setTimeLinked(time);
	}

}
