import java.io.*; 
import java.util.*; 

public class Bfs{
	static int row;
	static int col;
	static int[][] arr;
	static Integer start;
	static Integer goal;
	static ArrayList<Integer> explored = new ArrayList<Integer>();

	// constructor
	public Bfs(){
	}

	// constructor
	public Bfs(int row, int col, int[][] arr){
        this.row = row;
        this.col = col;
        this.arr = arr;
	}

	public static ArrayList<Integer> search(NodeQueue queue, Integer start, Integer goal){
		ArrayList<Integer> thepath = new ArrayList<Integer>();
		// thepath.add(start);
		queue.enqueue(start);
		queue.add_checked(start); 
		int solutionExists = 0; 
		int nodesexpanded = 0;


		while(!queue.isEmpty()){
			Integer topelement = queue.queue_peek();

			queue.dequeue();
			nodesexpanded += 1;
			// queue.add_checked(topelement);  
			explored.add(topelement);  


			queue.enqueue(topelement);
			if(!queue.isEmpty()){
				topelement = queue.queue_peek();

				if(topelement.equals(goal)){
					int[] parentlist = queue.get_parentlist();
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
	    NodeQueue queue = new NodeQueue(row, col, arr);
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
	    ArrayList<Integer> apath = search(queue, start, goal);
	    return apath;

	}

	public static ArrayList<Integer> return_explored(){
		return explored;
	}
}

