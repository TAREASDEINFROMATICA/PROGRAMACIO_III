import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EntrenamientoPokemon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Leer el número de casos de prueba
        int T = sc.nextInt();
        
        // Procesar cada caso de prueba
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt(); // Número de batallas ganadas
            int M = sc.nextInt(); // Número de niveles
            int E = sc.nextInt(); // Experiencia ganada por cada batalla
            
            sc.nextLine(); // Consumir la nueva línea
            
            // Mapa para almacenar la experiencia de cada Pokémon
            Map<String, Integer> experienciaPokemons = new HashMap<>();
            
            // Leer los nombres de los pokemones ganadores y acumular su experiencia
            for (int i = 0; i < N; i++) {
                String pokemon = sc.nextLine();
                experienciaPokemons.put(pokemon, experienciaPokemons.getOrDefault(pokemon, 0) + E);
            }
            
            // Leer la experiencia necesaria para cada nivel
            int[] experienciaPorNivel = new int[M];
            for (int i = 0; i < M; i++) {
                experienciaPorNivel[i] = sc.nextInt();
            }
            
            int nivelMaximo = 0; // Nivel máximo alcanzado por algún Pokémon
            
            // Calcular el nivel de cada Pokémon
            for (int experiencia : experienciaPokemons.values()) {
                int nivel = 0;
                int experienciaAcumulada = 0;
                
                // Determinar el nivel basado en la experiencia acumulada
                for (int i = 0; i < M; i++) {
                    experienciaAcumulada += experienciaPorNivel[i];
                    if (experiencia >= experienciaAcumulada) {
                        nivel = i + 1;
                    } else {
                        break;
                    }
                }
                
                // Actualizar el nivel máximo alcanzado
                nivelMaximo = Math.max(nivelMaximo, nivel);
            }
            
            // Imprimir el resultado para el caso actual
            System.out.println(nivelMaximo);
        }
        
        sc.close();
    }
}
