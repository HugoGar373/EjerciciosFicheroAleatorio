import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio6 {
    private static final String entrada = "src/practicas.dat";
    private static final String salida = "src/precio.dat";
    private static final double precio = 45.7;

    public static void main(String[] args) {
        try (RandomAccessFile fEntrada = new RandomAccessFile(entrada, "r");
             RandomAccessFile fSalida = new RandomAccessFile(salida, "rw")) {

            fSalida.setLength(0);

            while (fEntrada.getFilePointer() < fEntrada.length()) {
                char[] nombreArr = new char[10];
                for (int i = 0; i < nombreArr.length; i++) {
                    nombreArr[i] = fEntrada.readChar();
                }

                fEntrada.seek(fEntrada.getFilePointer() + (35 * 2));

                char[] dniArr = new char[9];
                for (int i = 0; i < dniArr.length; i++) {
                    dniArr[i] = fEntrada.readChar();
                }

                fEntrada.seek(fEntrada.getFilePointer() + 4);

                int numClases = fEntrada.readInt();

                double cantidadAPagar = numClases * precio;

                fSalida.writeChars(new String(nombreArr));

                fSalida.writeChars(new String(dniArr));

                fSalida.writeInt(numClases);

                fSalida.writeDouble(cantidadAPagar);
            }

            System.out.println("Fichero 'precio.dat' generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error en el procesamiento de ficheros.");
        }
    }
}