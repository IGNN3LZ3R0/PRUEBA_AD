import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class HiloClienteUDP extends Thread {
    private DatagramSocket socket;
    private InetAddress clientAddress;
    private int clientPort;
    private List<Pregunta> preguntas;

    public HiloClienteUDP(DatagramSocket socket, InetAddress clientAddress, int clientPort, List<Pregunta> preguntas) {
        this.socket = socket;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
        this.preguntas = preguntas;
    }

    @Override
    public void run() {
        try {
            for (Pregunta pregunta : preguntas) {
                // Enviar la pregunta
                String textoPregunta = pregunta.getPregunta();
                byte[] buffer = textoPregunta.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                socket.send(packet);

                // Enviar las opciones
                for (String opcion : pregunta.getOpciones()) {
                    buffer = opcion.getBytes();
                    packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                    socket.send(packet);
                }

                // Esperar respuesta del cliente
                byte[] responseBuffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                socket.receive(responsePacket);
                String respuestaCliente = new String(responsePacket.getData(), 0, responsePacket.getLength());

                // Verificar la respuesta
                char respuestaCorrecta = pregunta.getRespuestaCorrecta();
                String resultado = (respuestaCliente.charAt(0) == respuestaCorrecta) ? "Correcto!" : "Incorrecto!";
                

          
                buffer = resultado.getBytes();
                packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                socket.send(packet);
            }

           
            String fin = "Fin del cuestionario";
            byte[] bufferFin = fin.getBytes();
            DatagramPacket packetFin = new DatagramPacket(bufferFin, bufferFin.length, clientAddress, clientPort);
            socket.send(packetFin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
