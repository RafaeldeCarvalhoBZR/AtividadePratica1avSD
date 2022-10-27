package atv3;


import java.net.*;
import java.io.*;

public class ChatClient {
   private String nomeHost;
   private int porta;
   private String nomeUsuario;

   public ChatClient(String nomeHost, int porta) {
       this.nomeHost = nomeHost;
       this.porta = porta;
   }

   public void execute() {
       try {
           Socket socket = new Socket(nomeHost, porta);

           System.out.println("Conectado ao servidor de chat");

           new ReadThread(socket, this).start();
           new WriteThread(socket, this).start();

       } catch (UnknownHostException ex) {
           System.out.println("servidor não encontrado: " + ex.getMessage());
       } catch (IOException ex) {
           System.out.println("I/O Erro: " + ex.getMessage());
       }

   }

   void setUserName(String nomeUsuario) {
       this.nomeUsuario = nomeUsuario;
   }

   String getUserName() {
       return this.nomeUsuario;
   }


   public static void main(String[] args) {

       String nomeHost = "localhost";
       int porta = 8989;

       ChatClient client = new ChatClient(nomeHost, porta);
       client.execute();
   }
}
