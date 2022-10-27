package atv2.control;


import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class Veiculo  extends UnicastRemoteObject     
			implements  Serializable {
     
    int idVeiculo;
    String nomeCliente;
    String marcaVeiculo;
    double valorVenda;
    int  ano;
    
    ArrayList<Veiculo> lista = new ArrayList();  

    private static final long serialVersionUID = 2L;
   
    public Veiculo() throws RemoteException {
	  super();
	}
    public Veiculo(String nomeCliente, String marcaVeiculo, double valorVenda, int ano) throws RemoteException{
        this.nomeCliente = nomeCliente;
        this.marcaVeiculo = marcaVeiculo;
        this.valorVenda = valorVenda;
        this.ano = ano;
        Veiculo v;
    }

    

    public int getIdVeiculo() throws RemoteException{
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) throws RemoteException{
        this.idVeiculo = idVeiculo;
    }
    
    
    public String getNomeCliente() throws RemoteException{
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) throws RemoteException{
        this.nomeCliente = nomeCliente;
    }

    public String getMarcaVeiculo() throws RemoteException{
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) throws RemoteException{
        this.marcaVeiculo = marcaVeiculo;
    }

    public double getValorVenda() throws RemoteException{
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) throws RemoteException{
        this.valorVenda = valorVenda;
    }

    public int getAno() throws RemoteException{
        return ano;
    }

    public void setAno(int ano) throws RemoteException{
        this.ano = ano;
    }

     
        

     
      public String getDados() throws RemoteException{
        return this.nomeCliente+"   "+this.marcaVeiculo+"   "+this.valorVenda+" "+this.ano+"\n";
    }
      
      
      
      
      
      
}
