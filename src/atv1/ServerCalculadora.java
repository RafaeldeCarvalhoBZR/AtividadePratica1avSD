package atv1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class ServerCalculadora {

	public static void main(String[] args) throws IOException {
	  
		DatagramSocket ds = new DatagramSocket(5001);
	  
        byte[] buffer = null;
	  
        DatagramPacket recebe = null;
        DatagramPacket envia = null;
        
        System.out.println("Servidor online.");
  
        while (true) {
            buffer = new byte[65535];
  
            recebe = new DatagramPacket(buffer, buffer.length);
  
            ds.receive(recebe);
  
            String msgCliente = new String(buffer, 0, buffer.length);
  
            msgCliente = msgCliente.trim();
  
            System.out.println("Comando recebido: "
                               + msgCliente);
  
            if (msgCliente.equals("sair")) {
                System.out.println(
                    "Cliente deseja sair. fechando.");
                break;
            }
  
            Double operacao;
            String resultado;
            
            StringTokenizer st = new StringTokenizer(msgCliente);
  
            try {
            	Double operando1 = Double.parseDouble(st.nextToken());
                String operador = st.nextToken();
                Double operando2 = Double.parseDouble(st.nextToken());
      
                if (operador.equals("+"))
                    operacao = operando1 + operando2;
      
                else if (operador.equals("-"))
                    operacao = operando1 - operando2;
      
                else if (operador.equals("*"))
                    operacao = operando1 * operando2;
      
                else
                    operacao = operando1 / operando2;
      
                System.out.println("Enviando resultado...");
                resultado = "O resultado da operação " + Double.toString(operando1) + " " +operador + " " + Double.toString(operando2) + " = " + Double.toString(operacao);
                buffer = resultado.getBytes();
			} catch (Exception e) {
				resultado = "Input invalido, digite conforme o comando.";
				buffer = resultado.getBytes();		
			}
            
  
            int porta = recebe.getPort();
  
            envia = new DatagramPacket(
                buffer, buffer.length, InetAddress.getLocalHost(),
                porta);
            ds.send(envia);
        }
    }
}
