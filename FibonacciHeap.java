import java.util.NoSuchElementException;

/**
 * File: FibonacciHeap.java
 * Description: a heap of the Fibonacci variety
 */

public class FibonacciHeap<T> {

    // head of the heap, minimum value
    private Node<Integer> min = null;
    private int size = 0;

    public FibonacciHeap() {
    }

    public Node<Integer> insert(int val) {
        Node<Integer> n = new Node<>(val);
        // add the new node as either the head or another entry
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

    /**
     * this algorithm looks fucked up from what i see online
     * @return
     */
    public int delete_min() {
        return 0;
    }

    /**
     * this one too
     */
    public void decrease_key() {

    }

//    /**
//     * gets two heaps and shoves them together.
//     * gets rid of the previous heaps while its at it
//     * @param a: first heap
//     * @param b: second heap
//     * @return the new heap, a combination of a and b
//     */
//    public FibonacciHeap<T> merge(FibonacciHeap<T> a, FibonacciHeap<T> b) {
//        FibonacciHeap<T> c = new FibonacciHeap<>();
//        c.min = mergeLists(a.min, b.min);
//        c.size = a.size + b.size;
//
//        a.size = b.size = 0;
//        a.min = null;
//        b.min = null;
//
//        return c;
//    }

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

}
