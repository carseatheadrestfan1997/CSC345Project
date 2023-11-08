/**
 * A node for a Fibonacci heap implementation.
 * In this case, the value held by the node is the priority.
 * @param <V> Not tested for values other than integers.
 */

public class Node<V> {
	private V val;
	private int degree;
	private boolean marked;
	private Node<V> next;
	private Node<V> prev;
	private Node<V> parent;
	private Node<V> child;

	public Node(V val) {
		this.val = val;
		this.degree = 0;
		this.marked = false;
		this.next = this.prev = this;
	}

	public void setVal(V val) {
		this.val = val;
	}

	public V getVal() {
		return this.val;
	}

	public int getDegree() {
		return degree;
	}

	public void subDegree() {
		this.degree--;
	}

	public void addDegree() {
		this.degree++;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public Node<V> getNext() {
		return next;
	}

	public void setNext(Node<V> next) {
		this.next = next;
	}

	public Node<V> getPrev() {
		return prev;
	}

	public void setPrev(Node<V> prev) {
		this.prev = prev;
	}

	public Node<V> getParent() {
		return parent;
	}

	public void setParent(Node<V> parent) {
		this.parent = parent;
	}

	public Node<V> getChild() {
		return child;
	}

	public void setChild(Node<V> child) {
		this.child = child;
	}
}
