package model;

public class ABB {
	private ABBNode root;
	private long time;
	private int count;

	public ABB(long time, int count) {
		this.time = time;
		this.count = count;
	}

	public ABBNode getRoot() {
		return root;
	}
	public void setRoot(ABBNode root) {
		this.root = root;
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

	public void addIterative(ABBNode node) {
		
		ABBNode toAdd = node;
		
		if (root == null) {
			root = toAdd;
		}else{
			ABBNode current = root;
			boolean added = false;

			while (!added) {

				if (toAdd.getValue() > current.getValue()) {
					if (current.getRight() == null) {
						current.setRight(toAdd);
						added = true;
					} else {
						current = current.getRight();
					}
				} else {
					if (current.getLeft() == null) {
						current.setLeft(toAdd);
						added = true;
					} else {
						current = current.getLeft();
					}
				}
			}
		}
	}

	public boolean searchIterative(long num) {
		long first = System.currentTimeMillis();
		
		boolean keep = true;
		boolean found = false;
		ABBNode current = root;
		
		if (root != null) {
			
			while (current != null && keep) {
				
				if (current.getValue() == num) {
					found = true;
					keep = false;
				}else if (current.getValue() <= num) {
					current = current.getLeft();
				}else if (current.getValue() >= num) {
					current = current.getRight();
				}
			}
		}
		long end = System.currentTimeMillis();
		time = end - first;
		
		return found;
	}

	public void addRecursive(ABBNode node, long value) {
		if(node == null) {
			root = new ABBNode(value);
		}else {
			if (value < node.getValue()) {
				if (node.getLeft() != null) {
					addRecursive(node.getLeft(), value);
				} else {
					node.setLeft(new ABBNode(value));
				}
			} else if (value > node.getValue()) {
				if (node.getRight() != null) {
					addRecursive(node.getRight(), value);
				} else {
					node.setRight(new ABBNode(value));
				}
			}
		}
	}

	public boolean searchRecursive(ABBNode node, long value) {

		if (node == null)
			return false;
		if (node.getValue() == value)
			return true;
		if (value < (int) node.getValue())
			return searchRecursive(node.getLeft(), value);
		else if (value > node.getValue())
			return searchRecursive(node.getRight(), value);
		
		return false;
	}
}
