
package proyecto_programacion_iii;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GatoVsRatonPvEMemo {
    public static int[][] tablero = {
            {0, 0, 1, 0, 0, 0, 1, 3}, // Gato en (0,7)
            {1, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {2, 0, 0, 0, 0, 0, 0, 4} // Ratón en (7,0), salida en (7,7)
    };

    public static int ratonX = 7, ratonY = 0; // Posición inicial del ratón
    public static int gatoX = 0, gatoY = 7;   // Posición inicial del gato
    public static Random random = new Random();
    public static Map<String, Integer> memo = new HashMap<>(); // Para memoización

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            imprimirTablero();
            juegoTerminado = moverRaton(scanner); // Turno del ratón
            if (juegoTerminado) break;

            imprimirTablero();
            juegoTerminado = moverGato(); // Turno del gato usando minimax
        }

        scanner.close();
    }

    public static void imprimirTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == ratonX && j == ratonY) {
                    System.out.print("2 "); // Ratón
                } else if (i == gatoX && j == gatoY) {
                    System.out.print("3 "); // Gato
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean moverRaton(Scanner scanner) {
        System.out.println("Turno del Ratón (Jugador 1):");

        while (true) { // Mantener el bucle hasta obtener un movimiento válido
            System.out.print("Ingrese movimiento (WASD): ");
            char movimiento = scanner.next().toUpperCase().charAt(0);

            int nuevoX = ratonX;
            int nuevoY = ratonY;
            switch (movimiento) {
                case 'W': nuevoX--; break; // Arriba
                case 'S': nuevoX++; break; // Abajo
                case 'A': nuevoY--; break; // Izquierda
                case 'D': nuevoY++; break; // Derecha
                default:
                    System.out.println("Movimiento no válido.");
                    continue;
            }

            if (esMovimientoValido(nuevoX, nuevoY)) {
                ratonX = nuevoX;
                ratonY = nuevoY;

                if (tablero[ratonX][ratonY] == 4) {
                    System.out.println("¡El ratón ha llegado a la salida! ¡Jugador 1 gana!");
                    return true;
                }
                break;
            } else {
                System.out.println("Movimiento inválido, hay un muro o está fuera de límites.");
            }
        }
        return false;
    }

    public static boolean moverGato() {
        System.out.println("Turno del Gato (Movimiento con minimax):");
        int profundidad = 5; // Profundidad máxima para limitar el alcance del minimax
        int[] mejorMovimiento = minimax(gatoX, gatoY, ratonX, ratonY, true, profundidad);
        
        gatoX = mejorMovimiento[1];
        gatoY = mejorMovimiento[2];

        if (gatoX == ratonX && gatoY == ratonY) {
            System.out.println("¡El gato ha atrapado al ratón! ¡Jugador 2 gana!");
            return true;
        }
        return false;
    }

    public static int[] minimax(int gatoX, int gatoY, int ratonX, int ratonY, boolean turnoGato, int profundidad) {
        // Límite de profundidad para cortar la recursión
        if (profundidad == 0) {
            return new int[] {evaluarEstado(gatoX, gatoY, ratonX, ratonY), gatoX, gatoY};
        }

        // Estado del juego actual para la memoización
        String estado = gatoX + "," + gatoY + "," + ratonX + "," + ratonY + "," + turnoGato + "," + profundidad;
        if (memo.containsKey(estado)) {
            return new int[] {memo.get(estado), gatoX, gatoY};
        }

        // Condiciones de salida de la función
        if (gatoX == ratonX && gatoY == ratonY) {
            return new int[] {100, gatoX, gatoY}; // Gato gana
        }
        if (ratonX == 7 && ratonY == 7) {
            return new int[] {-100, ratonX, ratonY}; // Ratón gana
        }

        int mejorValor = turnoGato ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] mejorMovimiento = {mejorValor, gatoX, gatoY};
        int[] movimientos = {-1, 0, 1, 0, 0, -1, 0, 1}; // Movimientos posibles

         for (int i = 0; i < movimientos.length; i += 2) {
            int nuevoX = turnoGato ? gatoX + movimientos[i] : ratonX + movimientos[i];
            int nuevoY = turnoGato ? gatoY + movimientos[i + 1] : ratonY + movimientos[i + 1];

            if (esMovimientoValido(nuevoX, nuevoY)) {
                int[] resultado = minimax(
                        turnoGato ? nuevoX : gatoX,
                        turnoGato ? nuevoY : gatoY,
                        turnoGato ? ratonX : nuevoX,
                        turnoGato ? ratonY : nuevoY,
                        !turnoGato,
                        profundidad - 1
                );

                if ((turnoGato && resultado[0] > mejorValor) || (!turnoGato && resultado[0] < mejorValor)) {
                    mejorValor = resultado[0];
                    mejorMovimiento = new int[] {mejorValor, nuevoX, nuevoY};
                }
            }
        }

        // Guardar el resultado en memoización para evitar cálculos repetitivos
        memo.put(estado, mejorValor);
        return mejorMovimiento;
    }

    // Función para evaluar el estado del juego en base a la distancia entre el gato y el ratón
    public static int evaluarEstado(int gatoX, int gatoY, int ratonX, int ratonY) {
        int distancia = Math.abs(gatoX - ratonX) + Math.abs(gatoY - ratonY);
        return -distancia; // El gato preferirá acercarse al ratón
    }


    public static boolean esMovimientoValido(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8 && tablero[x][y] != 1 && tablero[x][y] != 8;
    }
}
