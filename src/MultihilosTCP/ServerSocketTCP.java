package MultihilosTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTCP {


    public static void main(String[] args) {

        try {

            ServerSocket server = new ServerSocket(4120);

            Socket peticion;

            while (true) {
                // 2 - Queda a la espera de peticiones y las acepta cuando las recibe
                System.out.println("Servidor se encuentra a la escucha de peticiones...");
                peticion = server.accept();
                System.out.println("(Servidor) conexión establecida...");
                new GestorProcesos(peticion).start();
            }


        }catch (IOException er){
            er.printStackTrace();
        }

    }

}
