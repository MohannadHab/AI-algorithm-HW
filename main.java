public class main {
    public static void main(String[] args)  {
        graph graph = new graph();
    // make a new graph
        graph.setGraph();
    // enter nodes manually
        //graph.setFromFile();
    // read the graph from the file
        state goal = graph.usc();
    // ucs algorithm 
        //state goal = graph.AStar();
    // A* algorithm
        graph.getPath(goal);
    // path to reach the goal
    }
}
