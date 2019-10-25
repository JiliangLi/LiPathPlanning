import java.io.*; 
import java.util.*; 
import java.lang.*;
  
public class HillClimbing{    
    static int row;
    static int col;
    static int goal;
    static int start;
    static int[][] arr;
    static int[] heuristiclist;
    static Graph graph = new Graph();
    static LinkedList<Node> thegraph;
    static ArrayList<Integer> thepath = new ArrayList<Integer>();
    static int[] parentlist = new int[0];
    static ArrayList<Integer> explored = new ArrayList<Integer>();

    // constructor
    public HillClimbing(){
    }

    // constructor
    public HillClimbing(int row, int col, int[][] arr){
        this.row = row;
        this.col = col;
        this.arr = arr;
	    for(int i=0; i<row; i++){
	    	for(int j=0; j<col; j++){
	    		if(arr[i][j]==83){
	    			this.start = i*col + j;
	    		}
	    		if(arr[i][j]==71){
	    			this.goal = i*col + j;
	    		}
	    	}
	    }
        this.thegraph = graph.getGraph(row, col, arr);
        int size = thegraph.size();
        this.parentlist = new int[size];
        this.heuristiclist = new int[size];
        for(int i=0; i<size; i++){
            this.parentlist[i] = -1;
        }
    }

    // keep track of the parent
    public static void track_path(int parent, int child){
        parentlist[child] = parent;
    }

    // return parentlist
    public static int[] get_parentlist(){
        return parentlist;
    }

    public static void set_heuristic(){
    	int size = thegraph.size();
    	for(int i=0; i<size; i++){
    		// System.out.println(goal-get_heuristic_value(i)); 		
    		heuristiclist[i] = goal - get_heuristic_value(i);
    	}
    }

    // manhattan distance heuristic value
    public static Integer get_heuristic_value(int index){
        Integer goalrow = goal/col;
        Integer goalcol = goal%col;
        Integer indexrow = index/col;
        Integer indexcol = index%col;
        int rowdifference = Math.abs(goalrow-indexrow);
        int coldifference = Math.abs(goalcol-indexcol);

        // manhattan distance
        // Integer heuristicvalue = rowdifference + coldifference;
        // return heuristicvalue; 

        // weighted straight-line distance, w=1
        // Double heuristicvaluedouble = (Math.sqrt(Math.pow(rowdifference, 2)+Math.pow(coldifference, 2)));
        // Integer heuristicvalue = heuristicvaluedouble.intValue();
        // return heuristicvalue; 

        // weighted straight-line distance, w=2
        Double heuristicvaluedouble = 2*(Math.sqrt(Math.pow(rowdifference, 2)+Math.pow(coldifference, 2)));
        Integer heuristicvalue = heuristicvaluedouble.intValue();
        return heuristicvalue; 

        // weighted straight-line distance, w=5
        // Double heuristicvaluedouble = 5*(Math.sqrt(Math.pow(rowdifference, 2)+Math.pow(coldifference, 2)));
        // Integer heuristicvalue = heuristicvaluedouble.intValue();
        // return heuristicvalue;

        // weighted straight-line distance, w=10
        // Double heuristicvaluedouble = 10*(Math.sqrt(Math.pow(rowdifference, 2)+Math.pow(coldifference, 2)));
        // Integer heuristicvalue = heuristicvaluedouble.intValue();
        // return heuristicvalue;
    }
    public static int[] deepcopy(int[] arr){
        int size = thegraph.size();
        int[] newarr = new int[size];
        for(int i=0; i<size; i++){
            newarr[i] = arr[i];
        }
        return newarr;
    }

    public static int startgoaldistance(){
        Integer goalrow = goal/col;
        Integer goalcol = goal%col;
        Integer startrow = start/col;
        Integer startcol = start%col;
        int rowdifference = Math.abs(goalrow-startrow);
        int coldifference = Math.abs(goalcol-startcol);
        int distance = (rowdifference + coldifference)/4;
        // Integer distance = distancedouble.intValue();
        return distance; 

    }

    // push adj indexes into the stack
    public static ArrayList<Integer> calculate(){
    	set_heuristic();
    	int index = start;
        int[] parentlistcopy = new int[thegraph.size()];
        Node item = thegraph.get(index);
        ArrayList<Integer> adj=item.getNext();
        // System.out.println("before while loop");
        // System.out.println(isLargest(index,adj));
        int count = 0;
        int nodesexpanded = 0;

        while(Math.abs((int)(index/col)-(int)(start/col))+Math.abs(index%col-start%col)<startgoaldistance()){
            if(!isLargest(index, adj)){
               
            	int nextindex = getLargest(adj);
                nodesexpanded += 1;
                explored.add(nextindex);  
            	track_path(index,nextindex);
            	index = nextindex;
                // System.out.println("nextindex: " + index);

            	// System.out.println(index);
            	item = thegraph.get(index);
            	adj=item.getNext();
            	if(index==goal){
            		// System.out.println("index: " + index);
            		// System.out.println("goal: " + goal);
            		count = 100;
            		break;
            	}
                // System.out.println("success");
            }
            else{
                 // System.out.println("problem");
               
                break;
            }
            // parentlistcopy = deepcopy(parentlist);
            // for(int i=0; i<thegraph.size(); i++){
            //     parentlist[i] = 0;
            // }
                   	
        }
        // for(int i=0; i<thegraph.size();i++){
        //     System.out.print(parentlistcopy[i]);
           
        // }
        int indexstart = index;
        // System.out.println("the start: " + indexstart);
        parentlistcopy = deepcopy(parentlist);

        while(count<100){
	        while(!isLargest(index, adj)){
	        	// System.out.println("while loop");
	        	int nextindex = getLargest(adj);
                nodesexpanded += 1;
                explored.add(nextindex);  
	        	track_path(index,nextindex);
	        	index = nextindex;
	        	// System.out.println(index);
	        	item = thegraph.get(index);
	        	adj=item.getNext();
	        	if(index==goal){
	        		// System.out.println("count: " + count);
	        		// System.out.println("index: " + index);
	        		// System.out.println("goal: " + goal);
	        		count = 100;
	        		break;
	        	}


	        }

            if(count<100){
                    count+=1;
                    // System.out.println(indexstart);
                    index = indexstart;
                    item = thegraph.get(index);
                    adj=item.getNext();  
                    parentlist = deepcopy(parentlistcopy);
                }   
            else{
                break;
            } 
             
	    }

	    // System.out.println("index: "+ index );
		int curelement = index;
		int indexcopy = index;
		while(curelement!=start){
            // System.out.println("crr" + curelement);
			thepath.add(curelement);
			curelement = parentlist[curelement];
		}
		if(indexcopy==goal){
            if(thepath.size()>1){
                thepath.remove(0);
                explored.remove(explored.size()-1);                
            }
            else if(thepath.size()==0){
                thepath.add(-1);
            }


		}
        System.out.println("Nodes Expanded:" + nodesexpanded);

		return thepath;
    } 


    public static boolean isLargest(int index, ArrayList<Integer> list){
    	int listsize = list.size();
    	int count = 0;
    	for(int i=0; i<listsize; i++){
    		int adjnumindex = list.get(i);
    		int adjnum = heuristiclist[adjnumindex];
    		int num = heuristiclist[index];
    		if(num < adjnum){
    			count += 1;
    		}
    	}
    	if(count==0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public static int getLargest(ArrayList<Integer> list){
    	Collections.shuffle(list);
    	int listsize = list.size();
    	int largestvalueindex = list.get(0);
    	int largestvalue = heuristiclist[largestvalueindex];
    	for(int i=1; i<listsize; i++){
			int curvalueindex = list.get(i);
			int curvalue = heuristiclist[curvalueindex];
			// System.out.println(largestvalue);
			// System.out.println("curvalue" + curvalue);

			if(curvalue>largestvalue){
				largestvalue=curvalue;
				largestvalueindex = curvalueindex;
    		}
    	}
    	return largestvalueindex;
    }

    // return the adj indexes
    public static ArrayList<Integer> getadj(Integer index){
        Node item = thegraph.get(index);
        ArrayList<Integer> adj = item.getNext();
        return adj;
    }  

    public static ArrayList<Integer> return_explored(){
        return explored;
    }

}




