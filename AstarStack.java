import java.io.*; 
import java.util.*; 
import java.lang.*;
  
public class AstarStack extends Astar{    
    static int row;
    static int col;
    static int start;    
    static int goal;
    static int[][] arr;
    static double[][] theweightedgraph;
    static WeightedGraph weightedgraph = new WeightedGraph();
    static ArrayList<Integer> checked = new ArrayList<Integer>();
    static Stack<Node> stack = new Stack<Node>();
    static Graph1 graph = new Graph1();
    static LinkedList<Node> thegraph;
    static int[] parentlist = new int[0];
    static ArrayList<Node> explorednodes = new ArrayList<Node>();

    // constructor
    public AstarStack(){
    }

    // constructor
    public AstarStack(int row, int col, int[][] arr){
        this.row = row;
        this.col = col;
        this.arr = arr;
        this.goal = goal;
        this.thegraph = graph.getGraph(row, col, arr);
        int size = thegraph.size();
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
        // this.parentlist = new int[size];
        // for(int i=0; i<size; i++){
        //     this.parentlist[i] = -1;
        // }
        this.weightedgraph = new WeightedGraph(row, col, arr, thegraph);
        this.theweightedgraph = weightedgraph.getweightedgraph();
    }

    // keep track of the parent
    // public static void track_path(int parent, int child){
    //     parentlist[child] = parent;
    // }

    // return parentlist
    // public static int[] get_parentlist(){
    //     return parentlist;
    // }

    // add to checked
    public static void add_checked(Integer index){
        checked.add(index);
    }
    
    public static Node create_node(int id){
        Node curnode = new Node(id);
        explorednodes.add(curnode);
        int data = explorednodes.size()-1;
        explorednodes.remove(data);
        curnode.setData(data);
        explorednodes.add(curnode);
        return curnode;       
    }

    public static void set_parent(int data, int parent){
        Node curnode = explorednodes.get(data);
        int id = curnode.getId();
        Node newnode = new Node(data, id, parent);
        explorednodes.set(data, newnode);
    }

    public static int get_id(int data){
        Node curnode = explorednodes.get(data);
        int id = curnode.getId();
        return id;
    }

    public static void stack_clear(int id){
        int i=0;
        while(i<stack.size()){
            Node curnode = stack.get(i);
            if(curnode.getId()==id){
                stack.remove(i);
                i-=1;
            }
            i+=1;
        }
    }

    public static void pushnode(Node node){
        stack.push(node);
    }
    // push adj indexes into the stack
    public static void stack_push(Integer id, Integer data){ 
         Node item = thegraph.get(id);
        ArrayList<Integer> adj=item.getNext();
        // System.out.println(adj);
        int size = adj.size();
        // System.out.println("adjsize " + size);
        for(int i = 0; i < size; i++){
            int adjindex = adj.get(i);
            if(notIn(adjindex, checked)){
                Node curnode = create_node(adjindex);
                set_parent(curnode.getData(), data);
                // System.out.println(adjindex);
                pushnode(curnode);
                // checked.add(adjindex);
            }
            // else{
            //     System.out.println("in");
            // }
        } 
    } 

    // manhattan distance heuristic value
    public static double get_heuristic_value(int index){
        Integer goalrow = goal/col;
        Integer goalcol = goal%col;
        Integer indexrow = index/col;
        Integer indexcol = index%col;
        int rowdifference = Math.abs(goalrow-indexrow);
        int coldifference = Math.abs(goalcol-indexcol);

        // manhattan distance
        double heuristicvalue = rowdifference + coldifference;
        return heuristicvalue; 

        // horiz/vert distance
        // double heuristicvalue = Math.min(rowdifference, coldifference);
        // return heuristicvalue; 

        // weighted straight-line distance, w=1
        // double heuristicvalue = (Math.sqrt(Math.pow(rowdifference, 2)+Math.pow(coldifference, 2)));
        // return heuristicvalue; 

        // weighted straight-line distance, w=2
        // double heuristicvalue = 2*(Math.sqrt(Math.pow(rowdifference, 2)+Math.pow(coldifference, 2)));
        // return heuristicvalue; 

        // weighted straight-line distance, w=5
        // double heuristicvalue = 5*(Math.sqrt(Math.pow(rowdifference, 2)+Math.pow(coldifference, 2)));
        // return heuristicvalue;

        // weighted straight-line distance, w=10
        // double heuristicvalue = 10*(Math.sqrt(Math.pow(rowdifference, 2)+Math.pow(coldifference, 2)));
        // return heuristicvalue;
    }
    
    public static ArrayList<Integer> get_parentlist(int data){
        ArrayList<Integer> parentlist = new ArrayList<Integer>();
        int id = 0;
        Node curnode;
        int thedata = data;
        while(id!=-1){
            curnode = explorednodes.get(thedata);
            id = curnode.getId();
            if(id!=-1){
                parentlist.add(id);
            }
            int parent = curnode.getParent();
            thedata = parent;
        }
        parentlist.add(start);
        return parentlist;
    }

    // sort stack based on the accumulated weight
    public static void stack_sort(){
                int size = stack.size();
        ArrayList<Node> tempstack = new ArrayList<Node>();
        double[] thelist = new double[size];
        for(int i=0; i<size; i++){

            Node topnode = stack.peek();
            int data = topnode.getData();
            int id = topnode.getId();
            ArrayList<Integer> theparentlist = get_parentlist(data);
            int parentlistsize = theparentlist.size();
            double heuristic = get_heuristic_value(id);
            double weight = heuristic;
            int count = 0;
            // theparentlist.remove(0);

            // System.out.println("parentlist size" + theparentlist.size());
            for(int j=0; j<parentlistsize-1; j++){
                int k=j+1;
                weight += theweightedgraph[theparentlist.get(k)][theparentlist.get(j)];
                
            }

            // parentlistsize = theparentlist.size();
            // if(parentlistsize>1){
            //     weight += theweightedgraph[start][theparentlist.get(0)];
            // }
   
            // System.out.println(stack.peek().getId() + " the total: "+ weight + " " + theparentlist.size());
            tempstack.add(stack.pop());
            thelist[i] = weight;
        }        

        // System.out.println("stacksize: " + stack.size());

        // System.out.print("stack empty:" + stack.empty());
        // int stacksize = stack.size();
        // System.out.println(stacksize);

        for(int j=0; j<size; j++){
            for(int k=j+1; k<size; k++){
                if(thelist[j] < thelist[k]){
                    Node temp0 = tempstack.get(j);
                    double temp1 = thelist[j];
                    tempstack.set(j, tempstack.get(k));
                    thelist[j] = thelist[k];
                    tempstack.set(k, temp0);
                    thelist[k] = temp1;
                }
            }
        }

        for(int l=0; l<size; l++){
            stack.push(tempstack.get(l));
            // System.out.print(thelist[l][1]);
        }

        // System.out.println("stacksize: " + stack.size());
        // for(int m=0; m<stack.size(); m++){
        //     System.out.print(stack.get(m).getId());
        //     System.out.print(" / ");
        // }
        // System.out.println("");
        // int value = stack.peek();
        // System.out.println(value);
    }
        

    // return the checked list
    public static ArrayList<Integer> get_checked(){
        return checked;
    }
    
    // return the adj indexes
    public static ArrayList<Integer> stack_getadj(Integer index){
        Node item = thegraph.get(index);
        ArrayList<Integer> adj = item.getNext();
        return adj;
    }  

    // pop the top item
    public static void stack_pop(){ 
        Node popped = stack.pop(); 
        int data = popped.getData();
        // checked.add(data); 
    } 
 
    // look at the top item without deleting it 
    public static Integer stack_peek(){ 
        Node topnode = stack.peek();
        int data = topnode.getData(); 
        return data;
    } 


    // see if a number is in a list
    public static boolean notIn(int num, ArrayList<Integer> list){
        int size = list.size();
        int count = 0;
        for(int i=0; i<size; i++){
            int nth = list.get(i);
            if(num==nth){
                count+=1;
            }
        }
        if(count==0){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isEmpty(){
        if (stack.empty()){
            return true;
        }
        else{
            return false;
        }
    }

}




