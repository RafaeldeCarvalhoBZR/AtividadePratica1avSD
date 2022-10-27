package atv1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientCalculadora {

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);

        DatagramSocket ds = new DatagramSocket();
  
        InetAddress ip = InetAddress.getLocalHost();
        byte buffer[] = null;
  
        while (true) {
        	System.out.print(
                "Digite o calculo da seguinte maneira:");
            System.out.println(
                "'operando1 operador operando2'");
            System.out.println(
	                "Para sair, digite 'sair'");
  
            String input = sc.nextLine();
            buffer = new byte[65535];
            buffer = input.getBytes();
  
            DatagramPacket envia = new DatagramPacket(
                buffer, buffer.length, ip, 5001);
  
            ds.send(envia);
  
            if (input.equals("sair"))
                break;
  
            buffer = new byte[65535];
  
            DatagramPacket recebe
                = new DatagramPacket(buffer, buffer.length);
  
            ds.receive(recebe);
  
            System.out.println(new String(buffer, 0, buffer.length));
        }
	}
}
