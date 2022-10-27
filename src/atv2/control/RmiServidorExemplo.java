package atv2.control;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RmiServidorExemplo extends
     UnicastRemoteObject implements VeiculoInterface {
	private static final long serialVersionUID = 1L;
        
	List<String> history;
    
    ArrayList<Veiculo> lista = new ArrayList<>();   
     
    protected RmiServidorExemplo() throws RemoteException {
        super();
       
        this.history = new LinkedList<String>();
    }

    public String exibirMensagem(String txt) throws RemoteException {
        String hist = "";
        System.out.println("O cliente enviou  a msg = " + txt);
        for (String h:  this.history) {
            hist = hist + "\n";
        }
        
       
        
        return "Retorno RMI : " + txt.toUpperCase() + "\n "+hist;
        
    }

    public Double somar(Double x, Double y) throws RemoteException {
        Double resp = x + y;
        System.out.println("O Cliente quer somar " + x + " + " + y);
        this.history.add(" "+x +" + " + y + " = " + resp );
        return resp;
    }

      public String mostrar() throws RemoteException{
          
          System.out.println("Nome  Marca_veiculo   Valor   Ano");
          for(int i = 0; i<this.lista.size(); i++){
               System.out.println(this.lista.get(i).getDados());
          }
            return null;
      }
            
     public void pegardados(Veiculo v)throws RemoteException{
         for(int i = 0; i<v.lista.size(); i++){
               this.lista.add(v.lista.get(i));
          }
         
     }
        @Override
    public void add(Veiculo v)throws RemoteException{
      
          this.lista.add(v);
          
    }
    
    public String getDados() throws RemoteException{
        System.out.println("br.com.rmi.control.RmiServidorExemplo.getDados()");
            return "ola";
    }
}
