package atv2.control;


import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
 
public class RmiServidor {
 
    public static void main(String[] args) throws Exception{ 

        LocateRegistry.createRegistry(1099);
 

        RmiServidorExemplo serv = new RmiServidorExemplo();
       

            

        Naming.bind("RmiServidor", serv);
       
        
        System.out.println("RmiServidor Conectado !!!");
    }
}
