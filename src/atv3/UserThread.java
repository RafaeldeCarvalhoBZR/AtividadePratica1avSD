package atv3;

import java.io.*;
import java.net.*;
 
public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter escritor;
 
    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }
 
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader leitor = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            escritor = new PrintWriter(output, true);
 
            String nomeUsuario = leitor.readLine();
            server.addUserName(nomeUsuario);
            server.selfcast("Você entrou no grupo de bate-papo como " + nomeUsuario, this);
 
            String mensagemServidor = "Novo usuário conectado: " + nomeUsuario;
            server.broadcast(mensagemServidor, this);
 
            String mensagemCliente;
            
            Boolean isQuit = true;
 
            do {
                mensagemCliente = leitor.readLine();
                if(mensagemCliente != null) {
	                if (!mensagemCliente.equals("#USERS") && !mensagemCliente.equals("#QUIT")) {
		                mensagemServidor = nomeUsuario + "> " + mensagemCliente;
		                server.broadcast(mensagemServidor, this);
	                }
                }
                
                if(mensagemCliente != null) {
                	if(mensagemCliente.equals("#QUIT")) {
                		server.selfcast("Você saiu do chat.", this);
                		isQuit = false;
                	} else if (mensagemCliente.equals("#USERS")) {
						server.selfcast(printUsers(), this);
					}
                }
            } while (isQuit);
 
            server.removeUser(nomeUsuario, this);
            socket.close();
 
            mensagemServidor = nomeUsuario + " Saiu.";
            server.broadcast(mensagemServidor, this);
 
        } catch (IOException ex) {
            System.out.println("Erro na thread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    String printUsers() {
        if (server.hasUsers()) {
            return "Usuários conectados: " + server.getUserNames();
        } else {
        	return"Nenhum outro usuário conectado";
        }
    }
 
    void sendMessage(String message) {
    		escritor.println(message);
    }
}