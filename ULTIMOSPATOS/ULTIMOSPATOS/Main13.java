import java.util.*;

public class Main13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // número de pelotas
        int[] dp = new int[8]; // dp[i] almacenará el costo mínimo para cubrir la combinación de deportes i
        Arrays.fill(dp, Integer.MAX_VALUE); // inicializamos el dp con valores grandes
        
        // Iteramos sobre las pelotas
        for (int i = 0; i < n; i++) {
            int price = sc.nextInt();
            String sports = sc.next();
            
            // Calculamos la máscara de bits para esta pelota
            int mask = 0;
            if (sports.contains("F")) mask |= 1; // Futsal
            if (sports.contains("W")) mask |= 2; // Wally
            if (sports.contains("B")) mask |= 4; // Basquet
            
            // Actualizamos las combinaciones posibles
            dp[mask] = Math.min(dp[mask], price);
            
            // Combinamos esta pelota con otras combinaciones anteriores
            for (int j = 0; j < 8; j++) {
                if (dp[j] != Integer.MAX_VALUE) {
                    dp[j | mask] = Math.min(dp[j | mask], dp[j] + price);
                }
            }
        }
        
        // La respuesta es el costo mínimo para la combinación de los tres deportes (máscara 7)
        int result = dp[7];
        
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1); // No es posible cubrir los tres deportes
        } else {
            System.out.println(result); // Imprimimos el costo mínimo
        }
    }
}

