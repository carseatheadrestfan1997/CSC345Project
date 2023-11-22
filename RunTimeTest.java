import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

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
	void deleteMinWorstEx1() {
		
		for (int i = 0; i < 100; i++) {
			h.insert(i + 1, i + 1);
		}
		
		System.out.println("Worst case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + " merge operations on initial size 100");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void deleteMinWorstEx2() {
		
		for (int i = 0; i < 100; i++) {
			h.insert((i + 1) * 2, (i + 1) * 2) ;
		}
		
		System.out.println("Worst case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + " merge operations on initial size 100");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void deleteMinWorstEx3() {
		
		for (int i = 0; i < 1000; i++) {
			h.insert(i + 1, i + 1);
		}
		
		System.out.println("Worst case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void deleteMinWorstEx4() {
		
		for (int i = 0; i < 10000; i++) {
			h.insert((i + 1) * 2, (i + 1) * 2) ;
		}
		
		System.out.println("Worst case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + " merge operations on initial size 10000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void deleteMinBestEx1() {
		
		for (int i = 0; i < 1001; i++) {
			h.insert((i + 1), (i + 1));
		}
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("best case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	
	@Test
	void deleteMinBestEx4() {
		
		for (int i = 0; i < 10001; i++) {
			h.insert((i + 1), (i + 1));
		}
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("Best case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void deleteMinBestEx5() {
		
		for (int i = 0; i < 10001; i++) {
			h.insert((i + 1), (i + 1));
		}
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("Best case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	

	@Test
	void deleteMinAverageEx6() {
		
		// Define the range of random numbers
	    int min = 10;
	    int max = 10000;

	    // Create a Random object to generate random numbers
	    Random random = new Random();

	    // Generate 1000 random integers within the specified range
	    for (int i = 0; i < 1001; i++) {
	        int randomNumber = random.nextInt(max - min + 1) + min;
	        h.insert(randomNumber, randomNumber);
	    }
		
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("Average case example 6:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void deleteMinAverageEx6_1() {
		
		// Define the range of random numbers
	    int min = 10;
	    int max = 10000;

	    // Create a Random object to generate random numbers
	    Random random = new Random();

	    // Generate 1000 random integers within the specified range
	    for (int i = 0; i < 1001; i++) {
	        int randomNumber = random.nextInt(max - min + 1) + min;
	        h.insert(randomNumber, randomNumber);
	    }
		
		
		// h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("Average case example 6, first delete min:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	
	
}
