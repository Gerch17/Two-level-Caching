import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        RamContainer<Integer, String> ram = new RamContainer<>(3);
        ram.put(1, "qwe");
        ram.put(2, "asd");
        ram.put(3, "zxc");
        ram.put(4, "rty");
        ram.get(1);
        System.out.println(ram.size());
        /*Hashtable<Integer, Integer> h = new Hashtable<>();
        h.put(1, 10);
        h.put(2, 20);
        h.put(3, 30);
        h.remove(3);
        h.put(4, 40);
        h.get(3);
        System.out.println(h.entrySet().toArray()[3]);*/
    }
}
