package model;

public class NodeList {
	private long value;
	NodeList next;
	NodeList prev;

	public NodeList(long value) {
		this.value = value;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public NodeList getNext() {
		return next;
	}
	public void setNext(NodeList next) {
		this.next = next;
	}
	public NodeList getPrev() {
		return prev;
	}
	public void setPrev(NodeList prev) {
		this.prev = prev;
	}


}
