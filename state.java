import java.util.ArrayList;

public class state {

    String name;
    boolean visited = false;
    boolean isGoal = false;
    int weight ;
    // the cost of the node
    state father;
    int h ;
    // the heieght of the node for A* algorithm
    ArrayList<state> children = new ArrayList<state>();
    // the childern for each node
    ArrayList<Integer> costs = new ArrayList<Integer>();
    //the cost between the node and its child

    state(String s){
        name = s;
    }

    state getCopy(){
        state s = new state(name);
        s.visited = visited;
        s.isGoal = isGoal;
        s.weight = weight;
        s.h = h;
        s.father = father;
        for(int i=0 ;i<children.size();i++){
            s.addChild(children.get(i).getCopy(),costs.get(i));
        }
        return s;
    // to save the node when walk on graph
    }

    void addChild(state s , int cost){
        children.add(s);
        costs.add(cost);
    // to add each node childrens and its costs
    }

    boolean same(state s){
        if(s.name == this.name){
            return true;
        }
        return false;
    }

    boolean inArray(ArrayList<state> arrayList){
        for(int i=0 ;i<arrayList.size();i++){
            if(same(arrayList.get(i))){
                return true;
            }
        }
        return false;

    }
}
