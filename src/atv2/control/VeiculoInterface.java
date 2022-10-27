package atv2.control;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface VeiculoInterface extends Remote{
    
    
    
    public void add (Veiculo v) throws RemoteException;
    public String getDados() throws RemoteException;
}
