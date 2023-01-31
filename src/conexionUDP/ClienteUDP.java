package conexionUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            //  Obtener dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();
            // Creación del socket
            DatagramSocket socket = new DatagramSocket();

            //  Creación del mensaje
            String mensaje = "Shope de pavo";
            byte[] buffer = mensaje.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 41500);
            socket.send(packet);
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
            e.printStackTrace();
        }
    }
}
