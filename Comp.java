import java.util.Comparator;

public class Comp implements Comparator<state> {
    public int compare(state s1 , state s2){
        int tmpN1 = s1.weight;
        int tmpN2 = s2.weight;
        if(tmpN1>tmpN2){
            return 1;
        }
        else if(tmpN1<tmpN2){
            return -1;
        }
        else {
            return 0;
        }
    }
}
// to check the lowest cost between different paths and take it