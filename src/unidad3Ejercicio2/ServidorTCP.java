package unidad3Ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try {

            String mensaje, direccion;

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
            direccion = br.readLine().trim();
            System.out.println(direccion);
            mensaje = leerFichero(".\\src\\unidad3Ejercicio2\\direccionesTXT", direccion);



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
    /***
     * Lee el fichero a partir de la ruta pasada como parámetro
     * @param rutaFichero
     * @return
     * @throws IOException
     */
    public static String leerFichero(String rutaFichero, String direccion) throws IOException {
        File archivo = new File(rutaFichero);
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String contenidoFichero = "";

        // Lectura del fichero
        String linea;
        while((linea=br.readLine())!=null){
            System.out.println(linea);
            String cadena[] = linea.split("-");
            if (cadena[0].equals(direccion)){
                contenidoFichero = cadena[1];
                break;
            }else{
                contenidoFichero="Esa dirección web no está registrada";
            }
        }
        br.close();
        fr.close();
        return contenidoFichero;
    }
}