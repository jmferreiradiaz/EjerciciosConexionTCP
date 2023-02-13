package ejercicio2UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class ServidorUDP {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(41500);
                System.out.println("Creación del array de bytes");
                byte[] buffer = new byte[64];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                int cont = 0;
                String mensaje;
                String[] mensajes = new String[2];

                while(cont < 2) {
                    System.out.println("Creación del datagrama");
                    System.out.println("A la espera de recibir datagrama");
                    socket.receive(packet);

                    System.out.println("Leemos el mensaje");
                    mensaje = new String(packet.getData()).trim();
                    System.out.println("Mensaje que ha enviado el cliente: " + mensaje);
                    mensajes[cont] = mensaje;
                    cont++;
                }

                //Ordenamos las cadenas alfabéticamente
                Arrays.sort(mensajes);
                System.out.println("Enviamos mensaje al cliente");
                mensaje = mensajes[0]+"\n"+mensajes[1];
                byte[] bufferSalida = mensaje.getBytes();
                DatagramPacket packetSalida = new DatagramPacket(bufferSalida, bufferSalida.length, packet.getAddress(), packet.getPort());
                socket.send(packetSalida);

        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error en la recepción del paquete");
            e.printStackTrace();
        }
    }
}
