import java.util.*;

public class Pato4 {
    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        int t = e.nextInt(); // Número de casos de prueba
        e.nextLine(); // Limpiar la línea

        for (int i = 0; i < t; i++) {
            String s = e.nextLine().replace(" ", ""); // Leer el mensaje y eliminar espacios
            Map<Character, Integer> f = new HashMap<>(); // Mapa para las frecuencias de cada letra

            // Contar ocurrencias de cada letra
            for (char c : s.toCharArray()) {
                f.put(c, f.getOrDefault(c, 0) + 1);
            }

            // Obtener la frecuencia de la primera letra en el mapa
            int freq = f.get(s.charAt(0));
            boolean sarcasmo = true;

            // Verificar si todas las frecuencias son iguales
            for (int v : f.values()) {
                if (v != freq) {
                    sarcasmo = false;
                    break;
                }
            }

            // Imprimir el resultado según si es sarcasmo o no
            System.out.println(sarcasmo ? "No te lo tomes enserio" : "Meh");
        }

        e.close();
    }
}
