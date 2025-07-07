package teste;

import dados.Cliente;
import dados.Funcionario;
import dados.Veiculo;
import java.util.ArrayList;

import negocio.ControladorEstacionamento;
import negocio.RepositorioCliente;
import negocio.RepositorioFuncionario;
import negocio.RepositorioVeiculo;

public class Main {
    public static void main(String[] args) {
        testeSingleton();
    }

    public static void testarTodosRepositorios() {
        
        RepositorioCliente repoCliente = RepositorioCliente.getInstance();
        Cliente cliente = new Cliente(new ArrayList<>(), "Fulano", "111.222.333-44", 30);
        repoCliente.adicionar(cliente); 
        
        
        RepositorioVeiculo repoVeiculo = RepositorioVeiculo.getInstance();
        Veiculo veiculo = new Veiculo("Toyota", "2021", "ABC1234", "SUV");
        repoVeiculo.adicionar(veiculo); 
        
        
        RepositorioFuncionario repoFunc = RepositorioFuncionario.getInstance();
        Funcionario func = new Funcionario(3000.0, 4.5, "08:00-17:00", "Beltrano", "555.666.777-88");
        repoFunc.adicionar(func);
        
        
    }

    public static void testeSingleton(){
        ControladorEstacionamento controller01 = ControladorEstacionamento.getInstance();
        ControladorEstacionamento controller02 = ControladorEstacionamento.getInstance();
        RepositorioCliente clienteRepository01 = RepositorioCliente.getInstance();
        RepositorioCliente clienteRepository02 = RepositorioCliente.getInstance();

        if(clienteRepository01 == clienteRepository02){
            System.out.println("o singleton pegou, OBA!");
        }
        else{
            System.out.println("deu merda alek");
        }
    }
}