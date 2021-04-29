import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Hashtable<Integer, Integer> h = new Hashtable<>();
        h.put(1, 1);
        h.put(2, 2);
        h.put(3, 3);
        System.out.println(h.values().toArray()[h.size()-1]);
    }
}
