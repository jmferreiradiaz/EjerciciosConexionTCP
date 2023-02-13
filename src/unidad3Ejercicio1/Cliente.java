package unidad3Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            //  Obtener dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();

            // Creación del socket
            System.out.println("Creando el socket");
            DatagramSocket socket = new DatagramSocket();

            System.out.println("Averigua el numero secreto (Entre 0 y 100)");
            int numeroSecreto = 0;
            String mensaje = "";
            boolean numeroEncontrado = false;
            int i = 0;
            //Si no el cliente no ha acertado el numero se repite
            while (!numeroEncontrado) {
                //  Creación del mensaje
                System.out.println("Inserta un número:");
                numeroSecreto = sc.nextInt();
                byte[] bufferEnvio = Integer.toString(numeroSecreto).getBytes();
                DatagramPacket packetEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccion, 41500);
                socket.send(packetEnvio);

                System.out.println("A la espera de recibir datagrama");
                byte[] bufferRecibo = new byte[64];
                DatagramPacket packetRecibo = new DatagramPacket(bufferRecibo, bufferRecibo.length, direccion, 41500);
                socket.receive(packetRecibo);

                //  Respuesta del servidor
                mensaje = new String(packetRecibo.getData()).trim();
                System.out.println("[Servidor]: " + mensaje);
                if(mensaje.equals("El número enviado es correcto")){
                    numeroEncontrado = true;
                }
                i++;
            }
            System.out.println("Número adivinado en " + i + " intentos.");

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
