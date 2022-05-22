import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class graph {
    state start ;

    void setGraph(){

        state S = new state("S");
        state A = new state("A");
        state B = new state("B");
        state C = new state("C");
        state G = new state("G");
        G.isGoal = true;
    // creat nodes
        S.addChild(A,1);
        S.addChild(B,4);
        A.addChild(B,3);
        A.addChild(C,6);
        A.addChild(G,1);
        B.addChild(C,8);
        C.addChild(G,10);
    // creat graph "each node childrens and their costs"
        S.h = 7;
        A.h = 6;
        B.h = 4;
        C.h = 2;
        G.h = 0;
    // the height between each node and the goal for A* algorithm
        start = S.getCopy();
    }

    void setFromFile(){
    // this will read the graph from the file
        int count = 0;
        ArrayList<state> states = new ArrayList<>();
        try {
            File file = new File("states.txt");
            Scanner myReader = new Scanner(file);
            if(myReader.hasNextLine()){
                count = Integer.parseInt(myReader.nextLine());
            }
            // check next lines exists
            for(int i=0 ;i<count ;i++){
                if(myReader.hasNextLine()){
                    String line = myReader.nextLine();
                    String[] parts = line.split(" ");
                    state s;
                    if(parts.length>1){
                        s = new state(parts[0]);
                        if(parts[1].equals("start")){
                            start = s;
                        }
                        else{
                            s.isGoal = true;
                        }
                    }
                    else{
                        s = new state(line);
                    }
                    states.add(s);
                }
            }
            while (myReader.hasNextLine()){
                String line = myReader.nextLine();
                String[] parts = line.split(" ");
                state s = new state("null");
                for(int i=0;i<states.size();i++){
                    if(states.get(i).name.equals(parts[0])){
                        s = states.get(i);
                    }
                }
                for(int i=1 ;i<parts.length;i++){
                    String[] child = parts[i].split(":");
                    int index =0;
                    for(int j=0;j<states.size();j++){
                        if(states.get(j).name.equals(child[0])){
                            index = j;
                        }
                    }
                    s.addChild(states.get(index),Integer.parseInt(child[1]));
                }
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    state usc(){
        Queue<state> open = new PriorityQueue<state>(new Comp());
        //array for open nodes
        ArrayList<state> close = new ArrayList<state>();
        //array for closed node
        open.add(start);
        boolean success = false;
        state tmp ;

        while (!open.isEmpty() && !success){
            tmp = open.poll();
            if(tmp.isGoal){
                success = true;
                return tmp;
            // return the goal when find it, when there's still nodes in open and didn't reach the goal yet
            }
            else{
                close.add(tmp.getCopy());
                for(int i=0 ; i<tmp.children.size() ;i++){
                    if(!tmp.children.get(i).inArray(close)){
                        tmp.children.get(i).father = tmp.getCopy();
                        tmp.children.get(i).weight = tmp.weight + tmp.costs.get(i);
                        open.add(tmp.children.get(i).getCopy());
                    }
                // to find path and calculate its cost from start to current node until get goal
                }
            // walk in the graph until find the goal
            }
        }
        return null;
    }

    state AStar(){
        Queue<state> open = new PriorityQueue<state>(new Comp());
        ArrayList<state> close = new ArrayList<state>();
        open.add(start);
        boolean success = false;
        state tmp ;

        while (!open.isEmpty() && !success){
            System.out.println("ss");
            tmp = open.poll();
            if(tmp.isGoal){
                success = true;
                return tmp;
            }
            else{
                close.add(tmp.getCopy());
                for(int i=0 ; i<tmp.children.size() ;i++){
                    if(!tmp.children.get(i).inArray(close)){
                        tmp.children.get(i).father = tmp.getCopy();
                        tmp.children.get(i).weight = tmp.weight + tmp.costs.get(i) + tmp.children.get(i).h;
                        open.add(tmp.children.get(i).getCopy());
                        tmp.children.get(i).weight -= tmp.children.get(i).h;
                    }
                }
            }
        // same as ucs algorithm but with calculate the height between current node and the goal
        }
        return null;
    }

    void getPath(state s){
        state tmp = s;
        ArrayList<String> path = new ArrayList<>();
        while (s!=null){
            path.add(s.name);
            s = s.father;
        }
        for(int i=path.size()-1 ; i>=0 ; i--){
            System.out.println(path.get(i));
        }
    // to save the path nodes and print it out
    }

}
