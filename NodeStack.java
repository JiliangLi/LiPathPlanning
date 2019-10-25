import java.io.*; 
import java.util.*; 
  
public class NodeStack extends Dfs{    
    static int row;
    static int col;
    static int[][] arr;
    static ArrayList<Integer> checked = new ArrayList<Integer>();
    static Stack<Integer> stack = new Stack<Integer>();
    static Graph2 graph = new Graph2();
    static LinkedList<Node> stackgraph;
    static int[] parentlist = new int[0];

    // constructor
    public NodeStack(){
    }

    // constructor
    public NodeStack(int row, int col, int[][] arr){
        // System.out.println(arr);
        this.row = row;
        this.col = col;
        this.arr = arr;
        this.stackgraph = graph.getGraph(row, col, arr);
        int size = stackgraph.size();
        // System.out.println(size);
        this.parentlist = new int[size];
    }

    public static void track_path(int parent, int child){
        parentlist[child] = parent;
    }

    public static int[] get_parentlist(){
        return parentlist;
    }

    public static void add_checked(Integer index){
        checked.add(index);
    }

    public static void stack_push(Integer index){ 
        Node item = stackgraph.get(index);
        ArrayList<Integer> adj=item.getNext();
        // System.out.println(adj);
        int size = adj.size();
        for(int i = 0; i < size; i++) 
        {
            int adjindex = adj.get(i);
            if(notIn(adjindex, checked)){
                track_path(index, adjindex);
                // System.out.println(adjindex);
                stack.push(adjindex); 
                checked.add(adjindex);
            }
        } 
        // System.out.println("");
    } 

    public static ArrayList<Integer> get_checked(){
        return checked;
    }
    
    public static ArrayList<Integer> stack_getadj(Integer index){
        Node item = stackgraph.get(index);
        ArrayList<Integer> adj = item.getNext();
        return adj;
    }  

    public static void stack_pop(){ 
        Integer popped = (Integer) stack.pop(); 
        checked.add(popped); 
        
    } 
  
    public static Integer stack_peek(){ 
        Integer element = (Integer) stack.peek(); 
        return element;
    } 
      
    // public static void stack_search(Stack<Integer> stack, int element) 
    // { 
    //     Integer pos = (Integer) stack.search(element); 
  
    //     if(pos == -1) 
    //         System.out.println("Element not found"); 
    //     else
    //         System.out.println("Element is found at position " + pos); 
    // } 


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
    // public static boolean isNotEmpty(int index, LinkedList<Node> stackgraph){
    //     Node item = stackgraph.get(index);
    //     if(item!=null){
    //         return true;
    //     }
    //     else{
    //         return false;
    //     }
    // }

    // public static Stack<Integer> readStack(){

    //     stack_push(5, stackgraph, stack);
    //     stack_pop(stack);
    //     return stack;
    // }

}




