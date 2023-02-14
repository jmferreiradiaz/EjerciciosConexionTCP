package ejercicio3UDP;

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
                String mensaje = "Hola "+ new String(packet.getData()).trim();
                System.out.println("Mensaje que ha enviado el cliente: "+ mensaje);

                int sumaAscii = 0;

                //Pasamos la cadena a ASCII
                for (int i = 0; i<mensaje.length(); i++){
                    char letra = mensaje.charAt(i);
                    int valor = (int) letra;
                    System.out.println("La letra "+letra+" tiene un valor de "+valor);
                    sumaAscii = sumaAscii + valor;
                }

                System.out.println("Enviamos mensaje al cliente");
                System.out.println(sumaAscii);
                mensaje = Integer.toString(sumaAscii);
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
