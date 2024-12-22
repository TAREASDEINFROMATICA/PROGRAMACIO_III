import java.util.*;

public class Main1233 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            int n = s.nextInt();
            boolean p = true, q = true, pq = true;
            Stack<Integer> st = new Stack<>();
            Queue<Integer> qu = new LinkedList<>();
            PriorityQueue<Integer> pqe = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                int c = s.nextInt();
                int x = s.nextInt();

                if (c == 1) {
                    if (p) st.push(x);
                    if (q) qu.add(x);
                    if (pq) pqe.add(x);
                } else {
                    if (p && (st.isEmpty() || st.pop() != x)) p = false;
                    if (q && (qu.isEmpty() || qu.poll() != x)) q = false;
                    if (pq && (pqe.isEmpty() || pqe.poll() != x)) pq = false;
                }
            }

            if (p && !q && !pq) System.out.println("stack");
            else if (!p && q && !pq) System.out.println("queue");
            else if (!p && !q && pq) System.out.println("priority queue");
            else if (!p && !q && !pq) System.out.println("impossible");
            else System.out.println("not sure");
        }
    }
}
