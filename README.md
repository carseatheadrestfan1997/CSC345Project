Fibonacci Heap

A project for CSC345 which implements a Fibonacci heap in Java (an overview can be found at (https://en.wikipedia.org/wiki/Fibonacci_heap).
This heap stores key-value pairs of some arbitrary key and a double priority.
This class has 5 public functions:
- insert: inserts a new node by merging it into the main heap at the minimum priority element.
- find_min: peeks the minimum value.
- delete_min: pops the minimum value off the heap if possible and then rearranges the heap accordingly. This (and decreasing a key's priority) is where most of the complexity is, as merging (and thus insertion) is lazy in implementation (i.e. it puts off the difficult operations til later).
- decrease_key: decreases the priority of a key and, if minheap properties are violated by that, rearranges the heap by cutting nodes.
- merge: takes two separate Fibonacci heaps and merges them together.
