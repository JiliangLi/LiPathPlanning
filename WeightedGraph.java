// adds random weight to the edges

import java.util.*;
import java.lang.*;

public class WeightedGraph extends SortedStack{
	static int row;
    static int col;
    static int[][] arr;
    static int size;
    static LinkedList<Node> thegraph;
    static double[][] weightedgraph = new double[0][0];
    static Random rand = new Random(); 

	// constructor
    public WeightedGraph(){
    }

	// constructor
	public WeightedGraph(int row, int col, int[][] arr, LinkedList<Node> thegraph){
        this.row = row;
        this.col = col;
        this.arr = arr;
		this.thegraph = thegraph;
	    this.size = thegraph.size();
	    this.weightedgraph = new double[size][size];
	}

	// create and return the generated graph
	public static double[][] getweightedgraph(){
		for(int i=0; i<size; i++){
			Node item = thegraph.get(i);
			ArrayList<Integer> adj = item.getNext();

			int arraysize = adj.size();
			for(int j=0; j<arraysize; j++){
				int adjindex = adj.get(j);
				// int randint = rand.nextInt(10);
				// int randint = 1;
				if(adjindex==i+1|adjindex==i-1|adjindex==i+col|adjindex==i-col){
					weightedgraph[i][adjindex] = 1;
				}
				else{
					weightedgraph[i][adjindex] = 5.3;
										// weightedgraph[i][adjindex] = Math.sqrt(2);

				}
			}
		}
		return weightedgraph;
	}

}