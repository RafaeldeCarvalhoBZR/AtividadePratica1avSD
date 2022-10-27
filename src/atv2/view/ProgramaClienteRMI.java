package atv2.view;


import java.rmi.Naming;


import atv2.control.Veiculo;
import atv2.control.VeiculoInterface;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

 
public class ProgramaClienteRMI {
  ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
  
  
public static void main(String[] args) throws Exception{ 
        String urlServidor = "rmi://192.168.100.6/RmiServidor"; // 172.18.3.194
        VeiculoInterface serv = (VeiculoInterface)Naming.lookup(urlServidor);
        
         
        
        
        
                       Scanner ler = new Scanner(System.in);
                       Veiculo v = new Veiculo();
                       System.out.println("Insira seu nome");
                        String nome= ler.nextLine();
                        v.setNomeCliente(nome);
                        System.out.println("Insira a marca do veiculo");
                        String marcaVeiculo = ler.nextLine();
                        v.setMarcaVeiculo(marcaVeiculo);
                        System.out.println("Informe o valor da venda");
                        double valorVenda = ler.nextDouble();
                        v.setValorVenda(valorVenda);
                        System.out.println("Informe o ano do veiculo");
                        int ano = ler.nextInt();
                        v.setAno(ano);
             
                       
                       serv.add(v);
                 
                       System.out.println(serv.getDados());
                 
               
              
               int y = Integer.parseInt(JOptionPane.showInputDialog("1-Continuar     \n2-Sair"));
               if(y == 1){
                   main(null);
               }
}


}
