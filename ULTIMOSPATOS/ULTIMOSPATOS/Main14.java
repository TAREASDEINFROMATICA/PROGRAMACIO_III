import java.util.*;

public class Main14 {
    // Método para calcular el máximo común divisor
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Número de puntos
        int n = sc.nextInt();
        
        // Mapa para contar las pendientes
        Map<String, Integer> slopeCount = new HashMap<>();
        
        // Variable para el resultado máximo
        int maxPoints = 0;
        
        // Leer los puntos
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            
            // Si el punto es (0,0), lo ignoramos ya que no se necesita
            if (x == 0 && y == 0) continue;
            
            // Calcular el MCD de x y y
            int gcd = gcd(x, y);
            
            // Simplificar la pendiente
            int dx = x / gcd;
            int dy = y / gcd;
            
            // Asegurar que la pendiente sea representada de manera única
            // Normalizamos el signo de la pendiente
            if (dx < 0) {
                dx = -dx;
                dy = -dy;
            }
            
            // Usamos una cadena para representar la pendiente
            String slope = dx + "/" + dy;
            
            // Contar las pendientes
            slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
            
            // Actualizar el número máximo de puntos
            maxPoints = Math.max(maxPoints, slopeCount.get(slope));
        }
        
        // Imprimir el resultado
        System.out.println(maxPoints);
    }
}
