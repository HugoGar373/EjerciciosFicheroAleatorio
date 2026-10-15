import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio4 {
    private static final String nombre = "src/juegos.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce la edad para buscar juegos recomendados: ");
        int edadBuscada = sc.nextInt();

        System.out.println("\nJuegos recomendados para " + edadBuscada + " años:");

        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "r")) {
            boolean encontrado = false;

            while (fichero.getFilePointer() < fichero.length()) {
                int orden = fichero.readInt();

                char[] nombreArr = new char[10];
                for (int i = 0; i < nombreArr.length; i++) {
                    nombreArr[i] = fichero.readChar();
                }

                int edadMinima = fichero.readInt();

                char[] tematicaArr = new char[10];
                for (int i = 0; i < tematicaArr.length; i++) {
                    tematicaArr[i] = fichero.readChar();
                }

                if (edadBuscada >= edadMinima) {
                    System.out.println("Nombre: " + new String(nombreArr).trim() +
                            " | Temática: " + new String(tematicaArr).trim());
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontraron juegos aptos para esa edad.");
            }

        } catch (IOException e) {
            System.out.println("Error al acceder al fichero.");
        }
    }
}
