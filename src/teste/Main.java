package teste;

import dados.Cliente;
import dados.Funcionario;
import dados.Veiculo;
import java.util.ArrayList;
import negocio.RepositorioCliente;
import negocio.RepositorioFuncionario;
import negocio.RepositorioVeiculo;

public class Main {
    public static void main(String[] args) {
        testarTodosRepositorios();
    }

    public static void testarTodosRepositorios() {
        
        RepositorioCliente repoCliente = new RepositorioCliente();
        Cliente cliente = new Cliente(new ArrayList<>(), "Fulano", "111.222.333-44", 30);
        repoCliente.adicionar(cliente); 
        
        
        RepositorioVeiculo repoVeiculo = new RepositorioVeiculo();
        Veiculo veiculo = new Veiculo("Toyota", "2021", "ABC1234", "SUV");
        repoVeiculo.adicionar(veiculo); 
        
        
        RepositorioFuncionario repoFunc = new RepositorioFuncionario();
        Funcionario func = new Funcionario(3000.0, 4.5, "08:00-17:00", "Beltrano", "555.666.777-88");
        repoFunc.adicionar(func);
        
        
    }
}