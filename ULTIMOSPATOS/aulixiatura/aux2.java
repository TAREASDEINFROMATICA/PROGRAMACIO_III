import java.util.*;

public class Laberinto {
    static class Nodo implements Comparable<Nodo> {
        int x, y, costo;
        Nodo(int x, int y, int costo) {
            this.x = x;
            this.y = y;
            this.costo = costo;
        }
        @Override
        public int compareTo(Nodo o) {
            return this.costo - o.costo;
        }
    }

    static int[] dx = {-1, 1, 0, 0}; // Movimientos: arriba, abajo, izquierda, derecha
    static int[] dy = {0, 0, -1, 1};

    public static int resolver(char[][] laberinto, int n, int m, int x1, int y1, int x2, int y2) {
        PriorityQueue<Nodo> pq = new PriorityQueue<>();
        boolean[][] visitado = new boolean[n][m];
        pq.add(new Nodo(x1, y1, 0));

        while (!pq.isEmpty()) {
            Nodo actual = pq.poll();
            if (visitado[actual.x][actual.y]) continue;
            visitado[actual.x][actual.y] = true;

            // Si llegamos al destino, devolvemos el costo
            if (actual.x == x2 && actual.y == y2) {
                return actual.costo;
            }

            // Expandir vecinos
            for (int i = 0; i < 4; i++) {
                int nx = actual.x + dx[i];
                int ny = actual.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visitado[nx][ny]) {
                    int nuevoCosto = laberinto[nx][ny] == '#' ? actual.costo + 1 : actual.costo;
                    pq.add(new Nodo(nx, ny, nuevoCosto));
                }
            }
        }
        return -1; // En caso de que no se encuentre solución
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Número de casos de prueba
        for (int caso = 1; caso <= t; caso++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            char[][] laberinto = new char[n][m];
            for (int i = 0; i < n; i++) {
                laberinto[i] = sc.next().toCharArray();
            }
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            int resultado = resolver(laberinto, n, m, x1, y1, x2, y2);
            System.out.println("Laberinto #" + caso + ": " + resultado);
        }
    }
}
