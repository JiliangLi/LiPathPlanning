import java.io.*; 
import java.util.*; 
  
public class NodeQueue extends Bfs{    
    static int row;
    static int col;
    static int[][] arr;
    static ArrayList<Integer> checked = new ArrayList<Integer>();
    static Queue<Integer> queue = new LinkedList<Integer>();
    static Graph3 graph = new Graph3();
    static LinkedList<Node> queuegraph;
    static int[] parentlist = new int[0];

    // constructor
    public NodeQueue(){
    }

    // constructor
    public NodeQueue(int row, int col, int[][] arr){
        // System.out.println(arr);
        this.row = row;
        this.col = col;
        this.arr = arr;
        this.queuegraph = graph.getGraph(row, col, arr);
        int size = queuegraph.size();
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

    public static void enqueue(Integer index){ 
        Node item = queuegraph.get(index);
        ArrayList<Integer> adj=item.getNext();
        // System.out.println(adj);
        int size = adj.size();
        for(int i = 0; i < size; i++) 
        {
            int adjindex = adj.get(i);
            if(notIn(adjindex, checked)){
                track_path(index, adjindex);
                // System.out.println(adjindex);
                queue.offer(adjindex); 
                checked.add(adjindex);
            }
        } 
        // System.out.println("");
    } 

    public static ArrayList<Integer> get_checked(){
        return checked;
    }
    
    public static ArrayList<Integer> queue_getadj(Integer index){
        Node item = queuegraph.get(index);
        ArrayList<Integer> adj = item.getNext();
        return adj;
    }  

    public static void dequeue(){ 
        Integer polled = (Integer) queue.poll(); 
        checked.add(polled); 
        
    } 
  
    public static Integer queue_peek(){ 
        Integer element = (Integer) queue.peek(); 
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
        Integer element = queue.peek();
        if (element==null){
            // System.out.println("null");
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















// import java.io.*; 
// import java.util.*; 
  
// public class NodeQueue extends Graph{    
//     static int row;
//     static int col;
//     static int[][] arr;
//     static LinkedList<Node> queuegraph;
//     static ArrayList<Integer> unchecked = new ArrayList<Integer>();
//     static ArrayList<Integer> checked = new ArrayList<Integer>();
//     static Queue<Integer> queue = new LinkedList<Integer>();

//     public NodeQueue(){
//     }

//     public NodeQueue(int row, int col, int[][] arr){
//         this.row = row;
//         this.col = col;
//         this.arr = arr;
//     }

//     public static void enqueue(int index, LinkedList<Node> queuegraph, Queue<Integer> queue) { 
//         Node item = queuegraph.get(index);
//         ArrayList<Integer> adj=item.getNext();
//         // System.out.println(adj);
//         int size = adj.size();
//         for(int i = 0; i < size; i++) 
//         {
//             int adjindex = adj.get(i);
//             if(notIn(adjindex, unchecked)){
//                 queue.offer(adjindex); 
//                 unchecked.add(adjindex);
//             }
//         } 
//     } 
      
//     public static void dequeue(Queue<Integer> queue) 
//     { 
//         Integer dequeued = (Integer) queue.poll(); 
//         checked.add(dequeued); 
        
//     } 
  
//     public static Integer queue_peek(Queue<Integer> queue) 
//     { 
//         Integer element = (Integer) queue.peek(); 
//         return element;
//     } 
      
//     // public static void stack_search(Stack<Integer> stack, int element) 
//     // { 
//     //     Integer pos = (Integer) stack.search(element); 
  
//     //     if(pos == -1) 
//     //         System.out.println("Element not found"); 
//     //     else
//     //         System.out.println("Element is found at position " + pos); 
//     // } 


//     public static boolean notIn(int num, ArrayList<Integer> list){
//         int size = list.size();
//         int count = 0;
//         for(int i=0; i<size; i++){
//             int nth = list.get(i);
//             if(num==nth){
//                 count+=1;
//             }
//         }
//         if(count==0){
//             return true;
//         }
//         else{
//             return false;
//         }
//     }

//     public static boolean isNotEmpty(int index, LinkedList<Node> queuegraph){
//         Node item = queuegraph.get(index);
//         if(item!=null){
//             return true;
//         }
//         else{
//             return false;
//         }
//     }

//     public static Queue<Integer> readQueue(){
//         Graph graph = new Graph();
//         queuegraph = graph.getGraph(row, col, arr);
//         enqueue(5, queuegraph, queue);
//         dequeue(queue);
//         return queue;
//     }

// }




