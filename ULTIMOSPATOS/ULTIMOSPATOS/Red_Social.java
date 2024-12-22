import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class Red_Social {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        Scanner scanner = new Scanner(System.in);
		        int n = scanner.nextInt();
		        int k = scanner.nextInt();
		        int[] mensajes = new int[n];
		        for (int i = 0; i < n; i++) {
		            mensajes[i] = scanner.nextInt();
		        }

		        Queue<Integer> pantalla = new LinkedList<>();
		        Set<Integer> enPantalla = new HashSet<>();

		        for (int mensaje : mensajes) {
		            if (!enPantalla.contains(mensaje)) {
		                if (pantalla.size() == k) {
		                    int eliminado = pantalla.poll();
		                    enPantalla.remove(eliminado);
		                }
		                pantalla.add(mensaje);
		                enPantalla.add(mensaje);
		            }
		        }

		        List<Integer> resultado = new ArrayList<>(pantalla);
		        Collections.reverse(resultado);

		        System.out.println(resultado.size());
		        for (int i = 0; i < resultado.size(); i++) {
		            System.out.print(resultado.get(i));
		            if (i < resultado.size() - 1) {
		                System.out.print(" ");
		            }
		        }
		    }

	}