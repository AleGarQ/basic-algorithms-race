package model;

import java.util.ArrayList;

public class Arraylist {
	private ArrayList<Long> array;
	private long time;
	private int count;

	public Arraylist(long time, int count) {
		this.time = time;
		this.count = count;
		array = new ArrayList<>();
	}

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<Long> getArrayList() {
		return array;
	}

	public void add(long value) {
		array.add(value);
	}

	public boolean searchIterative(long value) {
		if (array.isEmpty() != false) {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) == value) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean removeIterative(long value) {
		if (array.isEmpty() != false) {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) == value) {
					array.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	public boolean searchRecursive(long value) {
		if (array.isEmpty() == false) {
			return searchRecursive(0, value);
		}
		return false;
	}

	private boolean searchRecursive(int pos, long value) {
		if (pos > (array.size() - 1))
			return false;

		if (array.get(pos) == value)
			return true;
		else
			return searchRecursive(pos + 1, value);

	}

	public boolean recursiveRemove(long value) {
		if (array.isEmpty() == false) {
			return removeRecursive(0, value);
		}
		return false;
	}

	private boolean removeRecursive(int pos, long value) {
		if (pos > (array.size() - 1))
			return false;

		if (array.get(pos) == value) {
			array.remove(pos);
			return true;
		}else
			return removeRecursive(pos + 1, value);
	}

}
