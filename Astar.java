// A* algorithm

import java.io.*; 
import java.util.*; 

public class Astar{
	static int row;
	static int col;
	static int[][] arr;
	static Integer start;
	static Integer goal;
	static AstarStack stack = new AstarStack();
	static ArrayList<Integer> explored = new ArrayList<Integer>();

	// constructor
	public Astar(){
	}

	// constructor
	public Astar(int row, int col, int[][] arr){
        this.row = row;
        this.col = col;
        this.arr = arr;
	}

	// A* search for a path
	public static ArrayList<Integer> search(AstarStack stack, Integer start, Integer goal){
		ArrayList<Integer> thepath = new ArrayList<Integer>();
		Node firstnode = stack.create_node(-1);
		stack.stack_push(start, (Integer) 0);
		stack.add_checked(start);
		stack.add_checked(start); 
		int solutionExists = 0; 
		int nodesexpanded = 0;
		int first = 0;
		Integer topelementdata = 0;


		while(!stack.isEmpty()){
			if(first==0){
				stack.stack_sort();
				topelementdata = stack.stack_peek();
				stack.set_parent(topelementdata, 0);
				first+=1;
			}
			Integer topelementdatacopy = topelementdata;

			stack.stack_pop();
			nodesexpanded += 1;
			stack.add_checked(stack.get_id(topelementdata));  
			stack.stack_clear(stack.get_id(topelementdata));

			stack.stack_push(stack.get_id(topelementdata), topelementdata);
			explored.add(stack.get_id(topelementdata));  

			if(!stack.isEmpty()){
				stack.stack_sort();
				topelementdata = stack.stack_peek();
				// System.out.println("topelement: "+ topelement);
				// System.out.println("goal: "+ goal);
				// if(goal.equals(topelement)){
				// 	System.out.println("equal");

				// }
				// else{
				// 	System.out.println("not equal");
				
				// }
				Integer adata = stack.get_id(topelementdata);

				if(adata.equals(goal)){
					// System.out.println("yeah");
					ArrayList<Integer> parentlist = stack.get_parentlist(topelementdata);
					int parentlistsize = parentlist.size();
					for(int i=0; i<parentlistsize; i++){
						thepath.add(parentlist.get(parentlistsize-1-i));
					}
					thepath.remove(0);
					thepath.remove(thepath.size()-1);

					solutionExists = 1;
					System.out.println("Nodes Expanded:" + nodesexpanded);
					return thepath;
				}

			}

		}


		if (solutionExists==1){
			return thepath;
		}
		else{
			ArrayList<Integer> newpath = new ArrayList<Integer>();
			// ArrayList<Integer> checked = stack.get_checked();
			// System.out.println(checked);
			newpath.add(-1);

			return newpath;
		}
	}

	// initiates the serach method
	public static ArrayList<Integer> calculate(){
	    for(int i=0; i<row; i++){
	    	for(int j=0; j<col; j++){
	    		if(arr[i][j]==83){
	    			start = i*col + j;
	    		}
	    		if(arr[i][j]==71){
	    			goal = i*col + j;
	    		}
	    	}
	    }
	   	stack = new AstarStack(row, col, arr);

	    ArrayList<Integer> apath = search(stack, start, goal);
	    return apath;

	}

	public static ArrayList<Integer> return_explored(){
		return explored;
	}




}