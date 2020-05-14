package model;

public class ABBNode {
	private ABBNode left;
	private ABBNode right;
	private ABBNode parent;
	private long value;

	public ABBNode(long value) {
		this.value = value;
	}
	
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}

	public ABBNode getLeft() {
		return left;
	}
	public void setLeft(ABBNode left) {
		this.left = left;
	}
	
	public ABBNode getRight() {
		return right;
	}
	public void setRight(ABBNode right) {
		this.right = right;
	}	
	
	public ABBNode getParent() {
		return parent;
	}
	public void setParent(ABBNode parent) {
		this.parent = parent;
	}

}

