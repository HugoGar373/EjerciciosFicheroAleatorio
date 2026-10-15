import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio7 {
    private static final String entrada = "src/precio.dat";
    private static final String salida = "src/descuento.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el % de descuento a aplicar: ");
        double porcentaje = sc.nextDouble();

        try (RandomAccessFile fEntrada = new RandomAccessFile(entrada, "r");
             RandomAccessFile fSalida = new RandomAccessFile(salida, "rw")) {

            fSalida.setLength(0);

            while (fEntrada.getFilePointer() < fEntrada.length()) {
                fEntrada.seek(fEntrada.getFilePointer() + (10 * 2));

                char[] dniArr = new char[9];
                for (int i = 0; i < dniArr.length; i++) {
                    dniArr[i] = fEntrada.readChar();
                }

                fEntrada.seek(fEntrada.getFilePointer() + 4);

                double cantidadAnterior = fEntrada.readDouble();

                double cantidadConDescuento = cantidadAnterior - (cantidadAnterior * (porcentaje / 100.0));

                fSalida.writeChars(new String(dniArr));
                fSalida.writeDouble(cantidadAnterior);
                fSalida.writeDouble(cantidadConDescuento);
            }

            System.out.println("Fichero 'descuento.dat' creado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros.");
        }

        System.out.println("\nListado de descuento:");
        try (RandomAccessFile fMostrar = new RandomAccessFile(salida, "r")) {
            while (fMostrar.getFilePointer() < fMostrar.length()) {
                char[] dniArr = new char[9];
                for (int i = 0; i < dniArr.length; i++) {
                    dniArr[i] = fMostrar.readChar();
                }

                double anterior = fMostrar.readDouble();
                double conDescuento = fMostrar.readDouble();

                System.out.println("DNI: " + new String(dniArr).trim() +
                        ", Cantidad Anterior: " + anterior + " €" +
                        ", Cantidad con Descuento: " + conDescuento + " €");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero 'descuento.dat'.");
        }
    }
}