import java.util.*;

public class CertificadosAvengers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNext()) {
            int m = sc.nextInt(); // Número de misiones
            sc.nextLine(); // Limpiar línea

            Set<String> avengersSet = new HashSet<>(); // Para almacenar nombres de Avengers sin duplicados

            // Leer Avengers en cada misión y agregar al conjunto
            for (int i = 0; i < m; i++) {
                String[] line = sc.nextLine().split(" ");
                int n = Integer.parseInt(line[0]);

                for (int j = 1; j <= n; j++) {
                    avengersSet.add(line[j]); // Agregar cada avenger al conjunto
                }
            }

            String a = sc.nextLine(); // El nombre del Avenger con el que Nick está molesto

            // Convertir el conjunto a lista y ordenar alfabéticamente
            List<String> avengersList = new ArrayList<>(avengersSet);
            Collections.sort(avengersList);

            // Contar cuántos Avengers están antes de 'a' en orden alfabético
            int count = 0;
            for (String avenger : avengersList) {
                if (avenger.compareTo(a) < 0) {
                    count++;
                } else {
                    break;
                }
            }

            // Imprimir el número de certificados que se entregarán
            System.out.println(count);
        }

        sc.close();
    }
}
