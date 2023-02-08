package transferenciaDatosUDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
    public static void main(String[] args) {
        String mensaje = "";
        try{
            DatagramSocket socket = new DatagramSocket(41500);
            FileWriter fichero = new FileWriter("src\\transferenciaDatosUDP\\fichero.txt");
            PrintWriter pw = new PrintWriter(fichero);

            //Si el mensaje es diferente de FIN se repite
            while (!mensaje.equals("FIN")){
                System.out.println("Creación del arra de bytes");
                byte[] buffer = new byte[64];

                System.out.println("Creación del datagrama");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("A la espera de recibir datagrama");
                socket.receive(packet);

                //Abrimos el fichero

                System.out.println("Leemos el mensaje");
                System.out.println("Mensaje que ha enviado el cliente: "+ new String(packet.getData()).trim());
                mensaje = new String(packet.getData()).trim();
                if (!mensaje.equals("FIN")){
                    String[] cadena = mensaje.split(": ");
                    System.out.println("Imprimimos el mensaje en el fichero");
                    pw.println(cadena[1]);
                }
            }
            pw.close();
            fichero.close();
            socket.close();

        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error en la recepción del paquete");
            e.printStackTrace();
        }
    }
}
