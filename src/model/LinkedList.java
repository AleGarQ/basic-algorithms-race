package model;

public class LinkedList {
	private NodeList first;
	private long time;
	private int count;

	public LinkedList(long time, int count) {
		this.time = time;
		this.count = count;
	}

	public NodeList getFirst() {
		return first;
	}
	public void setFirst(NodeList first) {
		this.first = first;
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

	public void addIterative(NodeList nl) {
		NodeList toAdd = nl;
		if (first == null) {
			first = toAdd;
		} else {
			NodeList current = first;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			toAdd.setPrev(current);
			current.setNext(toAdd);
		}
	}

	public boolean searchIterative(long snl) {
		boolean find = false;
		long start = System.currentTimeMillis();
		NodeList current = first;
		if (current != null) {
			while (current.getNext() != null && !find) {
				if (current.getValue() == snl) {
					find = true;
				}
				current = current.getNext();
			}
		}
		long end = System.currentTimeMillis();
		time = end - start;
		return find;
	}

	public boolean removeIterative(long rnl) {
		boolean remove = false;
		long star= System.currentTimeMillis();
		NodeList current = first;
		if (current != null) {
			while (current.getNext() != null && !remove) {
				if (current.getValue() == rnl) {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());
					remove = true;
				}
				current = current.getNext();
			}
		}
		long end = System.currentTimeMillis();
		time = end - star;
		return remove;
	}

	private void add(NodeList added, long data) {
		if (added.getNext() != null) {
			add(added.getNext(), data);
		} else {
			added.next = new NodeList(data);
		}
	}

	public void addR(long data) {
		if (first == null) {
			first = new NodeList(data);
		} else {
			add(first, data);
		}
	}

	public boolean search(NodeList node, long x) {
		if (node == null)
			return false;

		if (node.getValue() == x)
			return true;

		return search(node.getNext(), x);
	}

	public boolean remove(NodeList node, long x) {
		if (node == null)
			return false;

		if (node.getValue() == x) {
			node.getPrev().setNext(node.getNext());
			node.getNext().setPrev(node.getPrev());
			return true;
		}

		return remove(node.next, x);
	}

}