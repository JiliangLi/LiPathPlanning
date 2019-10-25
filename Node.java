// Create a class Node that stores the information
// reference: https://www.youtube.com/watch?v=zSJRn8erf9M

import java.util.*;

public class Node extends Graph{
	int id;
	int data;
	int parent;
	ArrayList<Integer> adjNode = new ArrayList<Integer>();

	// constructor
	public Node(){
	}
	
	public Node(int id){
		this.id = id;
	}
	// constructor
	public Node(int data, int id){
		this.data = data;
		this.id = id;
	}

	public Node(int data, int id, int parent){
		this.data = data;
		this.id = id;
		this.parent = parent;
	}
	// return the info stored in the node
	public int getData(){
		return data;
	}

	public int getId(){
		return id;
	}

	public int getParent(){
		return parent;
	}

	// return the adj indexes
	public ArrayList<Integer> getNext(){
		return adjNode;
	} 
	
	public void setParent(int parent){
		parent = parent;
	}
	// set the info stored in the node
	public void setData(int value){
		data = value;
	}

	// set the adj indexes
	public void setNext(Integer n){
		adjNode.add(n);
	}

}