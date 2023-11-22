import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * READ READ READ READ
 * 
 * UNCOMMENT @Test in order to run anything!
 *
 */

class RunTimeTest {
	
	FibonacciHeap<Integer> h;
	
	@BeforeEach
    void setup() {
        h = new FibonacciHeap<>();
    }
	
	/**
	 * these testcases demonstrate the worst case runtimes for the delete min operation
	 */
	
	// @Test
	void deleteMinWorstEx1() {
		
		for (int i = 0; i < 100; i++) {
			h.insert(i + 1, i + 1);
		}
		
		System.out.println("Worst case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + " merge operations on initial size 100");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	// @Test
	void deleteMinWorstEx2() {
		
		for (int i = 0; i < 100; i++) {
			h.insert((i + 1) * 2, (i + 1) * 2) ;
		}
		
		System.out.println("Worst case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + " merge operations on initial size 100");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	// @Test
	void deleteMinWorstEx3() {
		
		for (int i = 0; i < 1000; i++) {
			h.insert(i + 1, i + 1);
		}
		
		System.out.println("Worst case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	// @Test
	void deleteMinWorstEx4() {
		
		for (int i = 0; i < 10000; i++) {
			h.insert((i + 1) * 2, (i + 1) * 2) ;
		}
		
		System.out.println("Worst case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + h.mergeCounter + " merge operations on initial size 10000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	// @Test
	void deleteMinBestEx1() {
		
		for (int i = 0; i < 1000; i++) {
			h.insert((i + 1), (i + 1));
		}
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("best case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	
	// @Test
	void deleteMinBestEx4() {
		
		for (int i = 0; i < 10001; i++) {
			h.insert((i), (i));
		}
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("Best case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 10000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	// @Test
	void deleteMinBestEx5() {
		
		for (int i = 0; i < 10001; i++) {
			h.insert((i + 1), (i + 1));
		}
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("Best case example:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 10000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	

	// @Test
	void deleteMinAverageEx1() {
		
		// Define the range of random numbers
	    int min = 10;
	    int max = 10000;

	    // Create a Random object to generate random numbers
	    Random random = new Random();

	    // Generate 1000 random integers within the specified range
	    for (int i = 0; i < 1000; i++) {
	        int randomNumber = random.nextInt(max - min + 1) + min;
	        h.insert(randomNumber, randomNumber);
	    }
		
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("random number example, second delete min operation:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	// @Test
	void deleteMinAverageEx1_1() {
		
		// Define the range of random numbers
	    int min = 10;
	    int max = 10000;

	    // Create a Random object to generate random numbers
	    Random random = new Random();

	    // Generate 1000 random integers within the specified range
	    for (int i = 0; i < 1000; i++) {
	        int randomNumber = random.nextInt(max - min + 1) + min;
	        h.insert(randomNumber, randomNumber);
	    }
		
		
		// h.delete_min();
		int cur = h.mergeCounter;
		
		System.out.println("random number insertion, first delete min:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	// @Test
	void deleteMinAverageEx2_1() {
		
		// Define the range of random numbers
	    int min = 10;
	    int max = 10000;

	    // Create a Random object to generate random numbers
	    Random random = new Random();

	    // Generate 1000 random integers within the specified range
	    for (int i = 0; i < 1000; i++) {
	        int randomNumber = random.nextInt(max - min + 1) + min;
	        h.insert(randomNumber, randomNumber);
	    }
		
		
		h.delete_min();
		int cur = h.mergeCounter;
		
		// Generate 100 more random integers within the specified range
	    for (int i = 0; i < 100; i++) {
	        int randomNumber = random.nextInt(max - min + 1) + min;
	        h.insert(randomNumber, randomNumber);
	    }
		
		
		System.out.println("random number insertion, first delete min:");
		System.out.println("removed minimum priority object: " + h.delete_min().getPriority());
		System.out.println("performed " + (h.mergeCounter - cur) + " merge operations on initial size 1099");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	@Test
	void decreaseKeyWorst() {
		
		for (int i = 0; i < 1000; i++) {
			h.insert((i + 1), (i + 1));
		}
		
		h.decrease_key(h.lastNode, 0);
		
		System.out.println("decrease key worst case example 1:");
		System.out.println("decreased to top: " + h.lastNode.getPriority());
		System.out.println("performed " + (h.mergeCounter + h.cutCounter) + " merge and cut operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
		
	}
	
	@Test
	void decreaseKeyWorst2() {
		
		for (int i = 0; i < 10000; i++) {
			h.insert((i + 1), (i + 1));
		}
		
		h.decrease_key(h.lastNode, 0);
		
		System.out.println("decrease key worst case example 2:");
		System.out.println("decreased to top: " + h.lastNode.getPriority());
		System.out.println("performed " + (h.mergeCounter + h.cutCounter) + " merge and cut operations on initial size 10000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
		
	}
	
	@Test
	void decreaseKeyWorst3() {
		
		for (int i = 0; i < 10000; i++) {
			h.insert((i + 1) * 2, (i + 1) * 2);
		}
		
		h.decrease_key(h.lastNode, 0);
		
		System.out.println("decrease key worst case example 3:");
		System.out.println("decreased to top: " + h.lastNode.getPriority());
		System.out.println("performed " + (h.mergeCounter + h.cutCounter) + " merge and cut operations on initial size 10000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
		
	}
	
	@Test
	void decreaseKeyAve1() {
		
		// Create a Random object to generate random numbers
	    Random random = new Random();
	    
	    int max = 10000;
	    int min = 10;
	    
	    
	    
	    // Generate 1000 random integers within the specified range
	    for (int i = 0; i < 1000; i++) {
	        int randomNumber = random.nextInt(max - min + 1) + min;
	        h.insert(randomNumber, randomNumber);
	    }
		
	    h.delete_min();
	    int dev = (h.mergeCounter + h.cutCounter);
	    
		h.decrease_key(h.lastNode, random.nextInt(max - min + 1) + min);
		
		System.out.println("decrease key Best case example 1:");
		System.out.println("decreased to top: " + h.lastNode.getPriority());
		System.out.println("performed " + (h.mergeCounter + h.cutCounter - dev) + " merge and cut operations on initial size 1000");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
	}
	
	
	
	
	
}
