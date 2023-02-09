package unidad3Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(41500);

            System.out.println("Creando número aleatorio secreto");
            int numRecibido = -1;
            int numSecreto = (int)(Math.random() * 101);
            int i = 0;
            while (numSecreto != numRecibido) {
                byte[] buffer = new byte[64];

                DatagramPacket packetRecibo = new DatagramPacket(buffer, buffer.length);
                System.out.println("Esperando recibir número del cliente");
                socket.receive(packetRecibo);

                System.out.println("Leemos el número y lo comparamos");
                numRecibido = Integer.parseInt(new String(packetRecibo.getData()).trim());

                String mensajeEnvio;
                if(numRecibido > numSecreto) {
                    mensajeEnvio = "El número secreto es MENOR que el número enviado.";
                    System.out.println(numRecibido + " > " + numSecreto);
                }
                else if(numRecibido < numSecreto) {
                    mensajeEnvio = "El número secreto es MAYOR que el número enviado.";
                    System.out.println(numRecibido + " < " + numSecreto);
                }
                else {
                    mensajeEnvio = "El número enviado es correcto";
                    System.out.println(numRecibido + " = " + numSecreto);
                }

                byte[] bufferEnvio = mensajeEnvio.getBytes();
                DatagramPacket packetEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, packetRecibo.getAddress(), packetRecibo.getPort());
                socket.send(packetEnvio);
                i++;
            }

            System.out.println("El cliente ha averiguado el número en " + i + " intentos");
        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error en la recepción del paquete");
            e.printStackTrace();
        }
    }
}
