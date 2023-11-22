import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * File: FibonacciHeap.java
 * Description: a heap of the Fibonacci variety
 * At its core, it's a bunch of min heap trees with their roots stored in a circular doubly linked list.
 * Insertion works by just merging the value into the minimum root.
 * Merging is a lazy method in that it puts off the more difficult operations til later, as it just concatenates
 * both lists.
 * Deletion of the min involves removing the min, merging the min's children into the root list, and fixing the
 * root list such that there is only one node of each degree (degrees being Fibonacci numbers, hence the name).
 * Decreasing the priority of a node involves updating the node's priority.
 * If this new priority results in invalidating heap properties, then it gets cut and set as a new root.
 * Cutting involves:
 *  1. the node is cut from its parent and is set as a new root.
 *  2. if the parent wasn't a root, it gets marked.
 *  3. if the parent was already marked, it gets recursively cut upwards.
 *  4. continue until hitting the root or an unmarked node.
 *  5. update the minimum node if necessary
 */

public class FibonacciHeap<V> {

    /**
     * Internal node class used to store data.
     * Stores a generic key value and a double priority value.
     * Also stores a bunch of pointers so that a circular double linked list can work.
     * @param <V>: whatever
     */
    public static final class Node<V> {
        private int degree = 0;
        private boolean marked = false;
        private Node<V> next;
        private Node<V> prev;
        private Node<V> parent;
        private Node<V> child;
        private V key;
        private double priority;

        private Node(V key, double priority) {
            next = prev = this;
            this.key = key;
            this.priority = priority;
        }

        public V getKey() {
            return this.key;
        }

        public double getPriority() {
            return this.priority;
        }

        public void setKey(V key) {
            this.key = key;
        }
    }

    private Node<V> min = null;
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
    public Node<V> insert(V val, double priority) {
        Node<V> n = new Node<>(val, priority);
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
    public Node<V> find_min() {
        if (isEmpty()) throw new NoSuchElementException("empty heap");
        return min;
    }

    /**
     * Pops the minimum node off the top of the heap if it exists.
     * If it doesn't, throws a NoSuchElementException.
     * Does way too much work to fix the heap afterward.
     * @return the lowest priority node in the heap.
     */
    public Node<V> delete_min() {
        if (isEmpty()) throw new NoSuchElementException("Empty heap");

        size--;
        Node<V> minVal = min;

        // first case: is this the only element? if so, just get rid of it
        // otherwise, slide it out of the way of its neighbor roots and reassign the min to whatever
        if (min.next == min) {
            min = null;
        }
        else {
            min.prev.next = min.next;
            min.next.prev = min.prev;
            min = min.next;
        }

        // get parents out of the way since the children are going to become roots
        if (min != null && min.child != null) {
            Node<V> cur = min.child;
            do {
                cur.parent = null;
                cur = cur.next;
            } while (cur != min.child);
        }

        // merge root children into list
        min = mergeLists(min, minVal.child);

        // no elements left, return
        if (min == null) return minVal;

        // starts getting worse here
        // need to reduce all roots such that there's only one of each degree (since its a fibonacci heap)
        // uses an arraylist to keep track of things as it's easy (relative)
        List<Node<V>> trees = new ArrayList<>();

        // gotta look through whole list without messing up any traversal order
        // therefore, make sure no nodes are visited twice with another arraylist
        List<Node<V>> treesToVisit = new ArrayList<>();

        // runs through the circular list til it hits the start again or til the visiting list is empty
        Node<V> start = min;
        do {
            treesToVisit.add(start);
            start = start.next;
        } while (start != min);

        // go through the new list of trees to visit and start unioning nodes
        for (Node<V> node : treesToVisit) {
            // merge until a match is found
            while (true) {
                // make sure the tree list can hold an element of this node's degree
                while (node.degree >= trees.size()) {
                    trees.add(null);
                }

                // if nothing of this node's degree, set it to this node and leave
                if (trees.get(node.degree) == null) {
                    trees.set(node.degree, node);
                    break;
                }

                // but if there is something of the same degree, start merging it instead
                // also clear the slot out since it's getting modified
                Node<V> t = trees.get(node.degree);
                trees.set(node.degree, null);

                // find out if the current node or temporary node is smaller
                Node<V> min = (t.priority < node.priority) ? t : node;
                Node<V> max = (t.priority < node.priority) ? node : t;

                // remove the bigger root from the circular list
                max.next.prev = max.prev;
                max.prev.next = max.next;

                // turn max into merging form, merge it into min
                max.next = max.prev = max;
                min.child = mergeLists(min.child, max);

                // reassign max parent since it just got merged
                max.parent = min;
                max.marked = false;

                // increment degree since max was just added
                min.degree++;

                // continue merging from the new min
                node = min;
            }
            // the new min becomes whatever node is left here if need be
            if (node.priority <= min.priority) min = node;
        }
        return minVal;
    }

    /**
     * Decreases a node's value to whatever is specified. Can't set it greater than the old value.
     * This function just checks for validity, then throws the args to a helper.
     * @param node: the node to change
     * @param priority: the new priority
     */
    public void decrease_key(Node<V> node, int priority) {
        if (priority > node.priority) {
            throw new IllegalArgumentException("new value is bigger than old, don't do that");
        }
        decrease_key_helper(node, priority);
    }

    /**
     * gets two heaps and shoves them together.
     * gets rid of the previous heaps while it's at it
     * @param a: first heap
     * @param b: second heap
     * @return the new heap, a combination of a and b
     */
    public FibonacciHeap<V> merge(FibonacciHeap<V> a, FibonacciHeap<V> b) {
        FibonacciHeap<V> c = new FibonacciHeap<>();
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
    private Node<V> mergeLists(Node<V> a, Node<V> b) {
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
            Node<V> temp = a.next;
            a.next = b.next;
            a.next.prev = a;
            b.next = temp;
            b.next.prev = b;

            // return the smaller of the two pointers
            return a.priority < b.priority ? a : b;
        }
    }

    /**
     * Sets the node's value to whatever is specified.
     * If it violates the heap properties, cuts it from the parent.
     * @param node: node being manipulated
     * @param priority: new value of the node
     */
    private void decrease_key_helper(Node<V> node, double priority) {
        node.priority = priority;

        // if the heap properties are violated, i.e. the node is lower priority than the parent,
        // cuts it from the parent
        if (node.parent != null && node.priority <= node.parent.priority) {
            cut(node);
        }

        // reassigns min if the new priority happens to be such
        if (node.priority <= min.priority) {
            min = node;
        }
    }

    /**
     * Cuts a node from its parent. Checks to see if the parent is also marked.
     * If the parent is marked, cuts it then checks the parent's parent, and so on and so forth.
     * @param node: the node being cut
     */
    private void cut(Node<V> node) {
        node.marked = false;

        // no parent to cut
        if (node.parent == null) return;

        // if it has siblings, slide out of the way
        if (node.next != node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        // if the node is its parent's child (which it usually is), make it not be
        if (node.parent.child == node) {
            // set the new child to whatever sibling if it exists
            if (node.next != node) {
                node.parent.child = node.next;
            }
            // otherwise, the child must not be an obstacle
            else {
                node.parent.child = null;
            }
        }

        // the child has been eliminated so the parent's degree goes down
        node.parent.degree--;

        // turn the node into merging form and then do it into the root
        node.prev = node.next = node;
        min = mergeLists(min, node);

        // check for parent markings, then set as marked
        if (node.parent.marked) {
            cut(node.parent);
        }
        else {
            node.parent.marked = true;
        }

        // the node's parent is now a root if everything went well, so get rid of it from the child
        node.parent = null;
    }
}
