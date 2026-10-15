import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1 {
    private static final String nombre = "src/enteros.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "rw")) {
            fichero.setLength(0);

            for (int i = 0; i < 6; i++) {
                fichero.writeInt(1);
            }
            for (int i = 0; i < 10; i++) {
                fichero.writeInt(2);
            }
            System.out.println("Fichero escrito correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        System.out.println("\nContenido actual del archivo:");
        mostrarContenido();

        System.out.print("\nIntroduce un número entre 0 y 9: ");
        int nuevoValor = sc.nextInt();

        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "rw")) {
            while (fichero.getFilePointer() < fichero.length()) {
                long posActual = fichero.getFilePointer();
                int numero = fichero.readInt();

                if (numero == 2) {
                    fichero.seek(posActual);
                    fichero.writeInt(nuevoValor);
                }
            }
            System.out.println("Sustitución realizada.");
        } catch (IOException e) {
            System.out.println("Error al modificar el fichero.");
        }

        System.out.println("\nContenido final del archivo:");
        mostrarContenido();

    }

    private static void mostrarContenido() {
        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "r")) {
            while (fichero.getFilePointer() < fichero.length()) {
                int valor = fichero.readInt();
                System.out.print(valor + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}