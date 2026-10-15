import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {
    private static final String nombre = "src/enteros.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce un número entre 0 y 9: ");
        int numero = sc.nextInt();

        System.out.print("Introduce la cantidad de veces que deseas añadirlo: ");
        int cantidad = sc.nextInt();

        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "rw")) {
            fichero.seek(fichero.length());

            for (int i = 0; i < cantidad; i++) {
                fichero.writeInt(numero);
            }
            System.out.println("Valores añadidos correctamente al final del archivo.");
        } catch (IOException e) {
            System.out.println("Error al acceder al fichero.");
        }

        System.out.println("\nContenido actualizado del archivo:");
        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "r")) {
            while (fichero.getFilePointer() < fichero.length()) {
                int val = fichero.readInt();
                System.out.print(val + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}
