//  a class Graph that builds the graph for a given grid

import java.util.*;

public class Graph4 extends NodeStack{
	static LinkedList<Node> setListArray = new LinkedList<Node>();
	static Node head;
	
	// constructor
	public Graph4(){
	}

	// create the graph
	private static void setGraph(int row, int col, int[][]arr){
		// System.out.println(arr);
		int[][] newarr = new int[row+2][col+2];
		
		for(int j = 0; j < col+2; j++){
			newarr[0][j] = 0;
			newarr[row+1][j] = 0;
		}

		for(int i = 0; i < row+2; i++){
			newarr[i][0] = 0;
			newarr[i][col+1] = 0;
		}

		for(int i = 1; i < row+1; i++){
			for(int j = 1; j < col+1; j++){
				newarr[i][j] = arr[i-1][j-1];
			}
		}

		for (int i=1; i<row+1; i++){
			for(int j=1; j<col+1; j++){
				int index = (i-1)*col + (j-1);
				Node curnode = new Node(newarr[i][j], index);
				if (newarr[i][j] == 48|newarr[i][j] == 83|newarr[i][j] == 71){

					if(newarr[i-1][j] == 48|newarr[i-1][j] == 83|newarr[i-1][j] == 71){
						curnode.setNext(index-col);
					}

					if(newarr[i][j-1] == 48|newarr[i][j-1] == 83|newarr[i][j-1] == 71){
						curnode.setNext(index-1);
					}		
		
					if(newarr[i+1][j] == 48|newarr[i+1][j] == 83|newarr[i+1][j] == 71){
						curnode.setNext(index+col);			
					}				

					if(newarr[i][j+1] == 48|newarr[i][j+1] == 83|newarr[i][j+1] == 71){
						curnode.setNext(index+1);
					}



					if(newarr[i+1][j+1] == 48|newarr[i+1][j+1] == 83|newarr[i+1][j+1] == 71){
						curnode.setNext(index+1+col);
					}

					if(newarr[i-1][j+1] == 48|newarr[i-1][j+1] == 83|newarr[i-1][j+1] == 71){
						curnode.setNext(index+1-col);
					}

					if(newarr[i+1][j-1] == 48|newarr[i+1][j-1] == 83|newarr[i+1][j-1] == 71){
						curnode.setNext(index-1+col);
					}

					if(newarr[i-1][j-1] == 48|newarr[i-1][j-1] == 83|newarr[i-1][j-1] == 71){
						curnode.setNext(index-1-col);
					}
				}
				setListArray.add(curnode);
			}
		}
	}

	// return the created graph
	public static LinkedList<Node> getGraph(int row, int col, int[][] array){
		Graph graph = new Graph();
		int[][] arr = array;
		setGraph(row,col,arr);

		return setListArray;
	}


}
