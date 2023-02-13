package MultihilosTCP;

import javax.security.auth.Destroyable;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocketTCP {


    public static void main(String[] args) {

        Socket socketCliente;

        Scanner scn = new Scanner(System.in);

        String mensaje;

        try {

            socketCliente = new Socket(InetAddress.getLocalHost(), 4120);

            InputStream is = socketCliente.getInputStream();

            OutputStream os = socketCliente.getOutputStream();

            InputStreamReader isw = new InputStreamReader(is,"UTF8");

            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

            BufferedWriter bw = new BufferedWriter(osw);

            BufferedReader br = new BufferedReader(isw);

            System.out.println("Inserte la dirección que desea");

            mensaje = scn.nextLine();

            bw.write(mensaje.trim());

            bw.newLine();

            bw.flush();

            mensaje = br.readLine();

            System.out.println(mensaje.trim());

            bw.close();

            br.close();

            isw.close();

            osw.close();

            is.close();

            os.close();





        } catch (UnknownHostException er) {
            er.printStackTrace();
        } catch (IOException er) {
            er.printStackTrace();
        }

    }

}
