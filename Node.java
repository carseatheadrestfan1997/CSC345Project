
public class Node<v> {
	private v val;
	private boolean marked = false;
	private Node left = null;
	private Node right = null;
	private ArrList children;
	
	public Node(v val){
		this.children = new ArrList();
	}
	
	public void setLeft(Node node) {
		this.left = node;
	}
	
	public void setRight(Node node) {
		this.right = node;
	}

	public void addChild(Node node) {
		this.children.addLast(node);
	}
	
	public void markForDeletion() {
		this.marked = false;
	}
}
