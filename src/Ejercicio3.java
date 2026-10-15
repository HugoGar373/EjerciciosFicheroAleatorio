import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio3 {
    private static final String nombre = "src/juegos.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "rw")) {
            fichero.seek(fichero.length());

            String responder;
            do {
                System.out.print("Introduce número de orden (entero): ");
                int orden = sc.nextInt();
                sc.nextLine();

                System.out.print("Introduce nombre del juego (máx 10 caracteres): ");
                String nombre = sc.nextLine();

                System.out.print("Introduce edad mínima recomendada (entero): ");
                int edadMinima = sc.nextInt();
                sc.nextLine();

                System.out.print("Introduce clasificación/temática (máx 10 caracteres): ");
                String tematica = sc.nextLine();

                fichero.writeInt(orden);

                StringBuffer sbNombre = new StringBuffer(nombre);
                sbNombre.setLength(10);
                fichero.writeChars(sbNombre.toString());

                fichero.writeInt(edadMinima);

                StringBuffer sbTematica = new StringBuffer(tematica);
                sbTematica.setLength(10);
                fichero.writeChars(sbTematica.toString());

                System.out.print("¿Deseas añadir otro juego? (s/n): ");
                responder = sc.nextLine();
            } while (responder.equalsIgnoreCase("s"));

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero.");
        }

        System.out.println("\n*** LISTADO DE JUEGOS ***");
        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "r")) {
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

                System.out.println("Orden: " + orden + ", Nombre: " + new String(nombreArr).trim() + ", Edad Mínima: " + edadMinima + ", Temática: " + new String(tematicaArr).trim());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}