package ejercicio1UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(41500);
            while (true){
                System.out.println("Creación del arra de bytes");
                byte[] buffer = new byte[64];

                System.out.println("Creación del datagrama");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("A la espera de recibir datagrama");
                socket.receive(packet);

                System.out.println("Leemos el mensaje");
                System.out.println("Mensaje que ha enviado el cliente: "+ new String(packet.getData()).trim());
                String mensaje = "Hola "+ new String(packet.getData());

                System.out.println("Enviamos mensaje al cliente");
                System.out.println(mensaje);
                byte[] bufferSalida = mensaje.getBytes();
                DatagramPacket packetSalida = new DatagramPacket(bufferSalida, bufferSalida.length, packet.getAddress(), packet.getPort());
                socket.send(packetSalida);

            }
        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error en la recepción del paquete");
            e.printStackTrace();
        }
    }
}
