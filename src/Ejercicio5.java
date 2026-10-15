import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5 {
    private static final String nombre = "src/practicas.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos alumnos deseas guardar?: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "rw")) {
            fichero.setLength(0);

            for (int i = 0; i < cantidad; i++) {
                System.out.println("\nAlumno " + (i + 1));

                System.out.print("Nombre (10 caracteres): ");
                String nombre = sc.nextLine();

                System.out.print("Apellidos (35 caracteres): ");
                String apellidos = sc.nextLine();

                System.out.print("DNI (9 caracteres: 8 dígitos y 1 letra): ");
                String dni = sc.nextLine();

                System.out.print("Vehículo de prácticas (entero): ");
                int vehiculo = sc.nextInt();

                System.out.print("Número de clases de prácticas (entero): ");
                int numClases = sc.nextInt();
                sc.nextLine();

                StringBuffer sbNombre = new StringBuffer(nombre);
                sbNombre.setLength(10);
                fichero.writeChars(sbNombre.toString());

                StringBuffer sbApellidos = new StringBuffer(apellidos);
                sbApellidos.setLength(35);
                fichero.writeChars(sbApellidos.toString());

                StringBuffer sbDni = new StringBuffer(dni);
                sbDni.setLength(9);
                fichero.writeChars(sbDni.toString());

                fichero.writeInt(vehiculo);

                fichero.writeInt(numClases);
            }

            System.out.println("\nDatos guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero.");
        }

        System.out.println("\nLISTADO DE ALUMNOS (practicas.dat)");
        try (RandomAccessFile fichero = new RandomAccessFile(nombre, "r")) {
            while (fichero.getFilePointer() < fichero.length()) {
                char[] nombreArr = new char[10];
                for (int j = 0; j < nombreArr.length; j++) {
                    nombreArr[j] = fichero.readChar();
                }

                char[] apellidosArr = new char[35];
                for (int j = 0; j < apellidosArr.length; j++) {
                    apellidosArr[j] = fichero.readChar();
                }

                char[] dniArr = new char[9];
                for (int j = 0; j < dniArr.length; j++) {
                    dniArr[j] = fichero.readChar();
                }

                int vehiculo = fichero.readInt();
                int numClases = fichero.readInt();

                System.out.println("Nombre: " + new String(nombreArr).trim() + ", Apellidos: " + new String(apellidosArr).trim() +
                        ", DNI: " + new String(dniArr).trim() + ", Vehículo: " + vehiculo + ", Clases: " + numClases);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}