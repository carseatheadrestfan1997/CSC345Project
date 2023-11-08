import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * File: FibonacciHeap.java
 * Description: a heap of the Fibonacci variety
 * Stores the roots of all trees in a circular doubly linked list, which is unfortunate to work with.
 * Uses two ArrayLists to keep track of the complicated deletion method.
 */

public class FibonacciHeap<T> {

    private Node<Integer> min = null;
    private int size = 0;

    /**
     * Blank constructor
     */
    public FibonacciHeap() {
    }

    /**
     * Inserts a node into the heap by merging the heap and a new node of specified value
     * @param val: the integer value, representing priority
     * @return the node which has been newly added
     */
    public Node<Integer> insert(int val) {
        Node<Integer> n = new Node<>(val);
        min = mergeLists(min, n);
        size++;
        return n;
    }

    /**
     * Returns the number of nodes in the heap
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns true if the head is empty
     */
    public boolean isEmpty() {
        return min == null;
    }

    /**
     * Returns the minimum node if it exists.
     * If it doesn't, throws a NoSuchElementException.
     */
    public Node<Integer> find_min() {
        if (isEmpty()) throw new NoSuchElementException("empty heap");
        return min;
    }

    /**
     * Pops the minimum node off the top of the heap if it exists.
     * If it doesn't, throws a NoSuchElementException.
     * Does way too much work to fix the heap afterward.
     * @return the lowest priority node in the heap.
     */
    public Node<Integer> delete_min() {
        if (isEmpty()) throw new NoSuchElementException("Empty heap");

        size--;
        Node<Integer> minVal = min;

        // first case: is this the only element? if so, just get rid of it
        // otherwise, slide it out of the way of its neighbor roots and reassign the min to whatever
        if (min.getNext() == min) {
            min = null;
        }
        else {
            min.getPrev().setNext(min.getNext());
            min.getNext().setPrev(min.getPrev());
            min.setNext(min.getNext());
        }

        // get parents out of the way since the children are going to become roots
        if (min != null && min.getChild() != null) {
            Node<Integer> cur = min.getChild();
            do {
                cur.setParent(null);
                cur = cur.getNext();
            } while (cur != min.getChild());
        }

        // merge root children into list
        min = mergeLists(min, minVal.getChild());

        // no elements left, return
        if (min == null) return minVal;

        // starts getting worse here
        // need to reduce all roots such that there's only one of each degree (since its a fibonacci heap)
        // uses an arraylist to keep track of things as it's easy (relative)
        List<Node<Integer>> trees = new ArrayList<>();

        // gotta look through whole list without messing up any traversal order
        // therefore, make sure no nodes are visited twice with another arraylist
        List<Node<Integer>> treesToVisit = new ArrayList<>();

        // runs through the circular list til it hits the start again or til the visiting list is empty
        for (Node<Integer> node = min;
             treesToVisit.isEmpty() || treesToVisit.get(0) != node; node = node.getNext()) {
            treesToVisit.add(node);
        }

        // go through the new list of trees to visit and start unionizing like it's the UPS
        for (Node<Integer> node : treesToVisit) {
            while (true) {
                // make sure the tree list can hold an element of this node's degree
                while (node.getDegree() >= trees.size()) {
                    trees.add(null);
                }

                // if nothing of this node's degree, set it to this node and leave
                if (trees.get(node.getDegree()) == null) {
                    trees.set(node.getDegree(), node);
                    break;
                }

                // but if there is something of the same degree, start merging it instead
                // also clear the slot out since it's getting modified
                Node<Integer> t = trees.get(node.getDegree());
                trees.set(node.getDegree(), null);

                // find out if the current node or temporary node is smaller
                Node<Integer> min = (t.getVal() < node.getVal()) ? t : node;
                Node<Integer> max = (t.getVal() < node.getVal()) ? t : node;

                // remove the bigger root from the circular list
                max.getNext().setPrev(max.getPrev());
                max.getPrev().setNext(max.getNext());

                // turn max into merging form, merge it into min
                max.setNext(max);
                max.setPrev(max);
                min.setChild(mergeLists(min.getChild(), max));

                // reassign max parent since it just got merged
                max.setParent(min);
                max.setMarked(false);

                // increment degree since max was just added
                min.addDegree();

                // continue merging from the new min
                node = min;
            }
            // the new min becomes whatever node is left here if need be
            if (node.getVal() <= min.getVal()) min = node;
        }
        return minVal;
    }

    /**
     * Decreases a node's value to whatever is specified. Can't set it greater than the old value.
     * This function just checks for validity, then throws the args to a helper.
     * @param node: the node to change
     * @param val: the new priority
     */
    public void decrease_key(Node<Integer> node, int val) {
        if (val > node.getVal()) {
            throw new IllegalArgumentException("new value is bigger than old, don't do that");
        }
        decrease_key_helper(node, val);
    }

    /**
     * gets two heaps and shoves them together.
     * gets rid of the previous heaps while its at it
     * @param a: first heap
     * @param b: second heap
     * @return the new heap, a combination of a and b
     */
    public FibonacciHeap<T> merge(FibonacciHeap<T> a, FibonacciHeap<T> b) {
        FibonacciHeap<T> c = new FibonacciHeap<>();
        c.min = mergeLists(a.min, b.min);
        c.size = a.size + b.size;

        a.size = b.size = 0;
        a.min = null;
        b.min = null;

        return c;
    }

    /**
     * this function gets two pointers to the heads of two lists.
     * using these pointers, it kinda shoves them together and returns
     * a single pointer to the new linked list
     * @param a: the head of the first list
     * @param b: the head of the second list
     * @return a pointer to the head (min value) of the new list
     */
    private Node<Integer> mergeLists(Node<Integer> a, Node<Integer> b) {
        // both lists are empty, nothing to merge. return null
        if (a == null && b == null) {
            return null;
        }
        // b is null, just return a
        else if (a != null && b == null) {
            return a;
        }
        // a is null, just return b
        else if (a == null) {
            return b;
        }
        // actually do some merging
        else {
            Node<Integer> temp = a.getNext();
            a.setNext(b.getNext());
            a.getNext().setPrev(a);
            b.setNext(temp);
            b.getNext().setPrev(b);

            // return the smaller of the two pointers
            return a.getVal() < b.getVal() ? a : b;
        }
    }

    /**
     * Sets the node's value to whatever is specified.
     * If it violates the heap properties, cuts it from the parent.
     * @param node: node being manipulated
     * @param val: new value of the node
     */
    private void decrease_key_helper(Node<Integer> node, int val) {
        node.setVal(val);

        // if the heap properties are violated, i.e. the node is lower priority than the parent,
        // cuts it from the parent
        if (node.getParent() != null && node.getVal() <= node.getParent().getVal()) {
            cut(node);
        }

        // reassigns min if the new priority happens to be such
        if (node.getVal() <= min.getVal()) {
            min = node;
        }
    }

    /**
     * Cuts a node from its parent. Checks to see if the parent is also marked.
     * If the parent is marked, cuts it then checks the parent's parent, and so on and so forth.
     * @param node: the node being cut
     */
    private void cut(Node<Integer> node) {
        node.setMarked(false);

        // no parent to cut
        if (node.getParent() == null) return;

        // if it has siblings, slide out of the way
        if (node.getNext() != node) {
            node.getNext().setPrev(node.getPrev());
            node.getPrev().setNext(node.getNext());
        }

        // if the node is its parent's child (which it usually is), make it not be
        if (node.getParent().getChild() == node) {
            // set the new child to whatever sibling if it exists
            if (node.getNext() != node) {
                node.getParent().setChild(node.getNext());
            }
            // otherwise, the child must not be an obstacle
            else {
                node.getParent().setChild(null);
            }
        }

        // the child has been eliminated so the parent's degree goes down
        node.getParent().subDegree();

        // turn the node into merging form and then do it into the root
        node.setPrev(node);
        node.setNext(node);
        min = mergeLists(min, node);

        // check for parent markings, then set as marked
        if (node.getParent().isMarked()) {
            cut(node.getParent());
        }
        else {
            node.getParent().setMarked(true);
        }

        // the node's parent is now a root if everything went well, so get rid of it from the child
        node.setParent(null);
    }
}
