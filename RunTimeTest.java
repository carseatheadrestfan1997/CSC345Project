import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RunTimeTest {
	
	FibonacciHeap<Integer> h;
	
	@BeforeEach
    void setup() {
        h = new FibonacciHeap<>();
    }
	
	/**
	 * these testcases demonstrate the worst case runtimes for the delete min operation
	 */
	
	@Test
	void delteMinWorstEx1() {
		
		for (int i = 0; i < 100; i++) {
			h.insert(i + 1, i + 1);
		}
		
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + "merge operations on initial size 100");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void delteMinWorstEx2() {
		
		for (int i = 0; i < 100; i++) {
			h.insert((i + 1) * 2, (i + 1) * 2) ;
		}
		
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + "merge operations on initial size 100");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void delteMinWorstEx3() {
		
		for (int i = 0; i < 1000; i++) {
			h.insert(i + 1, i + 1);
		}
		
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + "merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void delteMinWorstEx4() {
		
		for (int i = 0; i < 10000; i++) {
			h.insert((i + 1) * 2, (i + 1) * 2) ;
		}
		
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + "merge operations on initial size 10000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}

	
	
}
