import java.util.*;
import java.util.regex.*;

public class Votos {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

	        // Leer el número de partidos y pronósticos
	        int numPartidos = scanner.nextInt();
	        int numPronosticos = scanner.nextInt();
	        scanner.nextLine(); // Consumir la línea en blanco

	        // Leer los partidos y sus porcentajes
	        Map<String, Double> resultados = new HashMap<>();
	        for (int i = 0; i < numPartidos; i++) {
	            String linea = scanner.nextLine().trim();
	            String[] entrada = linea.split(" ");
	            String partido = entrada[0];
	            double porcentaje = Double.parseDouble(entrada[1]);
	            resultados.put(partido, porcentaje);
	        }

	        // Procesar los pronósticos
	        for (int i = 1; i <= numPronosticos; i++) {
	            String pronostico = scanner.nextLine().trim();
	            
	            // Extraer las partes del pronóstico: expresión, operador, valor esperado
	            Matcher matcher = Pattern.compile("(.+)\\s+(>|<|>=|<=|=)\\s+([0-9]+)").matcher(pronostico);
	            if (matcher.matches()) {
	                String expresion = matcher.group(1);
	                String operador = matcher.group(2);
	                double valorComparar = Double.parseDouble(matcher.group(3));

	                // Calcular la suma de los partidos en la expresión
	                double suma = 0.0;
	                String[] partidos = expresion.split("\\+");
	                for (String partido : partidos) {
	                    partido = partido.trim();
	                    suma += resultados.getOrDefault(partido, 0.0);
	                }

	                // Comparar la suma con el valor esperado
	                boolean correcto = false;
	                switch (operador) {
	                    case ">":
	                        correcto = suma > valorComparar;
	                        break;
	                    case "<":
	                        correcto = suma < valorComparar;
	                        break;
	                    case ">=":
	                        correcto = suma >= valorComparar;
	                        break;
	                    case "<=":
	                        correcto = suma <= valorComparar;
	                        break;
	                    case "=":
	                        correcto = Math.abs(suma - valorComparar) < 1e-6; // Comparación precisa
	                        break;
	                }

	                // Imprimir el resultado
	                if (correcto) {
	                    System.out.println("Guess #" + i + " was correct.");
	                } else {
	                    System.out.println("Guess #" + i + " was incorrect.");
	                }
	            } else {
	                System.out.println("Formato de pronóstico inválido en la línea " + i);
	            }
	        }

	        scanner.close();
	}

}