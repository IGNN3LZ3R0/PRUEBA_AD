import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServidorUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(5000)) {
            System.out.println("Servidor esperando respuestas de clientes...");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String resultados = new String(packet.getData(), 0, packet.getLength());

                // Guardar los resultados en el archivo
                guardarResultadosEnArchivo(resultados);
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static void guardarResultadosEnArchivo(String resultados) {
        try {
            File archivo = new File("resultados_cuestionario.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));
            writer.write("---- " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " ----\n");
            writer.write(resultados);
            writer.write("\n");
            writer.close();
            System.out.println("Resultados guardados en 'resultados_cuestionario.txt'");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
