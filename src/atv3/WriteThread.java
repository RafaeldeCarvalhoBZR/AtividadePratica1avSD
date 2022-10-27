package atv3;

import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class WriteThread extends Thread {
	private PrintWriter writer;
    private Socket socket;
    private ChatClient cliente;
 
    public WriteThread(Socket socket, ChatClient cliente) {
        this.socket = socket;
        this.cliente = cliente;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error recebendo output: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void run() {
 
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\nInforme seu apelido(nickname): ");
        String nomeUsuario = sc.nextLine();
        cliente.setUserName(nomeUsuario);
        writer.println(nomeUsuario);
 
        String text;              
        
        do {
            text = sc.nextLine();
				writer.println(text);
        } while (!text.equals("#QUIT"));
 
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Erro enviando ao servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
