package atv3;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private int porta;
    private Set<String> nomeUsuarios = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
 
    public ChatServer(int porta) {
        this.porta = porta;
    }
 
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
 
            System.out.println("O servidor de chat está ouvindo na porta " + porta);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo usuário conectado");
 
                UserThread novoUsuario = new UserThread(socket, this);
                userThreads.add(novoUsuario);
                novoUsuario.start();
 
            }
 
        } catch (IOException ex) {
            System.out.println("Erro no servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
 
        int port = 8989;
 
        ChatServer server = new ChatServer(port);
        server.execute();
    }
 
    void broadcast(String mensagem, UserThread quemEnvia) {
    	for (UserThread aUser : userThreads) {
            if (aUser != quemEnvia) {
                aUser.sendMessage(mensagem);            
            }
        }
    }
    
    void selfcast(String mensagem, UserThread quemEnvia) {
        for (UserThread aUser : userThreads) {
            if (aUser == quemEnvia) {
                aUser.sendMessage(mensagem);
            }
        }
    }
 
    void addUserName(String nomeUsuario) {
        nomeUsuarios.add(nomeUsuario);
    }
 
    void removeUser(String nomeUsuario, UserThread aUser) {
        boolean removed = nomeUsuarios.remove(nomeUsuario);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("O " + nomeUsuario + " Saiu");
        }
    }
 
    Set<String> getUserNames() {
        return this.nomeUsuarios;
    }
 
    boolean hasUsers() {
        return !this.nomeUsuarios.isEmpty();
    }
}
