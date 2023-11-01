import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Array {
    private Node[] array;//the underlying array
    private int accessCount;//counts the number of times the array is accessed

    //constructor that creates an array of 0's of size <cap>
    public Array (int cap) {
	this.accessCount = 0;
	this.array = new Node[cap];
    }

    //gets a String representation of the array
    public String toString() {
		return Arrays.toString(array);
    }

    //gets the length of the array
    public int length() {
	return array.length;
    }

    //gets the value at index i
    public Node getVal(int i) {
	Node val = array[i];
	accessCount++;
	return val;
    }

    //sets the value at index i to val
    public void setVal(int i, Node val) {
	array[i] = val;
	accessCount++;
    }

    //swaps the values at i and j
    public void swap(int i, int j) {
	Node temp = array[i];
	array[i] = array [j];
	array[j] = temp;
	accessCount+=4;
    }

    //returns the accessCount
    public int getAccessCount() {
	return accessCount;
    }

    //resets accessCount to 0--used only for testing!!!
    public void resetAccessCount() {
	this.accessCount = 0;
    }

    //sorts the array--used for testing only!!!
    public void sort() {
	Arrays.sort(this.array);
    }

    //compares the elements in two arrays--used for testing only!!!
    public boolean equals(Array a) {
	if(this.length() != a.length())
	    return false;
	this.sort();
	a.sort();
	for(int i = 0; i < this.length(); i++) {
	    if(this.getVal(i) != a.getVal(i))
		return false;
	}
	return true;
    }

    //resizes the array
    public void resize(int newCap, int start, int n) {
	Node[] temp = new Node[newCap];
	int i = start;
	int j = 0;
	while (j < n) {
	    temp[j] = this.array[i];
	    j++;
	    i = (i+1)%array.length;
	    accessCount+=2;
	}
	this.array = temp;
    }


	}


