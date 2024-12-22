
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class DiccionarioAndy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<String> palabrasUnicas = new TreeSet<>();
        
        // Leer todo el texto hasta EOF
        while (sc.hasNext()) {
            // Leer la siguiente línea del texto
            String linea = sc.nextLine().toLowerCase();
            
            // Usar regex para encontrar palabras (consecutivos de a-z)
            String[] palabras = linea.split("[^a-z]+");
            
            // Añadir cada palabra al conjunto si no es vacía
            for (String palabra : palabras) {
                if (!palabra.isEmpty()) {
                    palabrasUnicas.add(palabra);
                }
            }
        }
        
        // Imprimir todas las palabras únicas en orden alfabético
        for (String palabra : palabrasUnicas) {
            System.out.println(palabra);
        }
        
        sc.close();
    }
}
