package ejercicio2UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            //  Obtener dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();
            // Creación del socket
            DatagramSocket socket = new DatagramSocket();

            Scanner sc = new Scanner(System.in);

            //  Creación del mensaje
            System.out.println("¿Envia la primera cadena?");
            String mensaje = sc.nextLine();
            byte[] buffer = mensaje.getBytes();
            DatagramPacket packet1 = new DatagramPacket(buffer, buffer.length, direccion, 41500);
            socket.send(packet1);

            System.out.println("¿Envia la segunda cadena?");
            mensaje = sc.nextLine();
            buffer = mensaje.getBytes();
            DatagramPacket packet2 = new DatagramPacket(buffer, buffer.length, direccion, 41500);
            socket.send(packet2);


            byte[] bufferSalida = new byte[64];
            DatagramPacket packetRecibido = new DatagramPacket(bufferSalida, bufferSalida.length, direccion, 41500);
            System.out.println("Recibimos el mensaje del servidor");
            socket.receive(packetRecibido);
            System.out.println("Mensaje que ha enviado el servidor: "+new String(packetRecibido.getData()).trim());
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
