package MultihilosUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ClienteSocketUDP {

    public static void main(String[] args) {
        DatagramSocket socket;
        int puertoServidor = 42069;
        String nombreServidor = "localhost";
        Scanner sc = new Scanner(System.in);
        String num;
        try {
            // 1 - Obtención de la dirección del servidor usando el métod getByName de
            // InetAddress
            System.out.println("(Cliente): Estableciendo parámetros de conexión...");

            InetAddress direccionServidor = InetAddress.getByName(nombreServidor);

            // 2 - Creación del socket UDP
            System.out.println("(Cliente): Creando el socket...");
            socket = new DatagramSocket();

            // 3 - Generación del datagrama
            System.out.println("(Cliente): Creando datagrama...");

            DatagramPacket paqueteSalida;

            byte[] bufferSalida;

            byte[] bufferEntrada;

            DatagramPacket paqueteEntrada;

            String aux ="";


            while (!aux.equals("ACERTADO")){

                System.out.println("Inserte un número: ");
                num = sc.nextLine();
                bufferSalida = num.getBytes();

                paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor,
                        puertoServidor);

                // 4 - Envío del datagrama a través de send
                System.out.println("(Cliente) Enviando datagrama...");
                socket.send(paqueteSalida);

                // 5 - Recepción de la respuesta
                System.out.println("(Cliente) Recibiendo respuesta...");

                bufferEntrada = new byte[64];

                paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                socket.receive(paqueteEntrada);

                aux = new String(paqueteEntrada.getData()).trim();

                System.out.println("Mensaje recibido: " +  aux);

            }


            // 6 - Cierre del socket
            System.out.println("(Cliente): Cerrando conexión...");
            socket.close();
            System.out.println("(Cliente): Conexión cerrada.");

        } catch (SocketException e) {
            System.err.println("Error al conectar con el servidor.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("No se ha podido enviar o recibir el paquete");
            e.printStackTrace();
        }
    }

}