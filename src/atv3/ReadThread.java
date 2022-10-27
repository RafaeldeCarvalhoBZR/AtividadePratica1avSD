package atv3;

import java.io.*;
import java.net.*;
 
public class ReadThread extends Thread {
    private BufferedReader leitor;
    private Socket socket;
    private ChatClient cliente;
 
    public ReadThread(Socket socket, ChatClient cliente) {
        this.socket = socket;
        this.cliente = cliente;
 
        try {
            InputStream input = socket.getInputStream();
            leitor = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error recebendo input: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
        while (true) {
            try {
                String response = leitor.readLine();
                System.out.println("\n" + response);
            } catch (IOException ex) {
                break;
            }
		}
    }
}
