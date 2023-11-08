import java.util.NoSuchElementException;

public class FibonacciHeap<T> {

    private Node<Integer> min = null;
    private int size = 0;

    public FibonacciHeap() {
    }

    public Node<Integer> insert(int val) {
        Node<Integer> n = new Node<>(val);
        min = mergeLists(min, n);
        size++;
        return n;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return min == null;
    }

    public Node<Integer> find_min() {
        if (isEmpty()) throw new NoSuchElementException("empty heap");
        return min;
    }

    public int extract_min() {
        if (isEmpty()) throw new NoSuchElementException("empty heap");

        Node<Integer> z = this.min;
        Node<Integer> c = z.getChild();
        if (c != null) {
            Node<Integer> k = c;
            Node<Integer> p;
            do {
                p = c.getNext();
                min = mergeLists(min, c);
                c.setParent(null);
                c = p;
            } while (c != null && c != k);
        }
        z.getPrev().setNext(z.getNext());
        z.getNext().setPrev(z.getPrev());
        if (z == z.getNext()) {
            this.min = null;
        } else {
            this.min = z.getNext();
            consolidate();
        }
        this.size--;
        return z.getVal();
    }

    public void decrease_key(Node<Integer> x, int k) {
        if (k > x.getVal())
            throw new IllegalArgumentException("New key is greater than current key");

        x.setVal(k);
        Node<Integer> y = x.getParent();
        if (y != null && x.getVal() < y.getVal()) {
            cut(x, y);
            cascading_cut(y);
        }
        if (x.getVal() < min.getVal()) {
            min = x;
        }
    }

    private void consolidate() {
        int maxDegree = (int) Math.floor(Math.log(size) / Math.log(2)) + 1;
        Node<Integer>[] degreeArray = new Node[maxDegree];

        Node<Integer> currentNode = min;
        Node<Integer> nextNode;
        do {
            nextNode = currentNode.getNext();
            int degree = currentNode.getDegree();

            while (degreeArray[degree] != null) {
                Node<Integer> other = degreeArray[degree];
                if (currentNode.getVal() > other.getVal()) {
                    Node<Integer> temp = currentNode;
                    currentNode = other;
                    other = temp;
                }
                link(other, currentNode);
                degreeArray[degree] = null;
                degree++;
            }
            degreeArray[degree] = currentNode;
            currentNode = nextNode;
        } while (currentNode != min);

        min = null;
        for (int i = 0; i < maxDegree; i++) {
            if (degreeArray[i] != null) {
                if (min == null) {
                    min = degreeArray[i];
                } else {
                    min = mergeLists(min, degreeArray[i]);
                }
            }
        }
    }

    private void cut(Node<Integer> x, Node<Integer> y) {
        // Remove node x from the child list of y.
        x.getNext().setPrev(x.getPrev());
        x.getPrev().setNext(x.getNext());
        y.setChild(x);

        if (y.getChild() == x) {
            y.setChild(x.getNext());
        }

        x.setNext(x);
        x.setPrev(x);
        x.setParent(null);
        x.setMarked(false);

        // Decrease the degree of y.
        y.decreaseDegree();

        // Add x to the root list.
        min = mergeLists(min, x);
    }

    private void cascading_cut(Node<Integer> y) {
        Node<Integer> z = y.getParent();
        if (z != null) {
            if (!y.isMarked()) {
                y.setMarked(true);
            } else {
                cut(y, z);
                cascading_cut(z);
            }
        }
    }

    private Node<Integer> mergeLists(Node<Integer> a, Node<Integer> b) {
        if (a == null && b == null) {
            return null;
        } else if (a != null && b == null) {
            return a;
        } else if (a == null) {
            return b;
        } else {
            Node<Integer> temp = a.getNext();
            a.setNext(b.getNext());
            a.getNext().setPrev(a);
            b.setNext(temp);
            b.getNext().setPrev(b);
            return a.getVal() < b.getVal() ? a : b;
        }
    }
}
