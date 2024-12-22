package Proyecto_Gato_y_Raton;

import java.util.Random;
import java.util.Scanner;

public class GatoVsRatonPvE {
    private static int[][] tablero = {
            {0, 0, 1, 0, 0, 0, 1, 3}, // Gato en (0,7)
            {1, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {2, 0, 0, 1, 0, 0, 0, 4} // Ratón en (7,0), salida en (7,7)
    };
    
    private static int ratonX = 7, ratonY = 0; // Posición inicial del ratón
    private static int gatoX = 0, gatoY = 7;   // Posición inicial del gato
    private static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            imprimirTablero();
            juegoTerminado = moverRaton(scanner); // Turno del ratón
            if (juegoTerminado) break;
            
            imprimirTablero();
            juegoTerminado = moverGato(); // Turno del gato
        }
        
        scanner.close();
    }

    private static void imprimirTablero() {
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

    private static boolean moverRaton(Scanner scanner) {
        System.out.println("Turno del Ratón (Jugador 1):");

        while (true) { // Mantener el bucle hasta obtener un movimiento válido
            System.out.print("Ingrese movimiento (WASD): ");
            char movimiento = scanner.next().toUpperCase().charAt(0);

            int nuevoX = ratonX, nuevoY = ratonY;
            switch (movimiento) {
                case 'W': nuevoX--; break; // Arriba
                case 'S': nuevoX++; break; // Abajo
                case 'A': nuevoY--; break; // Izquierda
                case 'D': nuevoY++; break; // Derecha
                default:
                    System.out.println("Movimiento no válido.");
                    continue;
            }

            // Verificar si el movimiento es válido
            if (esMovimientoValido(nuevoX, nuevoY)) {
                // Limpiar posición anterior en el tablero
                tablero[ratonX][ratonY] = 0;

                // Actualizar la nueva posición del ratón
                ratonX = nuevoX;
                ratonY = nuevoY;

                // Verificar si el ratón ha llegado a la salida
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

    private static boolean moverGato() {
        System.out.println("Turno del Gato (Movimiento aleatorio):");

        while (true) { // Mantener el bucle hasta obtener un movimiento válido
            int movimiento = random.nextInt(4); // Generar movimiento aleatorio
            int nuevoX = gatoX, nuevoY = gatoY;

            switch (movimiento) {
                case 0: nuevoX--; break; // Arriba
                case 1: nuevoX++; break; // Abajo
                case 2: nuevoY--; break; // Izquierda
                case 3: nuevoY++; break; // Derecha
            }

            // Verificar si el movimiento es válido
            if (esMovimientoValido(nuevoX, nuevoY)) {
                // Limpiar posición anterior en el tablero
                tablero[gatoX][gatoY] = 0;

                // Actualizar la nueva posición del gato
                gatoX = nuevoX;
                gatoY = nuevoY;

                // Verificar si el gato ha atrapado al ratón
                if (gatoX == ratonX && gatoY == ratonY) {
                    System.out.println("¡El gato ha atrapado al ratón! ¡Jugador 2 gana!");
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private static boolean esMovimientoValido(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8 && tablero[x][y] != 1 && tablero[x][y] != 4;
    }
}
