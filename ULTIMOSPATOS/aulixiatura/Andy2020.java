import java.util.Scanner;

public class Andy2020 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Número de casos de prueba
        while (t-- > 0) {
            int n = sc.nextInt(); // Longitud de la cadena
            String s = sc.next();

            // Comprobamos si "2020" ya está al inicio, al final, o si se puede formar
            boolean posible =
                    s.startsWith("2020") || // La cadena comienza con "2020"
                    s.endsWith("2020") || // La cadena termina con "2020"
                    (s.startsWith("2") && s.endsWith("020")) || // "2" al inicio y "020" al final
                    (s.startsWith("20") && s.endsWith("20")) || // "20" al inicio y "20" al final
                    (s.startsWith("202") && s.endsWith("0")); // "202" al inicio y "0" al final

            System.out.println(posible ? "SI" : "NO");
        }
    }
}
