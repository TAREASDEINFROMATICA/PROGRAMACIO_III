import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
public class Segundo_Diccionario {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	        StringBuilder text = new StringBuilder();

	        // Leer hasta el final del archivo (EOF)
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            text.append(line).append(" "); // Agregar un espacio para separar las líneas
	        }

	        // Convertir el texto a minúsculas y manejar guiones
	        String[] words = text.toString().toLowerCase().split("\\s+");
	        Set<String> uniqueWords = new HashSet<>();

	        StringBuilder currentWord = new StringBuilder();

	        for (String word : words) {
	            // Si la palabra termina con un guion, continuamos la palabra
	            if (word.endsWith("-")) {
	                currentWord.append(word.substring(0, word.length() - 1)); // Eliminar el guion
	            } else {
	                currentWord.append(word); // Agregar la palabra actual
	                if (currentWord.length() > 0) {
	                    // Limpiar caracteres no alfabéticos al final de la palabra
	                    String cleanedWord = currentWord.toString().replaceAll("[^a-zA-Z-]", "");
	                    uniqueWords.add(cleanedWord); // Agregar la palabra completa al conjunto
	                    currentWord.setLength(0); // Reiniciar el StringBuilder para la siguiente palabra
	                }
	            }
	        }

	        // Usar TreeSet para ordenar las palabras alfabéticamente
	        TreeSet<String> sortedWords = new TreeSet<>(uniqueWords);

	        // Imprimir las palabras ordenadas
	        for (String word : sortedWords) {
	            System.out.println(word);
	        }

	        scanner.close();
	}

}