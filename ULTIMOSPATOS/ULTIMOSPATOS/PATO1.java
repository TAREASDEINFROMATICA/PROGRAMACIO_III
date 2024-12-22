import java.util.*;

public class PATO1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt(); // número de casos de prueba

        for (int i = 0; i < t; i++) {
            int n = s.nextInt(); // cantidad de números en la secuencia
            Set<Integer> set = new TreeSet<>(); // Utilizamos TreeSet para ordenar y eliminar duplicados

            for (int j = 0; j < n; j++) {
                int x = s.nextInt();
                set.add(x);
            }

            // Convertimos el conjunto ordenado a una lista para acceder a los elementos por índice
            List<Integer> l = new ArrayList<>(set);

            // Comprobamos si hay al menos dos elementos únicos
            if (l.size() < 2) {
                System.out.println("NO");
            } else {
                System.out.println(l.get(1)); // El segundo elemento en el conjunto ordenado
            }
        }
        s.close();
    }
}