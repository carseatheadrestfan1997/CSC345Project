import java.math.*;
/**
 * ArrList implements an ArrayList
 * @author adost
 *
 */
public class ArrList {
	private int size = 0;
	private Array array;//the underlying array
	private int cap = 10;//the default capacity
	private int end = cap;//the index of the end of the array list
	private int start = cap;//the index of the start of the array list

	//default constructor creates an array list of size ten
	public ArrList(){
		this.array = new Array(cap);}
	
	//constructor creates an array list of size cap
	public ArrList(int cap) {
		this.array = new Array(cap);
		this.cap = cap;
		this.end = cap;
		this.start = cap;}
	
	//adds a value to the front of the list
	public void addFirst(Node val) {
		if (size == cap) {
			this.resize((float) 2);}
		this.start = (start == 0)? cap-1 : start-1;
		this.array.setVal(start%cap, val);
		this.size += 1;}
	
	public String toString() {
		return this.array.toString();
	}
	
	public Node get(int i) {
		return this.array.getVal(Math.abs(start+i)%cap);}

	public Node getFirst() throws EmptyListException{
		return this.array.getval(start);}

	public Node getLast() throws EmptyListException{
		return this.array.getval(end);}
	
	//resizes the array by a given factor
	private void resize(Float factor) {
		if (!(cap == 10 && factor < 1)) {//does not resize an array smaller than 10
			int newCap = (int)((float) cap * factor);
			System.out.println(start);
			this.array.resize(newCap, start%cap, size);
			this.cap = newCap;
			this.start = 0;
			this.end = size;}}
	
	//determines how full the list is
	public float getFactor() {
		return (float) size/ (float) cap;
	}
	
	//adds an element to the end of the array
	public void addLast (Node val) {
		if (size == cap) {
			this.resize((float) 2);}
		this.array.setVal(end%cap, val);
		this.end += 1;
		this.size +=1;}
	
	//returns the first index of a the input number or -1 if it is not present
	public int indexOf(Node val) {
		int i = start;
		for (int j = 0; j < size; j++) {
			if (array.getVal(i%cap) == val){
				return Math.abs(start-i)%cap;
			}
			i = i+1;
			}
		return -1;
	}
	
	//returns true if passed integer is present, otherwise false
	public boolean contains(Node val) {
		int i = start;
		for (int j = 0; j < size; j++) {
			if (array.getVal(i%cap) == val){
				return true;
			}
			i = i+1;
		}
		return false;
	}
	//returns true if the array is empty, otherwise false
	public boolean isEmpty() {
		return size == 0;
	}
	
	//returns the first index of a the input number or -1 if it is not present
	public int lastIndexOf(Node val) {
		int i = end;
		for (int j = 0; j < size; j++) {
			if (array.getVal(i%cap) == val){
				return Math.abs(start-i);
				}
			i = (i == 0)? cap-1:i-1;
		}
		return -1;
	}
	
	//Removes and returns an element from the beginning of the array
	public Node removeFirst() throws EmptyListException{
		if (size == 0) {throw new EmptyListException();}
		else {
			int i = start%cap;
			Node retval = array.getVal(i);
			this.array.setVal(i, null);
			this.start += 1;
			this.size -= 1;
			float factor = getFactor();
			if (factor <= 0.25) {this.resize((float) 0.5);}
			return retval;}}
	
	//Removes and returns an element from the end of the array
	public Node removeLast() throws EmptyListException{
		if (size == 0) {throw new EmptyListException();}
		else if (size == 1) {
			Node retval = this.array.getVal(start);
			this.array.setVal(start, null);
			this.end = 0;
			return retval;
		}
		else {
			Node retval = array.getVal((end-1)%cap);
			this.array.setVal((end-1)%cap, null);
			this.size -= 1;
			this.end = (end == 0)? cap-1: end-1;//if end is 0, resets it to the last index of the underlying array
			float factor = getFactor();
			if (factor <= 0.25) {this.resize((float) 0.5);}
			return retval;}}

	//Removes the first instance of the passed value from the list
	//Returns true if it is found; otherwise, returns false
	public boolean removeByValue(Node val) throws EmptyListException{
		if (size == 0) {throw new EmptyListException();}
		else {
			int i = start%cap;
			while (i <= end% cap) {
				if (array.getVal(i) == val){
					this.array.setVal(i, null);
					if (i == end){this.end = (end==0)? cap-1:end-1;}
					else if (i == start) {this.start += 1;}
					else {this.fill_gap(i);}
					this.size -= 1;
					float factor = getFactor();
					if (factor <= 0.25) {this.resize((float) 0.5);}
					return true;}
				i = (i+1)%cap;
					}
				}
			return false;}	
	
	//Removes and returns the value at the passed index
	public Node removeByIndex(int i) throws EmptyListException{
		if (size == 0) {throw new EmptyListException();}
		else {
			i = (start+i)%cap;
			Node retval = array.getVal(i);
			this.array.setVal(i, null);
			if (i == end){this.end = (end==0)? cap-1:end+1;}
			else if (i == start) {start += 1;}
			else {this.fill_gap(i);}
			this.size -= 1;
			float factor = getFactor();
			if (factor <= 0.25) {this.resize((float) 0.5);}
			return retval;}}
	
	//Sets the value at the passed index to the passed value and 
	//and returns it
	public Node set(int i, Node val) {
		i = (start+i)%cap;
		Node retval = this.array.getVal(i);
		this.array.setVal(i, val);
		return retval;}
	
	//fills in gaps where elements have been removed from the array
	public void fill_gap(int i) {
		while (i != end%cap) {
			int next = (i+1)%cap;
			this.array.swap(i, next);
			i= (i+1)%cap;}}
	
	//Returns the number of elements in the array
	public int size() {
		return this.size;
	}

	
}
