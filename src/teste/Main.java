package teste;

import dados.Cliente;
import dados.Veiculo;
import java.util.ArrayList;


public class Main {

    
    public static void main(String[] args) {
        
        Veiculo veiculo01 = new Veiculo("Fiat Uno","1989","abcd-1234","passeio");
        ArrayList<Veiculo> cliente01carros = new ArrayList <>();
        cliente01carros.add(veiculo01);
        Cliente cliente01 = new Cliente(cliente01carros,"Bananilson Farofa","1234567-8",45);
        System.out.println(cliente01);
        
    }
    
}
