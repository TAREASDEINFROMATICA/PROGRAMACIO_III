
import java.util.*;

public class Main15 {
    public static void main(String[] args) {
        // Leer la entrada
        Scanner sc = new Scanner(System.in);
        
        // Usamos una lista para almacenar los números de entrada
        List<Integer> numbers = new ArrayList<>();
        
        // Leer los números de la entrada
        while (sc.hasNextInt()) {
            numbers.add(sc.nextInt());
        }

        // Paso 1: Contar las ocurrencias de cada número
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Paso 2: Filtrar los números que aparecen solo una vez
        List<Integer> uniqueNumbers = new ArrayList<>();
        for (int num : numbers) {
            if (frequencyMap.get(num) == 1) {
                uniqueNumbers.add(num);
            }
        }
        
        // Paso 3: Alternar la asignación de los números a los dos grupos
        List<Integer> leftGroup = new ArrayList<>();
        List<Integer> rightGroup = new ArrayList<>();
        
        for (int i = 0; i < uniqueNumbers.size(); i++) {
            if (i % 2 == 0) {
                leftGroup.add(uniqueNumbers.get(i));  // Asignar a la izquierda
            } else {
                rightGroup.add(uniqueNumbers.get(i));  // Asignar a la derecha
            }
        }
        
        // Paso 4: Imprimir el resultado
        System.out.println(leftGroup.size() + " " + rightGroup.size());
    }
}
