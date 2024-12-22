import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Pato2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        while (s.hasNext()) {
            int n = Integer.parseInt(s.nextLine().trim()); // Número de palabras clave
            String[] p = s.nextLine().split(" "); // Palabras clave
            String[] q = s.nextLine().split(" "); // Puntajes
            String[] f = s.nextLine().split(" "); // Frase a evaluar

            HashMap<String, Integer> m = new HashMap<>();
            HashSet<String> v = new HashSet<>();

            // Llenar el mapa con palabras clave y puntajes
            for (int i = 0; i < n; i++) {
                m.put(p[i], Integer.parseInt(q[i]));
            }

            int t = 0; // Puntaje total del estudiante

            // Calcular el puntaje de la frase
            for (String x : f) {
                if (m.containsKey(x) && !v.contains(x)) {
                    t += m.get(x);
                    v.add(x); // Añadir la palabra a la lista de palabras encontradas
                }
            }

            System.out.println(t); // Mostrar el puntaje total
        }

        s.close();
    }
}
