package MultihilosTCP;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GestorProcesos extends Thread {
    private Socket socket;
    private OutputStream os;

    private InputStream is;

    private InputStreamReader isr;

    private OutputStreamWriter osr;

    private BufferedReader br;

    private BufferedWriter bw;

    public GestorProcesos(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarProceso() throws IOException {

        is = socket.getInputStream();

        isr = new InputStreamReader(is, "UTF-8");

        br = new BufferedReader(isr);

        os = socket.getOutputStream();

        osr = new OutputStreamWriter(os, "UTF-8");

        bw = new BufferedWriter(osr);

        String aux = br.readLine();

        aux = leerLineaFichero(aux);

        bw.write(aux);

        bw.newLine();

        bw.flush();

        bw.close();

        br.close();

        osr.close();

        isr.close();

        os.close();

        is.close();

        socket.close();
    }


    public static String leerLineaFichero(String mensaje){

        boolean encontrado = false;

        String ip = "";

        String aux;

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("conexiones.txt"));

            aux = bufferedReader.readLine();

            String [] mensajeAEnviar;

            while (aux != null){

                mensajeAEnviar = aux.split("-");

                if (mensajeAEnviar[0].trim().equals(mensaje)){

                    ip = mensajeAEnviar[1];

                    encontrado = true;

                }

                aux = bufferedReader.readLine();

            }

        }catch (FileNotFoundException er){
            er.printStackTrace();
        }catch (IOException er){


        }

        if (!encontrado){

            ip = "La direccion no está registrada";
        }

        return ip;
    }

}