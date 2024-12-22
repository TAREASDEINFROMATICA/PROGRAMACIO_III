
package proyecto_programacion_iii;

import java.util.Scanner;

public class PROYECTO_PROGRAMACION_III {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nSeleccione una opción para ejecutar:");
            System.out.println("1 - Ejecutar ClaseA");
            System.out.println("2 - Ejecutar ClaseB");
            System.out.println("3 - Ejecutar ClaseC");
            System.out.println("0 - Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    GatoVsRatonPvE.main(new String[]{});
                    break;
                case 2:
                    GatoVsRatonPvEMemo.main(new String[]{});
                    break;
                case 3:
                    GatoVsRatonPvP.main(new String[]{});
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }while (opcion != 0);

    }
}



