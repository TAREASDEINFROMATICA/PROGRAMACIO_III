import java.util.*;

public class Pato3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int c = s.nextInt(); // Número de casos de prueba
        s.nextLine();

        for (int i = 0; i < c; i++) {
            int n = s.nextInt(); // Número de palabras en el diccionario
            s.nextLine();

            String[] palabras = new String[n];
            Map<String, Integer> mapaAnagramas = new HashMap<>();

            // Leer todas las palabras y almacenarlas
            for (int j = 0; j < n; j++) {
                palabras[j] = s.nextLine();
                String claveOrdenada = ordenarLetras(palabras[j]);
                
                // Contar las ocurrencias de cada "firma" de anagrama
                mapaAnagramas.put(claveOrdenada, mapaAnagramas.getOrDefault(claveOrdenada, 0) + 1);
            }

            // Calcular la cantidad de anagramas para cada palabra
            StringBuilder resultado = new StringBuilder();
            for (int j = 0; j < n; j++) {
                String claveOrdenada = ordenarLetras(palabras[j]);
                int cantidadAnagramas = mapaAnagramas.get(claveOrdenada) - 1; // Restamos 1 porque la palabra no se cuenta a sí misma
                resultado.append(cantidadAnagramas).append(" ");
            }

            System.out.println(resultado.toString().trim()); // Imprimir resultado para el caso de prueba actual
        }

        s.close();
    }

    // Función auxiliar para ordenar los caracteres de una palabra
    private static String ordenarLetras(String palabra) {
        char[] letras = palabra.toCharArray();
        Arrays.sort(letras);
        return new String(letras);
    }
}

