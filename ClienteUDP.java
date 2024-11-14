import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClienteUDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String respuesta;
        int correctas = 0; // Contador de respuestas correctas
        StringBuilder resultados = new StringBuilder(); // Usamos StringBuilder para acumular las respuestas
        String ipCliente = obtenerIpCliente();

        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = ahora.format(formato);

        // Registro de fecha y IP del cliente
        resultados.append("IP del cliente: ").append(ipCliente).append("\n");
        resultados.append("Fecha y hora: ").append(fechaHora).append("\n");

        // Preguntas y respuestas
        // Pregunta 1
        System.out.println("Pregunta 1: ¿Cuál es el río más largo del mundo?");
        System.out.println("a) Nilo");
        System.out.println("b) Amazonas");
        System.out.println("c) Yangtsé");
        respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("b")) {
            correctas++;
            System.out.println("Pregunta 1: Correcto! La respuesta correcta es: b) Amazonas");
            resultados.append("Pregunta 1: Correcto! La respuesta correcta es: b) Amazonas \n");
        } else {
            System.out.println("Pregunta 1: Incorrecto! La respuesta correcta es: b) Amazonas");
            resultados.append("Pregunta 1: Incorrecto! La respuesta correcta es: b) Amazonas \n");
        }

        // Pregunta 2
        System.out.println("Pregunta 2: ¿Cuál es el océano más grande del mundo?");
        System.out.println("a) Atlántico");
        System.out.println("b) Índico");
        System.out.println("c) Pacífico");
        respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("c")) {
            correctas++;
            System.out.println("Pregunta 2: Correcto! La respuesta correcta es: c) Pacífico");
            resultados.append("Pregunta 2: Correcto! La respuesta correcta es: c) Pacífico \n");
        } else {
            System.out.println("Pregunta 2: Incorrecto! La respuesta correcta es: c) Pacífico");
            resultados.append("Pregunta 2: Incorrecto! La respuesta correcta es: c) Pacífico \n");
        }

        // Pregunta 3
        System.out.println("Pregunta 3: ¿Cuál es el planeta más cercano al sol?");
        System.out.println("a) Venus");
        System.out.println("b) Mercurio");
        System.out.println("c) Marte");
        respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("b")) {
            correctas++;
            System.out.println("Pregunta 3: Correcto! La respuesta correcta es: b) Mercurio");
            resultados.append("Pregunta 3: Correcto! La respuesta correcta es: b) Mercurio\n");
        } else {
            System.out.println("Pregunta 3: Incorrecto! La respuesta correcta es: b) Mercurio");
            resultados.append("Pregunta 3: Incorrecto! La respuesta correcta es: b) Mercurio\n");
        }

        // Pregunta 4
        System.out.println("Pregunta 4: ¿Quién escribió 'Cien años de soledad'?");
        System.out.println("a) Gabriel García Márquez");
        System.out.println("b) Mario Vargas Llosa");
        System.out.println("c) Julio Cortázar");
        respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("a")) {
            correctas++;
            System.out.println("Pregunta 4: Correcto! La respuesta correcta es: a) Gabriel García Márquez");
            resultados.append("Pregunta 4: Correcto! La respuesta correcta es: a) Gabriel García Márquez\n");
        } else {
            System.out.println("Pregunta 4: Incorrecto! La respuesta correcta es: a) Gabriel García Márquez");
            resultados.append("Pregunta 4: Incorrecto! La respuesta correcta es: a) Gabriel García Márquez\n");
        }

        // Pregunta 5
        System.out.println("Pregunta 5: ¿Cuál es el idioma oficial de Brasil?");
        System.out.println("a) Español");
        System.out.println("b) Inglés");
        System.out.println("c) Portugués");
        respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("c")) {
            correctas++;
            System.out.println("Pregunta 5: Correcto! La respuesta correcta es: c) Portugués");
            resultados.append("Pregunta 5: Correcto! La respuesta correcta es: c) Portugués\n");
        } else {
            System.out.println("Pregunta 5: Incorrecto! La respuesta correcta es: c) Portugués");
            resultados.append("Pregunta 5: Incorrecto! La respuesta correcta es: c) Portugués\n");
        }

        // Puntuación total
        int puntosFinales = (correctas * 20) / 5; // cálculo de la puntuación total 

        // Resultado en consola
        System.out.println("\nFin del cuestionario");
        System.out.println("Respuestas correctas: " + correctas);
        System.out.println("Respuestas incorrectas: " + (5 - correctas));
        System.out.println("Puntuación total = " + puntosFinales);

        // Mostrar si el usuario aprobó o no (al menos 3 respuestas correctas)
        if (correctas >= 3) {
            System.out.println("¡Felicidades, aprobaste el cuestionario!");
        } else {
            System.out.println("Lo siento, no aprobaste el cuestionario.");
        }

        // Guardar los resultados en un StringBuilder
        resultados.append("\nRespuestas correctas: ").append(correctas).append("\n");
        resultados.append("Respuestas incorrectas: ").append(5 - correctas).append("\n");
        resultados.append("Puntuación total: ").append(puntosFinales).append("\n");
        if (correctas >= 3) {
            resultados.append("¡Felicidades, aprobaste el cuestionario!\n");
        } else {
            resultados.append("Lo siento, no aprobaste el cuestionario.\n");
        }

        // Enviar resultados al servidor
        enviarResultadosAlServidor(resultados.toString(), "172.29.28.93", 1236);
        scanner.close();
    }

    private static String obtenerIpCliente() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "IP desconocida";
        }
    }

    private static void enviarResultadosAlServidor(String resultados, String ipServidor, int puerto) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = resultados.getBytes();
            InetAddress address = InetAddress.getByName(ipServidor);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, puerto);
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al enviar los resultados al servidor: " + e.getMessage());
        }
    }
}