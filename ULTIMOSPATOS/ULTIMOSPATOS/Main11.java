import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main11 {

    public static int contarPares(int N, int[] A, int[] B, int[] C) {
        // Crear un mapa para contar las frecuencias de B[C[j]]
        Map<Integer, Integer> countB = new HashMap<>();

        // Llenar el mapa con las frecuencias de B[C[j]] usando C[j] como índices
        for (int j = 0; j < N; j++) {
            int index = C[j] - 1; // Ajustar el índice (1-indexed a 0-indexed)
            countB.put(B[index], countB.getOrDefault(B[index], 0) + 1);
        }

        // Contar cuántos pares (i, j) cumplen que A[i] == B[C[j]]
        int totalPares = 0;
        for (int i = 0; i < N; i++) {
            if (countB.containsKey(A[i])) {
                totalPares += countB.get(A[i]);
            }
        }

        return totalPares;
    }

    public static void main(String[] args) {
        // Crear un escáner para leer los datos de entrada
        Scanner sc = new Scanner(System.in);
        
        // Leer el tamaño de las secuencias
        int N = sc.nextInt();
        
        // Leer las secuencias A, B y C
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            B[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            C[i] = sc.nextInt();
        }
        
        // Obtener el resultado y mostrarlo
        int resultado = contarPares(N, A, B, C);
        System.out.println(resultado);
        
        // Cerrar el escáner
        sc.close();
    }
}

