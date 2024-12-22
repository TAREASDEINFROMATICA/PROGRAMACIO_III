import java.util.*;

public class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        String[] n = s.nextLine().split(" ");
        LinkedHashMap<Integer, Integer> c = new LinkedHashMap<>();
        for (String x : n) {
            int k = Integer.parseInt(x);
            c.put(k, c.getOrDefault(k, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> e : c.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}
