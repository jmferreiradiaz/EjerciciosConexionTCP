package unidad3Ejercicio2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            //1 Creación del socket tipo cliente
            System.out.println("(Cliente):Creación de socket...");
            InetAddress direccion = InetAddress.getLocalHost();
            Socket socketCliente = new Socket(direccion,50000);

            //2 - Abrir flujos de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida");
            OutputStream os = socketCliente.getOutputStream();
            InputStream is = socketCliente.getInputStream();
            Scanner sc = new Scanner(System.in);

            //3 - Intercambio de datos con el servidor
            //Enviarle mensaje al servidor
            System.out.println("Cliente envía al servidor un menasaje");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            System.out.println("(Cliente): Introduzca una dirección web");
            String url = sc.nextLine();
            bw.write(url);
            bw.newLine();
            bw.flush();

            //Leer mensaje enviado por el servidor e imprimirlo por consola
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("(Cliente): Mensaje recibido del servidor: "+ br.readLine());


            //4 - Cerrar flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();

        } catch (UnknownHostException e) {
            System.err.println("ERROR: no se encuentra el host");
            e.printStackTrace();
        } catch (IOException e){
            System.err.println("ERROR: Problema con la conexión");
            e.printStackTrace();
        }
    }
}
