import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RunTimeTest {
	
	FibonacciHeap<Integer> h;
	
	@BeforeEach
    void setup() {
        h = new FibonacciHeap<>();
    }

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
	
	
}
