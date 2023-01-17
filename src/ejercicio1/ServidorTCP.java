package ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try {

            int numero;
            String mensaje;

            // 1. CreaciÃ³n del socket servidor
            System.out.println("(Servidor): Abriendo conexión...");
            ServerSocket socketServidor = new ServerSocket(50000);

            // 2. Espera y acepta conexiones
            System.out.println("(Servidor): Aceptando conexión...");
            Socket socketCliente = socketServidor.accept();

            // 3. Flujos de entrada y salida
            System.out.println("(Servidor): Abriendo flujos de entrada y salida...");
            InputStream is = socketCliente.getInputStream();
            OutputStream os = socketCliente.getOutputStream();

            // 4. Intercambiar datos con el cliente
            //Leer mensaje en viado por el cliente e imprimirlo por consola
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("(Servidor): Mensaje recibido del cliente: ");
            numero = br.read();
            System.out.println(numero);
            mensaje = esPrimo(numero);

            //Enviarle mensaje al cliente
            System.out.println("Servidor envía al cliente un menasaje");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("(Servidor): "+mensaje);
            bw.newLine();
            bw.flush();

            // 5. Cerramos flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();

            // 6. Cerramos la conexiÃ³n
            socketCliente.close();
            socketServidor.close();
        }
        catch (IOException e) {
            System.err.println("Error: Error al crear el socket en el puerto 50000");
        }
    }
    public static String esPrimo(int num){
        String resul = "Es primo";

        if (num <= 1){
            resul = "No es primo";
        }

        for (int i = 2; i <= num; i++){
            if (num % i == 0){
                resul = "No es primo";
            }
        }
        return resul;
    }
}