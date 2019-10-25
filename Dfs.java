import java.io.*; 
import java.util.*; 

public class Dfs{
	static int row;
	static int col;
	static int[][] arr;
	static Integer start;
	static Integer goal;
	static ArrayList<Integer> explored = new ArrayList<Integer>();

	// constructor
	public Dfs(){
	}

	// constructor
	public Dfs(int row, int col, int[][] arr){
        this.row = row;
        this.col = col;
        this.arr = arr;
	}

	// public static boolean isDeadEnd(NodeStack stack, Integer index){
	// 	ArrayList<Integer> adj = stack.stack_getadj(index);
	// 	ArrayList<Integer> checked = stack.get_checked();
	// 	int size = adj.size();
	// 	int number = 0;
	// 	for(int i=0; i<size; i++){
	// 		int num = adj.get(i);
	// 		if (stack.notIn(num, checked)){
	// 			number += 1;
	// 		}
	// 	}
	// 	if(number!=0){
	// 		return false;
	// 	}
	// 	else{
	// 		return true;
	// 	}
	// }

	public static ArrayList<Integer> search(NodeStack stack, Integer start, Integer goal){
		ArrayList<Integer> thepath = new ArrayList<Integer>();
		// thepath.add(start);
		stack.stack_push(start);
		stack.add_checked(start); 
		int solutionExists = 0; 
		int nodesexpanded = 0;


		while(!stack.isEmpty()){
			Integer topelement = stack.stack_peek();
			// System.out.println("hello1");

			stack.stack_pop();
			nodesexpanded += 1;
			// stack.add_checked(topelement);  
			explored.add(topelement);  


			stack.stack_push(topelement);
			if(!stack.isEmpty()){
				topelement = stack.stack_peek();

				if(topelement.equals(goal)){
					int[] parentlist = stack.get_parentlist();
					int curelement = topelement;
					while(curelement!=start){
						thepath.add(curelement);
						curelement = parentlist[curelement];
					}
					thepath.remove(0);
					solutionExists = 1;
					System.out.println("Nodes Expanded:" + nodesexpanded);
					return thepath;
			}

				// thepath.add(goal);
				
			// thepath.add(topelement);
			// if(topelement==21){
			// 	System.out.println("yeah");
			// }
			// if(topelement==22){
			// 	ArrayList<Integer> testprint = stack.stack_getadj(topelement);
			// 	ArrayList<Integer> testprintchecked = stack.get_checked();				
				// System.out.println(testprint);
				// System.out.println(testprintchecked);

			// }
			// System.out.println("hello2");

			// System.out.println(adj+"jilkjl");
			// if(isDeadEnd(stack, topelement)){


			// 	// System.out.println(topelement);
			// 	// stack.add_checked(topelement);  

			// 	// System.out.println(topelement);
			// 	// stack.add_checked(topelement);
			// 	int length = thepath.size();
			// 	thepath.remove(length-1);
			// }
			
			// ArrayList<Integer> testprint = stack.stack_getadj(topelement);
			// System.out.print(testprint);

			}

		}

		if (solutionExists==1){
			return thepath;
		}
		else{
			ArrayList<Integer> newpath = new ArrayList<Integer>();
			newpath.add(-1);
			return newpath;
		}
	}


	public static ArrayList<Integer> calculate(){
	    NodeStack stack = new NodeStack(row, col, arr);
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
	    ArrayList<Integer> apath = search(stack, start, goal);
	    return apath;

	}


	public static ArrayList<Integer> return_explored(){
		return explored;
	}






}