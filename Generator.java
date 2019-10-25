import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*; 


public  class Generator{
    static int[][] arrcopy;

    // main method
    public static void main(String[] args) {
        int row = 0;
        int col = 0;
        int[][] arr = new int[0][0];

        if (args.length != 2){
           
            System.out.println("Please provide two integers seperated by space, for example:");
            System.out.println("4 3");
            System.exit(0);
        }  
       
        else{
            try{
                row = Integer.parseInt(args[0]);
                col = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e){
                System.out.println("Please enter two integers");
                System.exit(0);
            }

            arr = new int[row][col];
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++){
                        arr[i][j] = 48;
                    }
                }
            } 
        // textfile(row, col, arr);
        // readblock(row, col, arr);
        generate_blocked_cell(row, col, arr);
        startgoal(row, col, arr);
        readgrid();
        

        System.out.println("");
        System.out.println("");
        System.out.println("DFS: ");
        long start = System.currentTimeMillis();
        Dfs dfs = new Dfs(row, col, arr);
        ArrayList<Integer> apath = dfs.calculate();
        ArrayList<Integer> checked_list = dfs.return_explored();
        long finish = System.currentTimeMillis();
        int indicator = apath.get(0);
        if(indicator!=-1){
            showexplored(checked_list, row, col, arr);
            showpath(apath, row, col, arr);
            System.out.println("");
            readgrid();
        }
        else{
            System.out.println("");
            System.out.println("No path exists");
        }
        long timeElapsed = finish - start;
        System.out.println("DFS Time Elapsed: " + timeElapsed + "ms");
        System.out.println("");
        arr = deepcopy(row, col, arrcopy);

        
        System.out.println("");
        System.out.println("");
        System.out.println("BFS: ");
        start = System.currentTimeMillis();
        Bfs bfs = new Bfs(row, col, arr);
        apath = bfs.calculate();
        checked_list = bfs.return_explored();
        finish = System.currentTimeMillis();
        indicator = apath.get(0);
        if(indicator!=-1){
            showexplored(checked_list, row, col, arr);
            showpath(apath, row, col, arr);
            System.out.println("");
            readgrid();
        }
        else{
            System.out.println("");
            System.out.println("No path exists");
        }
        timeElapsed = finish - start;
        System.out.println("BFS Time Elapsed: " + timeElapsed + "ms");
        System.out.println("");
        arr = deepcopy(row, col, arrcopy);
        


        System.out.println("");
        System.out.println("");
        System.out.println("UCS: ");
        start = System.currentTimeMillis();
        Ucs ucs = new Ucs(row, col, arr);
        apath = ucs.calculate();
        checked_list = ucs.return_explored();
        finish = System.currentTimeMillis();
        indicator = apath.get(0);
        if(indicator!=-1){
            showexplored(checked_list, row, col, arr);
            showpath(apath, row, col, arr);
            System.out.println("");
            readgrid();
        }
        else{
            System.out.println("");
            System.out.println("No path exists");
        }
        timeElapsed = finish - start;
        System.out.println("UCS Time Elapsed: " + timeElapsed + "ms");
        System.out.println("");
        arr = deepcopy(row, col, arrcopy);




        System.out.println("");
        System.out.println("");
        System.out.println("A*: ");
        start = System.currentTimeMillis();
        Astar astar = new Astar(row, col, arr);
        apath = astar.calculate();
        checked_list = astar.return_explored();
        finish = System.currentTimeMillis();
        indicator = apath.get(0);
        if(indicator!=-1){
            showexplored(checked_list, row, col, arr);
            showpath(apath, row, col, arr);
            System.out.println("");
            readgrid();
        }
        else{
            System.out.println("");
            System.out.println("No path exists");
        }
        timeElapsed = finish - start;
        System.out.println("Astar Time Elapsed: " + timeElapsed + "ms");
        System.out.println("");
        arr = deepcopy(row, col, arrcopy);



        System.out.println("");
        System.out.println("");

        System.out.println("Hill Climbing: ");
        start = System.currentTimeMillis();
        HillClimbing hillclimbing = new HillClimbing(row, col, arr);
        apath = hillclimbing.calculate();
        checked_list = hillclimbing.return_explored();
        finish = System.currentTimeMillis();
        indicator = apath.get(0);
        if(indicator!=-1){
            showexplored(checked_list, row, col, arr);
            showpath(apath, row, col, arr);
            System.out.println("");
            readgrid();
        }
        else{
            System.out.println("");
            System.out.println("No path exists");
        }
        timeElapsed = finish - start;
        System.out.println("");
        System.out.println("HillClimbing Time Elapsed: " + timeElapsed + "ms");
    }



    public static int[][] deepcopy(int row, int col, int[][] arr){
        int[][] newarr = new int[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                newarr[i][j] = arr[i][j];
            }
        }
        return newarr;
    }

    public static void generate_blocked_cell(int row, int col, int[][] arr){
        Random rand = new Random(); 

        for(int i=0; i<row; i++){
            for(int j=0; j<rand.nextInt(col); j++){
                int randint = rand.nextInt(col);
                arr[i][randint] = 88;
            }
        }       
        textfile(row, col, arr);
    }

    public static void showexplored(ArrayList<Integer> checked_list, int row, int col, int[][] arr){
        int size = checked_list.size();
        for(int i=0; i<size; i++){
            Integer index = checked_list.get(i);
            Integer coordinaterow = index/col;
            Integer coordinatecol = index%col; 
            arr[coordinaterow][coordinatecol] = 124;           
        }
        textfile(row, col, arr);

    }

    // store and print out the calculated path
    public static void showpath(ArrayList<Integer> apath, int row, int col, int[][] arr){
        int size = apath.size();
        for(int i=0; i<size; i++){
            if(i!=size-1){
                Integer index = apath.get(i);
                Integer indexnext = apath.get(i+1);
                Integer coordinaterow = index/col;
                Integer coordinatecol = index%col;
                if(indexnext==index+1|indexnext==index-1){
                    arr[coordinaterow][coordinatecol]= 46;
                }
                else if(indexnext==index+1-col|indexnext==index-1+col){
                    arr[coordinaterow][coordinatecol]= 46;
                }
                else if(indexnext==index-1-col|indexnext==index+1+col){
                    arr[coordinaterow][coordinatecol]= 46;
                }
                else{
                    arr[coordinaterow][coordinatecol]= 46;
                }
            }
            // System.out.print(i);
            else if(i==size-1&&i!=0){
                Integer index = apath.get(i);
                Integer indexprev = apath.get(i-1);
                Integer coordinaterow = index/col;
                Integer coordinatecol = index%col;
                if(indexprev==index+1|indexprev==index-1){
                    arr[coordinaterow][coordinatecol]= 46;
                }
                else if(indexprev==index+1-col|indexprev==index-1+col){
                    arr[coordinaterow][coordinatecol]= 46;
                }
                else if(indexprev==index-1-col|indexprev==index+1+col){
                    arr[coordinaterow][coordinatecol]= 46;
                }
                else{
                    arr[coordinaterow][coordinatecol]= 46;
                }
            }
            else{
                Integer index = apath.get(i);
                Integer coordinaterow = index/col;
                Integer coordinatecol = index%col;
                arr[coordinaterow][coordinatecol]= 46;
                }
        }
        textfile(row, col, arr);
    }



    // Read the grids generated
    public static void readgrid(){

        Path path = Paths.get("textfile.dat");     

        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null){
            System.out.println(currentLine);
            }
        } 

        catch (IOException ioe){
            System.out.println("Can't read file: " + ioe.getMessage());
        }
    }



    // Let the user select a starting position (mark it with "S") and a goal position (mark it with "G")
    public static void startgoal(int row, int col, int[][]arr){
        int num = 1;
        int overlap = 1;
        Integer startrow = 0;
        Integer startcol = 0;
        Integer goalrow = 0;
        Integer goalcol = 0;

        while (num == 1){
            Scanner start = new Scanner(System.in);  
            System.out.println("Enter the row of the starting position");
            try{
                startrow = Integer.parseInt(start.nextLine());  
                if (startrow < row+1){
                    num += 1;
                }
                else{
                    System.out.println("Please enter a valid integer");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a valid integer");
            }
        }

        while (num == 2){
            Scanner start2 = new Scanner(System.in);  
            System.out.println("Enter the column of the starting position");
            try{
                startcol = Integer.parseInt(start2.nextLine()); 
                if(startcol < col+1){
                    num += 1;
                } 
                else{
                    System.out.println("Please enter a valid integer");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a valid integer");
            }
        }

        arr[startrow-1][startcol-1] = 83;

        while (overlap != 0){
            while (num ==3){
                Scanner goal = new Scanner(System.in);  
                System.out.println("Enter the row of the goal position");
                try{
                    goalrow = Integer.parseInt(goal.nextLine());
                    if(goalrow < row+1){
                        num += 1;
                    } 
                    else{
                        System.out.println("Please enter a valid integer");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a valid integer");
                }
            }  
            while (num ==4){
                Scanner goal2 = new Scanner(System.in);  
                System.out.println("Enter the column of the goal position");
                try{
                    goalcol = Integer.parseInt(goal2.nextLine());  
                    if(goalcol < col+1){
                        num += 1;
                    } 
                    else{
                        System.out.println("Please enter a valid integer");
                    }
                }
                catch(NumberFormatException e){
                    // System.out.println("num:"+num);
                    System.out.println("Please enter a valid integer");
                }

                if (goalrow==startrow && goalcol==startcol){
                    System.out.println("The goal position cannot be the starting position");
                    num = 3;
                }
                else{
                    if(num!=4){
                        arr[goalrow-1][goalcol-1] = 71;
                        overlap = 0;
                    }
                }
            }
        }
        arrcopy = deepcopy(row, col, arr);

        textfile(row, col, arr);
    }



    //Add the option to read an array with positions (x,y) to indicate blocked cells. Mark those cells with "X". 
    public static void readblock(int row, int col, int[][] arr){
        int count = 1;
        int num = 1;
        int answercheck = 1;
        Integer blockedrow = 0;
        Integer blockedcol = 0;
        Integer answer = 0;


        while(count!=0){

            while (num!=0){
                Scanner block = new Scanner(System.in);  
                System.out.println("Enter the row of the blocked cell");
                try{
                    blockedrow = Integer.parseInt(block.nextLine());
                    if(blockedrow < row+1){
                        num = 0;
                    } 
                    else{
                        num = 1;
                        System.out.println("Please enter a valid integer");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a valid integer"); 
                }
            } 
            
            num = 1;

            while (num!=0){
                Scanner block2 = new Scanner(System.in);  
                System.out.println("Enter the column of the blocked cell");
                try{
                    blockedcol = Integer.parseInt(block2.nextLine());  
                    if(blockedcol < col+1){
                        num = 0;
                    }
                    else{
                        num = 1;
                        System.out.println("Please enter a valid integer");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("please enter a valid integer");
                }
            }
            num = 1;

            arr[blockedrow-1][blockedcol-1] = 88;

            textfile(row, col, arr);

            while (answercheck == 1){
                Scanner question = new Scanner(System.in);  
                System.out.println("More blocked cells?");
                System.out.println("    1) yes");
                System.out.println("    2) no ");
                try{
                    answer = Integer.parseInt(question.nextLine());
                    if (answer == 1 | answer == 2){
                        answercheck = 0;
                    }
                    else{
                        System.out.println("please enter number 1 or 2");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("please enter number 1 or 2");
                }
            }
            answercheck = 1;
            if (answer==2){
                count=0;
            }
        }
    }



    // generate a text file of x rows and y columns containing "0" separated by white spaces
    public static void textfile(int row, int col, int[][] arr){
        try (FileOutputStream myFile = new FileOutputStream("textfile.dat")){  
            
            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    myFile.write(arr[i][j]);
                    myFile.write(32);
                    if (j == col-1){
                        myFile.write(10);
                    }
                }
            }
        } 

        catch (IOException ioe) {
            System.out.println("Could not write into the file: " + ioe.getMessage());    
        }
    }


}


