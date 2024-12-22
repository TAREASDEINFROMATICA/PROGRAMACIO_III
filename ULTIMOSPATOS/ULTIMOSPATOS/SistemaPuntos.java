import java.util.*;

public class SistemaPuntos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Leer los valores iniciales
        int m = sc.nextInt(); // Número de palabras en el diccionario
        int n = sc.nextInt(); // Número de descripciones de trabajo
        sc.nextLine(); // Limpiar la línea

        // Crear el diccionario de palabras con su valor
        Map<String, Integer> diccionario = new HashMap<>();

        // Leer las palabras del diccionario y sus valores
        for (int i = 0; i < m; i++) {
            String palabra = sc.next();
            int valor = sc.nextInt();
            diccionario.put(palabra, valor);
            sc.nextLine(); // Limpiar la línea
        }

        // Procesar cada descripción de trabajo
        for (int i = 0; i < n; i++) {
            int salarioTotal = 0;
            String linea;

            // Leer las líneas de la descripción hasta encontrar un punto
            while (!(linea = sc.nextLine()).equals(".")) {
                // Dividir la línea en palabras
                String[] palabras = linea.split(" ");
                
                // Sumar el valor de las palabras que estén en el diccionario
                for (String palabra : palabras) {
                    salarioTotal += diccionario.getOrDefault(palabra, 0);
                }
            }

            // Imprimir el salario total para esta descripción de trabajo
            System.out.println(salarioTotal);
        }
        
        sc.close();
    }
}
